package com.hwj.demo.identity.server.service;

import com.hwj.demo.identity.base.entity.IdentityAccount;
import org.springframework.stereotype.Service;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Service
public class IdentityAccountService implements IIdentityAccountService {
    @Override
    public IdentityAccount checkUser(String code, String password, String userType) {
        return null;
    }
}
