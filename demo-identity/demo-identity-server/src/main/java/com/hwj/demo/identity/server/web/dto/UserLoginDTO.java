package com.hwj.demo.identity.server.web.dto;

import javax.validation.constraints.NotEmpty;

/**
 * 用户登录DTO
 */
public class UserLoginDTO {

    private String agencyMainAccount;

    @NotEmpty(message = "用户名不能为空")
    private String code;

    @NotEmpty(message = "密码不能为空")
    private String password;

    public String getAgencyMainAccount() {
        return agencyMainAccount;
    }

    public void setAgencyMainAccount(String agencyMainAccount) {
        this.agencyMainAccount = agencyMainAccount;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
