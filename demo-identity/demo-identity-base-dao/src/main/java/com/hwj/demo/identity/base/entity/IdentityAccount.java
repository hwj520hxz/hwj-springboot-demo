package com.hwj.demo.identity.base.entity;

import com.hwj.demo.component.audit.support.model.AuditSupport;
import java.io.Serializable;
import java.time.LocalDateTime;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

/**
 * iden_identity_account
 */
public class IdentityAccount extends AuditSupport implements Serializable {
    /**
     * 身份账号
     */
    @Size(min = 0, max = 50 , message = "身份账号的长度必须大于{min}，小于{max}！")
    private String accout;

    /**
     * 身份账号名称
     */
    @Size(min = 0, max = 100 , message = "身份账号名称的长度必须大于{min}，小于{max}！")
    private String accoutName;

    /**
     * 密码摘要
     */
    @NotEmpty(message = "密码摘要不能为空！")
    @Size(min = 0, max = 200 , message = "密码摘要的长度必须大于{min}，小于{max}！")
    private String password;

    /**
     * 手机号码
     */
    @Size(min = 0, max = 20 , message = "手机号码的长度必须大于{min}，小于{max}！")
    private String mobile;

    /**
     * 邮箱
     */
    @Size(min = 0, max = 50 , message = "邮箱的长度必须大于{min}，小于{max}！")
    private String email;

    /**
     * 是否启用
     */
    private Boolean enabled;

    /**
     * 最后登录时间
     */
    private LocalDateTime lastLoginTime;

    /**
     * 状态：0正常 1锁定
     */
    private Boolean lockStatus;

    /**
     * 登录错误次数
     */
    @NotEmpty(message = "登录错误次数不能为空！")
    @Max(value=2147483647,message="登录错误次数的长度不能大于{value}！")
    @Min(value=0,message="登录错误次数的长度不能小于{value}！")
    private Integer errorCount;

    /**
     * 单位标识
     */
    @Size(min = 0, max = 64 , message = "单位标识的长度必须大于{min}，小于{max}！")
    private String agencyId;

    @NotEmpty(message = "不能为空！")
    @Size(min = 0, max = 2 , message = "的长度必须大于{min}，小于{max}！")
    private String userType;

    /**
     * 解锁时间
     */
    private LocalDateTime unlockTime;

    private static final long serialVersionUID = 1L;

    public String getAccout() {
        return accout;
    }

    public void setAccout(String accout) {
        this.accout = accout;
    }

    public String getAccoutName() {
        return accoutName;
    }

    public void setAccoutName(String accoutName) {
        this.accoutName = accoutName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public LocalDateTime getLastLoginTime() {
        return lastLoginTime;
    }

    public void setLastLoginTime(LocalDateTime lastLoginTime) {
        this.lastLoginTime = lastLoginTime;
    }

    public Boolean getLockStatus() {
        return lockStatus;
    }

    public void setLockStatus(Boolean lockStatus) {
        this.lockStatus = lockStatus;
    }

    public Integer getErrorCount() {
        return errorCount;
    }

    public void setErrorCount(Integer errorCount) {
        this.errorCount = errorCount;
    }

    public String getAgencyId() {
        return agencyId;
    }

    public void setAgencyId(String agencyId) {
        this.agencyId = agencyId;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public LocalDateTime getUnlockTime() {
        return unlockTime;
    }

    public void setUnlockTime(LocalDateTime unlockTime) {
        this.unlockTime = unlockTime;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        IdentityAccount other = (IdentityAccount) that;
        return (this.getRecId() == null ? other.getRecId() == null : this.getRecId().equals(other.getRecId()))
            && (this.getRecCreatedBy() == null ? other.getRecCreatedBy() == null : this.getRecCreatedBy().equals(other.getRecCreatedBy()))
            && (this.getRecCreatedOrg() == null ? other.getRecCreatedOrg() == null : this.getRecCreatedOrg().equals(other.getRecCreatedOrg()))
            && (this.getRecCreatedTime() == null ? other.getRecCreatedTime() == null : this.getRecCreatedTime().equals(other.getRecCreatedTime()))
            && (this.getRecModifiedBy() == null ? other.getRecModifiedBy() == null : this.getRecModifiedBy().equals(other.getRecModifiedBy()))
            && (this.getRecModifiedOrg() == null ? other.getRecModifiedOrg() == null : this.getRecModifiedOrg().equals(other.getRecModifiedOrg()))
            && (this.getRecModifiedTime() == null ? other.getRecModifiedTime() == null : this.getRecModifiedTime().equals(other.getRecModifiedTime()))
            && (this.getRecVersion() == null ? other.getRecVersion() == null : this.getRecVersion().equals(other.getRecVersion()))
            && (this.getAccout() == null ? other.getAccout() == null : this.getAccout().equals(other.getAccout()))
            && (this.getAccoutName() == null ? other.getAccoutName() == null : this.getAccoutName().equals(other.getAccoutName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
            && (this.getLastLoginTime() == null ? other.getLastLoginTime() == null : this.getLastLoginTime().equals(other.getLastLoginTime()))
            && (this.getLockStatus() == null ? other.getLockStatus() == null : this.getLockStatus().equals(other.getLockStatus()))
            && (this.getErrorCount() == null ? other.getErrorCount() == null : this.getErrorCount().equals(other.getErrorCount()))
            && (this.getAgencyId() == null ? other.getAgencyId() == null : this.getAgencyId().equals(other.getAgencyId()))
            && (this.getUserType() == null ? other.getUserType() == null : this.getUserType().equals(other.getUserType()))
            && (this.getUnlockTime() == null ? other.getUnlockTime() == null : this.getUnlockTime().equals(other.getUnlockTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecId() == null) ? 0 : getRecId().hashCode());
        result = prime * result + ((getRecCreatedBy() == null) ? 0 : getRecCreatedBy().hashCode());
        result = prime * result + ((getRecCreatedOrg() == null) ? 0 : getRecCreatedOrg().hashCode());
        result = prime * result + ((getRecCreatedTime() == null) ? 0 : getRecCreatedTime().hashCode());
        result = prime * result + ((getRecModifiedBy() == null) ? 0 : getRecModifiedBy().hashCode());
        result = prime * result + ((getRecModifiedOrg() == null) ? 0 : getRecModifiedOrg().hashCode());
        result = prime * result + ((getRecModifiedTime() == null) ? 0 : getRecModifiedTime().hashCode());
        result = prime * result + ((getRecVersion() == null) ? 0 : getRecVersion().hashCode());
        result = prime * result + ((getAccout() == null) ? 0 : getAccout().hashCode());
        result = prime * result + ((getAccoutName() == null) ? 0 : getAccoutName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
        result = prime * result + ((getLastLoginTime() == null) ? 0 : getLastLoginTime().hashCode());
        result = prime * result + ((getLockStatus() == null) ? 0 : getLockStatus().hashCode());
        result = prime * result + ((getErrorCount() == null) ? 0 : getErrorCount().hashCode());
        result = prime * result + ((getAgencyId() == null) ? 0 : getAgencyId().hashCode());
        result = prime * result + ((getUserType() == null) ? 0 : getUserType().hashCode());
        result = prime * result + ((getUnlockTime() == null) ? 0 : getUnlockTime().hashCode());
        return result;
    }
}