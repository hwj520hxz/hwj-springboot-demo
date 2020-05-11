package com.hwj.demo.user.server;

import com.hwj.demo.component.server.runtime.ServerRuntimeStart;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@SpringBootApplication
@Import(ServerRuntimeStart.class)
public class UserServerStart {
    public static void main(String[] args) {
        SpringApplication.run(UserServerStart.class, args);
    }
}
