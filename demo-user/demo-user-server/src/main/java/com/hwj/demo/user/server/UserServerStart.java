package com.hwj.demo.user.server;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@SpringBootApplication
@MapperScan("com.hwj.demo.user.server.dao")
public class UserServerStart {
    public static void main(String[] args) {
        SpringApplication.run(UserServerStart.class, args);
    }
}
