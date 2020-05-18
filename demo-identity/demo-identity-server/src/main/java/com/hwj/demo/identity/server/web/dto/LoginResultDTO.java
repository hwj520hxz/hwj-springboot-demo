package com.hwj.demo.identity.server.web.dto;

import java.time.LocalDateTime;

/**
 * 登录结果
 * @author DoubleV
 * @date 2020/4/10
 */
public class LoginResultDTO {
    /**
     * jwt token
     */
    private String token;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }
}
