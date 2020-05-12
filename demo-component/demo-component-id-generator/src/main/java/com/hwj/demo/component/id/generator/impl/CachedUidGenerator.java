package com.hwj.demo.component.id.generator.impl;

import com.hwj.demo.component.id.generator.buffer.BufferPaddingExecutor;
import com.hwj.demo.component.id.generator.buffer.RejectedPutBufferHandler;
import com.hwj.demo.component.id.generator.buffer.RejectedTakeBufferHandler;
import com.hwj.demo.component.id.generator.buffer.RingBuffer;
import com.hwj.demo.component.id.generator.exception.UidGenerateException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.util.Assert;
import com.hwj.demo.component.id.generator.BitsAllocator;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * 这是一种缓存型的ID生成方式，当剩余ID不足的时候，会异步的方式重新生成一批ID缓存起来，后续请求的时候直接返回现成的ID即可
 * 生成uid时性能比default要高
 * CachedUidGenerator启动过程：
 * 1.初始化Bean，实现InitializingBean的afterPropertiesSet
 * 2.读取spring配置的占位分配数据，包括timeBits、workerBits、seqBits等
 * 3.再数据库插入一条worknode数据并获取该数据ID作为workId
 * 4.开始填充RingBuffer 数组大小默认扩容至序列号部分的3倍，长度为65536
 * 5.设置paddingFactor，默认50，决定自动扩容的阈值
 * 6.设置BufferPaddingExecutor线程池，默认为CPU核数的2倍（主要是完成RingBuffer的初始化工作，包括容量大小、阈值设置、是否周期填充，拒绝策略等）
 * 7.根据spring配置加载Schedule周期填充，拒绝策略，默认不加载
 * 8.获取同一机器ID下，同一毫秒内的所有序列，放在List里
 * 9.循环读取List，执行RingBuffer的put方法
 * 10.获取tail，当前cursor位置
 * 11.RingBuffer满了或者flags数组内tail的下一个位置不是CAN_PUT_FLAG标志，是则执行拒绝策略
 * 12.slots数组增加此uid的值，flags数组相应索引位置设置为CAN_TAKE_FLAG
 * 13.直至填满66536个
 * 14.完成填充RingBuffer，初始化完成
 */
public class CachedUidGenerator extends DefaultUidGenerator implements DisposableBean {
    private static final Logger LOGGER = LoggerFactory.getLogger(CachedUidGenerator.class);
    private static final int DEFAULT_BOOST_POWER = 3;

    /** RingBuffer size扩容参数, 可提高UID生成的吞吐量 */
    /** 默认:3， 原bufferSize=8192, 扩容后bufferSize= 8192 << 3 = 65536 */
    private int boostPower = DEFAULT_BOOST_POWER;

    /** 指定何时向RingBuffer中填充UID, 取值为百分比(0, 100), 默认为50 */
    /** 举例: bufferSize=1024, paddingFactor=50 -> threshold=1024 * 50 / 100 = 512 */
    /** 当环上可用UID数量 < 512时, 将自动对RingBuffer进行填充补全 */
    private int paddingFactor = RingBuffer.DEFAULT_PADDING_PERCENT;

    /** 另外一种RingBuffer填充时机, 在Schedule线程中, 周期性检查填充 */
    /** 默认:不配置此项, 即不使用Schedule线程. 如需使用, 请指定Schedule线程时间间隔, 单位:秒 */
    private Long scheduleInterval;

    /** 拒绝策略: 当环已满, 无法继续填充时 */
    /** 默认无需指定, 将丢弃Put操作, 仅日志记录. 如有特殊需求, 请实现RejectedPutBufferHandler接口(支持Lambda表达式) */
    private RejectedPutBufferHandler rejectedPutBufferHandler;

    /** 拒绝策略: 当环已空, 无法继续获取时 */
    /** 默认无需指定, 将记录日志, 并抛出UidGenerateException异常. 如有特殊需求, 请实现RejectedTakeBufferHandler接口(支持Lambda表达式) */
    private RejectedTakeBufferHandler rejectedTakeBufferHandler;

    /** RingBuffer */
    private RingBuffer ringBuffer;
    private BufferPaddingExecutor bufferPaddingExecutor;

    /**
     * 在初始化bean的时候都会执行该方法
     **/
    @Override
    public void afterPropertiesSet() throws Exception {
        // initialize workerId & bitsAllocator
        super.afterPropertiesSet();

        // 初始化RingBuffer
        this.initRingBuffer();
        LOGGER.info("Initialized RingBuffer successfully.");
    }

    /**
     * 获取UID
     **/
    @Override
    public long getUID() {
        try {
            return ringBuffer.take();
        } catch (Exception e) {
            LOGGER.error("Generate unique id exception. ", e);
            throw new UidGenerateException(e);
        }
    }

    /**
     * 解析UID
     **/
    @Override
    public String parseUID(long uid) {
        return super.parseUID(uid);
    }

    /**
     * 关闭线程池
     **/
    @Override
    public void destroy() throws Exception {
        bufferPaddingExecutor.shutdown();
    }

    /**
     * 在相同的毫秒内获取最大的序列号下的uid
     *
     * @param currentSecond
     * @return UID list, size of {@link BitsAllocator#getMaxSequence()} + 1
     */
    protected List<Long> nextIdsForOneSecond(long currentSecond) {
        // 初始化序列，长度为MaxSequence+1,MaxSequence是从0开始计算的，所以需要加1，通过以下代码0L可以确定
        int listSize = (int) bitsAllocator.getMaxSequence() + 1;
        List<Long> uidList = new ArrayList<>(listSize);

        // 第一个序列的时间偏差进行计算，然后获取第一个序列的uid，其余序列的uid利用偏移量进行分配
        // currentSecond 当前毫秒  epochSeconds 时间基点 因为是第一个序列，所以sequence是0
        long firstSeqUid = bitsAllocator.allocate(currentSecond - epochSeconds, workerId, 0L);
        for (int offset = 0; offset < listSize; offset++) {
            uidList.add(firstSeqUid + offset);
        }
        return uidList;
    }

    /**
     * 初始化RingBuffer数组
     * RingBuffer是个环形数组，默认大小为8192个，里面缓存着生成的id
     * 填充机制：程序启动时，将RingBuffer填充满，缓存8192个id，在调用getUID()时，检测RingBuffer中的剩余id个数小于总个数的50%，将RingBuffer填充满，可配置定时填充
     */
    private void initRingBuffer() {
        // initialize RingBuffer
        int bufferSize = ((int) bitsAllocator.getMaxSequence() + 1) << boostPower;
        this.ringBuffer = new RingBuffer(bufferSize, paddingFactor);
        LOGGER.info("Initialized ring buffer size:{}, paddingFactor:{}", bufferSize, paddingFactor);

        // initialize RingBufferPaddingExecutor
        boolean usingSchedule = (scheduleInterval != null);
        this.bufferPaddingExecutor = new BufferPaddingExecutor(ringBuffer, this::nextIdsForOneSecond, usingSchedule);
        if (usingSchedule) {
            bufferPaddingExecutor.setScheduleInterval(scheduleInterval);
        }

        LOGGER.info("Initialized BufferPaddingExecutor. Using schdule:{}, interval:{}", usingSchedule, scheduleInterval);

        // set rejected put/take handle policy
        this.ringBuffer.setBufferPaddingExecutor(bufferPaddingExecutor);
        if (rejectedPutBufferHandler != null) {
            this.ringBuffer.setRejectedPutHandler(rejectedPutBufferHandler);
        }
        if (rejectedTakeBufferHandler != null) {
            this.ringBuffer.setRejectedTakeHandler(rejectedTakeBufferHandler);
        }

        // fill in all slots of the RingBuffer
        bufferPaddingExecutor.paddingBuffer();

        // start buffer padding threads
        bufferPaddingExecutor.start();
    }

    /**
     * Setters for spring property
     */
    public void setBoostPower(int boostPower) {
        Assert.isTrue(boostPower > 0, "Boost power must be positive!");
        this.boostPower = boostPower;
    }

    public void setRejectedPutBufferHandler(RejectedPutBufferHandler rejectedPutBufferHandler) {
        Assert.notNull(rejectedPutBufferHandler, "RejectedPutBufferHandler can't be null!");
        this.rejectedPutBufferHandler = rejectedPutBufferHandler;
    }

    public void setRejectedTakeBufferHandler(RejectedTakeBufferHandler rejectedTakeBufferHandler) {
        Assert.notNull(rejectedTakeBufferHandler, "RejectedTakeBufferHandler can't be null!");
        this.rejectedTakeBufferHandler = rejectedTakeBufferHandler;
    }

    public void setScheduleInterval(long scheduleInterval) {
        Assert.isTrue(scheduleInterval > 0, "Schedule interval must positive!");
        this.scheduleInterval = scheduleInterval;
    }
}
