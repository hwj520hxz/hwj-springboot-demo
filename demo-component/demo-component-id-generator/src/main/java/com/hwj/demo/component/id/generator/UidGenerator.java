package com.hwj.demo.component.id.generator;

import com.hwj.demo.component.id.generator.exception.UidGenerateException;

/**
 * @author ：hwj
 * @Description ：主键生成接口
 * Snowflake算法描述：指定机器 & 同一时刻 & 某一并发序列，是唯一的。据此可生成一个64 bits的唯一ID
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
