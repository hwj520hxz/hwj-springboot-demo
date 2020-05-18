package com.hwj.demo.identity.server.web;

import com.hwj.demo.component.encryption.jwt.JWTUtil;
import com.hwj.demo.identity.base.entity.IdentityAccount;
import com.hwj.demo.identity.server.service.IIdentityAccountService;
import com.hwj.demo.identity.server.web.dto.LoginResultDTO;
import com.hwj.demo.identity.server.web.dto.UserLoginDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@RestController
@RequestMapping("/demo/identity")
public class LoginController {

    @Autowired
    private IIdentityAccountService identityAccountService;

    /**
     * 运营支撑用户登录
     *
     * @param user
     * @return
     */
    @PostMapping("login")

    public LoginResultDTO login(@Valid @RequestBody UserLoginDTO user) {
        IdentityAccount identityAccount = identityAccountService.checkUser(user.getCode(), user.getPassword(), IIdentityAccountService.USER_TYPE1);
        LoginResultDTO result = new LoginResultDTO();
        result.setToken(JWTUtil.sign(identityAccount.getRecId(), user.getCode(), identityAccount.getPassword()));
        result.setLastLoginTime(identityAccount.getLastLoginTime());
        return result;
    }
}
