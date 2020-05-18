package com.hwj.demo.user.authorize.integrated.shiro;

import org.apache.shiro.authc.AuthenticationToken;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：获取用户提交的身份和凭据
 * Shiro会调用CredentialsMatcher对象的doCredentialsMatch方法对AuthenticationInfo对象和AuthenticationToken进行匹配
 */
public class JWTToken implements AuthenticationToken {

    private String token;

    public JWTToken(String token) {
        this.token = token;
    }

    @Override
    public Object getPrincipal() {
        return token;
    }

    @Override
    public Object getCredentials() {
        return token;
    }
}
