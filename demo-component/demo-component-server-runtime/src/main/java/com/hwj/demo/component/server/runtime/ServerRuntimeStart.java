package com.hwj.demo.component.server.runtime;

import com.bosssoft.nontax.commons.groovy.mapper.spring.MapperBeanFactory;
import com.hwj.demo.component.id.generator.impl.CachedUidGenerator;
import com.hwj.demo.component.id.generator.worker.DisposableWorkerIdAssigner;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;


/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@MapperScan("com.hwj.demo.**.dao")

public class ServerRuntimeStart {

    @Bean
    public CachedUidGenerator uidGenerator(@Autowired DisposableWorkerIdAssigner disposableWorkerIdAssigner) {
        CachedUidGenerator cachedUidGenerator = new CachedUidGenerator();
        // 2^28 /(365*24*60*60) = 8年多
        cachedUidGenerator.setTimeBits(28);
        // 最多支持2^22 4194304 次机器启动
        cachedUidGenerator.setWorkerBits(22);
        // 每秒支持2^13 8192个并发
        cachedUidGenerator.setSeqBits(13);
        // 系统初始日期
        cachedUidGenerator.setEpochStr("2020-03-01");
        // 工作ID分配器
        cachedUidGenerator.setWorkerIdAssigner(disposableWorkerIdAssigner);
        return cachedUidGenerator;
    }

    @Bean
    public DisposableWorkerIdAssigner disposableWorkerIdAssigner(){
        return new DisposableWorkerIdAssigner();
    }

    @Bean
    public MapperBeanFactory mapper(@Value("classpath*:gmp/*.groovy") Resource[] resources) {
        MapperBeanFactory factory = new MapperBeanFactory();
        factory.setLocations(resources);
        return factory;
    }
}
