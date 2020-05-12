package com.hwj.demo.component.id.generator;

import com.hwj.demo.component.id.generator.exception.UidGenerateException;

/**
 * @author ：hwj
 * @Description ：主键生成接口
 * Snowflake算法描述：指定机器 & 同一时刻 & 某一并发序列，是唯一的。据此可生成一个64 bits的唯一ID
 * UidGenerator启动过程：
 * 1.初始化Bean，实现InitializingBean的afterPropertiesSet
 * 2.读取spring配置的占位分配数据，包括timeBits、workerBits、seqBits等
 * 3.再数据库插入一条worknode数据并获取该数据ID作为workId
 * 4.初始化完成，主要是完成workId的获取，每次重启都获取一个新的，保证不重复
 */
public interface UidGenerator {

    /**
     * 获取 ID
     */
    long getUID() throws UidGenerateException;

    /**
     * 解析ID
     */
    String parseUID(long uid);
}
