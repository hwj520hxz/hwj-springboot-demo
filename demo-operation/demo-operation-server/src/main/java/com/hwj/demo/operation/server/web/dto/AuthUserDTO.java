package com.hwj.demo.operation.server.web.dto;

import java.time.LocalDateTime;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class AuthUserDTO {
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
     *   类型：1超级管理员 2：普通用户
     */
    private Integer userType;
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
     *   微信openid
     */
    private String wechatOpenid;
    /**
     *   最后登录时间
     */
    private LocalDateTime lastLoginTime;
    /**
     *   状态：0锁定 1正常
     */
    private Integer status;
    /**
     *   登录错误次数
     */
    private Integer errorCount;
    /**
     *   所属应用
     */
    private Long appId;
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
    /**
     *  组织机构ID
     */
    private Long orgId;
    /**
     * 机构编码
     */
    private String orgCode;
    /**
     * 机构名称
     */
    private String orgName;

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

    public Integer getUserType() {
        return userType;
    }

    public void setUserType(Integer userType) {
        this.userType = userType;
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

    public String getWechatOpenid() {
        return wechatOpenid;
    }

    public void setWechatOpenid(String wechatOpenid) {
        this.wechatOpenid = wechatOpenid;
    }

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public Long getAppId() {
        return appId;
    }

    public void setAppId(Long appId) {
        this.appId = appId;
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

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }
}
