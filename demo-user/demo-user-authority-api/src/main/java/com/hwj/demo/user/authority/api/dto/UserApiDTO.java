package com.hwj.demo.user.authority.api.dto;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户信息DTO
 */
public class UserApiDTO implements Serializable {

    /**
     *   ID
     */
    private Long recId;
    /**
     *   账号
     */
    private String code;
    /**
     *   姓名
     */
    private String name;
    /**
     *   密码
     */
    private String password;
    /**
     *   是否内置用户
     */
    private Boolean internal;
    /**
     *   手机号
     */
    private String mobile;
    /**
     *   邮箱
     */
    private String email;
    /**
     *   是否启用
     */
    private Boolean enabled;
    /**
     *   记录创建人
     */
    private String recCreatedBy;
    /**
     *   记录创建机构
     */
    private String recCreatedOrg;
    /**
     *   记录创建时间
     */
    private LocalDateTime recCreatedTime;
    /**
     *   记录最后修改人
     */
    private String recModifiedBy;
    /**
     *   记录最后修改机构
     */
    private String recModifiedOrg;
    /**
     *   记录最后修改时间
     */
    private LocalDateTime recModifiedTime;
    /**
     *   记录版本号
     */
    private Integer recVersion;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
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

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
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

    public LocalDateTime getRecCreatedTime() {
        return recCreatedTime;
    }

    public void setRecCreatedTime(LocalDateTime recCreatedTime) {
        this.recCreatedTime = recCreatedTime;
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

    public LocalDateTime getRecModifiedTime() {
        return recModifiedTime;
    }

    public void setRecModifiedTime(LocalDateTime recModifiedTime) {
        this.recModifiedTime = recModifiedTime;
    }

    public Integer getRecVersion() {
        return recVersion;
    }

    public void setRecVersion(Integer recVersion) {
        this.recVersion = recVersion;
    }
}
