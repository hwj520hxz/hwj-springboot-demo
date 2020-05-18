package com.hwj.demo.user.authorize.integrated.shiro;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：线程存储用户ID
 */
public class UserContext {

    private static ThreadLocal<Long> user = new ThreadLocal<>();

    public static void set(Long userId){
        user.set(userId);
    }

    public static Long get(){
        return user.get();
    }

    public static void remove(){
        user.remove();
    }
}
