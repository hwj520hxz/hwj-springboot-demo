package com.hwj.demo.component.encryption;

import org.apache.commons.lang3.time.StopWatch;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：加密工具类
 */

public class PasswordUtil {

    /** spring security中的BCryptPasswordEncoder方法采用SHA-256 +随机盐+密钥对密码进行加密 */
    /** SHA系列是Hash算法，不是加密算法，使用加密算法意味着可以解密（这个与编码/解码一样），但是采用Hash处理，其过程是不可逆的 */
    private static final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(10);
    /** 默认密码 */
    public static final String DEFAULT_PASSWORD = "888888";

    /**
     * 加密
     * @param rawPassword
     * @return
     */
    public static String encode(String rawPassword) {
        return encoder.encode(rawPassword);
    }

    /**
     * 校验
     * @param rawPassword 原密码
     * @param encodedPassword 加密的密码
     * @return
     */
    public static boolean verify(String rawPassword, String encodedPassword) {
        return encoder.matches(rawPassword, encodedPassword);
    }

    public static void main(String[] args) {
        String pw = "admin123432423";
        StopWatch watch = new StopWatch();

        watch.start();
        String admin = PasswordUtil.encode(pw);
        System.out.println(admin);
        watch.stop();
        System.out.println(watch.getTime());

        watch.reset();
        watch.start();
        System.out.println(verify(pw,admin));
        watch.stop();
        System.out.println(watch.getTime());
    }
}

