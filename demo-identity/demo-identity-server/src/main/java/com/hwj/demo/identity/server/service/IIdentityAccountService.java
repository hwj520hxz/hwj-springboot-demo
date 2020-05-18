package com.hwj.demo.identity.server.service;

import com.hwj.demo.identity.base.entity.IdentityAccount;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface IIdentityAccountService {

    String USER_TYPE1 = "1";

    String USER_TYPE2 = "2";

    /**
     * 校验运营平台用户
     * @param code 账号
     * @param password 密码
     * @param userType 用户类型
     * @return
     */
    IdentityAccount checkUser(String code, String password, String userType);
}
