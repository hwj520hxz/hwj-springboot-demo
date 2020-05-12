package com.hwj.demo.component.id.generator.impl;

import com.hwj.demo.component.id.generator.BitsAllocator;
import com.hwj.demo.component.id.generator.UidGenerator;
import com.hwj.demo.component.id.generator.exception.UidGenerateException;
import com.hwj.demo.component.id.generator.utils.DateUtils;
import com.hwj.demo.component.id.generator.worker.WorkerIdAssigner;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @author ：hwj
 * InitializingBean : 凡是继承该接口的类，在初始化bean的时候都会执行该方法
 * 和CachedUidGenerator获取的ID不同之处在于，DefaultUidGenerator获取的时间戳部分是当前时间，后者则不是
 */
public class DefaultUidGenerator implements UidGenerator, InitializingBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(DefaultUidGenerator.class);

    /** 雪花算法ID组成部分总计64位，还有一个sign是一个恒为0的值，保证算出的ID时正数 */
    protected int timeBits = 28;    //时间戳 28位即最大表示2^28的数值的秒数，换算一下就是8.7年左右
    protected int workerBits = 22;  //机器ID 最多可支持约420w次机器启动。内置实现为在启动时由数据库分配。420w = 2^22
    protected int seqBits = 13;     //每秒下的并发序列 13 bits可支持每秒8192个并发，即2^13个并发

    /** 起始时间戳 For example 2016-05-20 (ms: 1463673600000)*/
    protected String epochStr = "2016-05-20";  // 时间基点
    protected long epochSeconds = TimeUnit.MILLISECONDS.toSeconds(1463673600000L);

    /** Stable fields after spring bean initializing */
    protected BitsAllocator bitsAllocator;
    protected long workerId;

    /** Volatile fields caused by nextId() */
    protected long sequence = 0L;
    protected long lastSecond = -1L;

    /** Spring property */
    protected WorkerIdAssigner workerIdAssigner;

    @Override
    public void afterPropertiesSet() throws Exception {
        // initialize bits allocator
        bitsAllocator = new BitsAllocator(timeBits, workerBits, seqBits);

        // initialize worker id
        workerId = workerIdAssigner.assignWorkerId();
        if (workerId > bitsAllocator.getMaxWorkerId()) {
            throw new RuntimeException("Worker id " + workerId + " exceeds the max " + bitsAllocator.getMaxWorkerId());
        }

        LOGGER.info("Initialized bits(1, {}, {}, {}) for workerID:{}", timeBits, workerBits, seqBits, workerId);
    }

    @Override
    public long getUID() throws UidGenerateException {
        try {
            return nextId();
        } catch (Exception e) {
            LOGGER.error("Generate unique id exception. ", e);
            throw new UidGenerateException(e);
        }
    }

    @Override
    public String parseUID(long uid) {
        long totalBits = BitsAllocator.TOTAL_BITS;
        long signBits = bitsAllocator.getSignBits();
        long timestampBits = bitsAllocator.getTimestampBits();
        long workerIdBits = bitsAllocator.getWorkerIdBits();
        long sequenceBits = bitsAllocator.getSequenceBits();

        // parse UID
        long sequence = (uid << (totalBits - sequenceBits)) >>> (totalBits - sequenceBits);
        long workerId = (uid << (timestampBits + signBits)) >>> (totalBits - workerIdBits);
        long deltaSeconds = uid >>> (workerIdBits + sequenceBits);

        Date thatTime = new Date(TimeUnit.SECONDS.toMillis(epochSeconds + deltaSeconds));
        String thatTimeStr = DateUtils.formatByDateTimePattern(thatTime);

        // format as string
        return String.format("{\"UID\":\"%d\",\"timestamp\":\"%s\",\"workerId\":\"%d\",\"sequence\":\"%d\"}",
                uid, thatTimeStr, workerId, sequence);
    }

    /**
     * 获取 UID
     * 1.获取当前时间毫秒数（getCurrentSecond有校验是否超过超过最大支持时间年限，跟分配的bit数相关）
     * 2.是否有时钟回拨，有的话抛出异常
     * 3.时间是否在同一毫秒内
     *    3.1.是的话序列号自增1。自增后的序列号是否超过最大值（和seqBits有关，也就是每秒最大并发数），是的话获取下一个毫秒数
     *    3.2.否的话序列号重新置为0
     * 4.保存当前的毫秒数lastSecond（用于下一次获取ID时使用）
     * 5.各部分移位得到最终的ID（根据相应的数据所占的位数）
     */
    protected synchronized long nextId() {
        long currentSecond = getCurrentSecond();

        // 时间大于当前时间，拒绝生成uid
        if (currentSecond < lastSecond) {
            // 当前时间减去初始时间的秒数
            long refusedSeconds = lastSecond - currentSecond;
            throw new UidGenerateException("Clock moved backwards. Refusing for %d seconds", refusedSeconds);
        }

        // 相同毫秒内，序列号自增
        if (currentSecond == lastSecond) {
            sequence = (sequence + 1) & bitsAllocator.getMaxSequence();
            // 同一毫秒的序列数已经达到最大，我们等待下一秒生成uid
            if (sequence == 0) {
                currentSecond = getNextSecond(lastSecond);
            }

            // 不同毫秒内，序列从零开始
        } else {
            sequence = 0L;
        }

        lastSecond = currentSecond;

        // 为UID分配位
        return bitsAllocator.allocate(currentSecond - epochSeconds, workerId, sequence);
    }

    /**
     * 得到下一个毫秒
     */
    private long getNextSecond(long lastTimestamp) {
        long timestamp = getCurrentSecond();
        while (timestamp <= lastTimestamp) {
            timestamp = getCurrentSecond();
        }

        return timestamp;
    }

    /**
     * 得到当前毫秒
     */
    private long getCurrentSecond() {
        long currentSecond = TimeUnit.MILLISECONDS.toSeconds(System.currentTimeMillis());
        // 当前时间减去时间基点大于最大支持时间也就是
        if (currentSecond - epochSeconds > bitsAllocator.getMaxDeltaSeconds()) {
            throw new UidGenerateException("Timestamp bits is exhausted. Refusing UID generate. Now: " + currentSecond);
        }

        return currentSecond;
    }

    /**
     * workId分配
     */
    public void setWorkerIdAssigner(WorkerIdAssigner workerIdAssigner) {
        this.workerIdAssigner = workerIdAssigner;
    }

    public void setTimeBits(int timeBits) {
        if (timeBits > 0) {
            this.timeBits = timeBits;
        }
    }

    public void setWorkerBits(int workerBits) {
        if (workerBits > 0) {
            this.workerBits = workerBits;
        }
    }

    public void setSeqBits(int seqBits) {
        if (seqBits > 0) {
            this.seqBits = seqBits;
        }
    }

    public void setEpochStr(String epochStr) {
        if (StringUtils.isNotBlank(epochStr)) {
            this.epochStr = epochStr;
            this.epochSeconds = TimeUnit.MILLISECONDS.toSeconds(DateUtils.parseByDayPattern(epochStr).getTime());
        }
    }
}
