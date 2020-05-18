package com.hwj.demo.identity.dto;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.List;

public class IdentityDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 账号
     */
    @NotEmpty(message = "用户账号不能为空")
    private String account;

    /**
     * 账号名
     */
    private String accountName;

    /**
     * 密码
     */
    @NotEmpty(message = "用户密码不能为空")
    private String password;

    /**
     * 手机号码
     */
    private String mobile;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 记录创建人
     */
    private String recCreatedBy;

    /**
     * 记录创建机构
     */
    private String recCreatedOrg;

    /**
     * 记录最后修改人
     */
    private String recModifiedBy;

    /**
     * 记录最后修改机构
     */
    private String recModifiedOrg;

    /**
     * 单位标识
     */
    private String agenId;

    /**
     * 属性列表
     */
    private List<IdentityAttributeDTO> attributes;

    public List<IdentityAttributeDTO> getAttributes() {
        return attributes;
    }

    public void setAttributes(List<IdentityAttributeDTO> attributes) {
        this.attributes = attributes;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRecCreatedBy() {
        return recCreatedBy;
    }

    public void setRecCreatedBy(String recCreatedBy) {
        this.recCreatedBy = recCreatedBy;
    }

    public String getRecCreatedOrg() {
        return recCreatedOrg;
    }

    public void setRecCreatedOrg(String recCreatedOrg) {
        this.recCreatedOrg = recCreatedOrg;
    }

    public String getRecModifiedBy() {
        return recModifiedBy;
    }

    public void setRecModifiedBy(String recModifiedBy) {
        this.recModifiedBy = recModifiedBy;
    }

    public String getRecModifiedOrg() {
        return recModifiedOrg;
    }

    public void setRecModifiedOrg(String recModifiedOrg) {
        this.recModifiedOrg = recModifiedOrg;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAgenId() {
        return agenId;
    }

    public void setAgenId(String agenId) {
        this.agenId = agenId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }
}
