package com.hwj.demo.identity.server.service;

import com.hwj.demo.identity.base.entity.IdentityAccount;
import com.hwj.demo.identity.base.entity.IdentityAttr;

import java.util.List;

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

    void updateLoginTime(Long recId);

    IdentityAccount save(IdentityAccount identityAccount, List<IdentityAttr> attrList, String type);

    /**
     * 保存身份信息
     * @param identityAccount
     * @param attrList
     * @return
     */
    Long saveIdentityAccount(IdentityAccount identityAccount, List<IdentityAttr> attrList, String type);


    /**
     * 删除用户
     * @param accountOpenId
     */
    void deleteUser(Long accountOpenId);
}
