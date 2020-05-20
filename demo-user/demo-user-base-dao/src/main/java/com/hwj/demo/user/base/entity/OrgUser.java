package com.hwj.demo.user.base.entity;

import com.hwj.demo.component.audit.support.model.AuditSupport;
import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * oper_org_user
 */
public class OrgUser extends AuditSupport implements Serializable {
    /**
     * 用户id
     */
    private Long userId;

    /**
     * 组织id
     */
    private Long orgId;

    /**
     * 用户账号
     */
    @Size(min = 0, max = 50 , message = "用户账号的长度必须大于{min}，小于{max}！")
    private String userCode;

    /**
     * 用户姓名
     */
    @Size(min = 0, max = 200 , message = "用户姓名的长度必须大于{min}，小于{max}！")
    private String userName;

    /**
     * 用户手机号
     */
    @Size(min = 0, max = 20 , message = "用户手机号的长度必须大于{min}，小于{max}！")
    private String userMobile;

    /**
     * 用户邮箱
     */
    @Size(min = 0, max = 50 , message = "用户邮箱的长度必须大于{min}，小于{max}！")
    private String userEmail;

    private static final long serialVersionUID = 1L;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getUserCode() {
        return userCode;
    }

    public void setUserCode(String userCode) {
        this.userCode = userCode;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserMobile() {
        return userMobile;
    }

    public void setUserMobile(String userMobile) {
        this.userMobile = userMobile;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
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
        OrgUser other = (OrgUser) that;
        return (this.getRecId() == null ? other.getRecId() == null : this.getRecId().equals(other.getRecId()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getOrgId() == null ? other.getOrgId() == null : this.getOrgId().equals(other.getOrgId()))
            && (this.getUserCode() == null ? other.getUserCode() == null : this.getUserCode().equals(other.getUserCode()))
            && (this.getUserName() == null ? other.getUserName() == null : this.getUserName().equals(other.getUserName()))
            && (this.getUserMobile() == null ? other.getUserMobile() == null : this.getUserMobile().equals(other.getUserMobile()))
            && (this.getUserEmail() == null ? other.getUserEmail() == null : this.getUserEmail().equals(other.getUserEmail()))
            && (this.getRecCreatedBy() == null ? other.getRecCreatedBy() == null : this.getRecCreatedBy().equals(other.getRecCreatedBy()))
            && (this.getRecCreatedOrg() == null ? other.getRecCreatedOrg() == null : this.getRecCreatedOrg().equals(other.getRecCreatedOrg()))
            && (this.getRecCreatedTime() == null ? other.getRecCreatedTime() == null : this.getRecCreatedTime().equals(other.getRecCreatedTime()))
            && (this.getRecModifiedBy() == null ? other.getRecModifiedBy() == null : this.getRecModifiedBy().equals(other.getRecModifiedBy()))
            && (this.getRecModifiedOrg() == null ? other.getRecModifiedOrg() == null : this.getRecModifiedOrg().equals(other.getRecModifiedOrg()))
            && (this.getRecModifiedTime() == null ? other.getRecModifiedTime() == null : this.getRecModifiedTime().equals(other.getRecModifiedTime()))
            && (this.getRecVersion() == null ? other.getRecVersion() == null : this.getRecVersion().equals(other.getRecVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRecId() == null) ? 0 : getRecId().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getOrgId() == null) ? 0 : getOrgId().hashCode());
        result = prime * result + ((getUserCode() == null) ? 0 : getUserCode().hashCode());
        result = prime * result + ((getUserName() == null) ? 0 : getUserName().hashCode());
        result = prime * result + ((getUserMobile() == null) ? 0 : getUserMobile().hashCode());
        result = prime * result + ((getUserEmail() == null) ? 0 : getUserEmail().hashCode());
        result = prime * result + ((getRecCreatedBy() == null) ? 0 : getRecCreatedBy().hashCode());
        result = prime * result + ((getRecCreatedOrg() == null) ? 0 : getRecCreatedOrg().hashCode());
        result = prime * result + ((getRecCreatedTime() == null) ? 0 : getRecCreatedTime().hashCode());
        result = prime * result + ((getRecModifiedBy() == null) ? 0 : getRecModifiedBy().hashCode());
        result = prime * result + ((getRecModifiedOrg() == null) ? 0 : getRecModifiedOrg().hashCode());
        result = prime * result + ((getRecModifiedTime() == null) ? 0 : getRecModifiedTime().hashCode());
        result = prime * result + ((getRecVersion() == null) ? 0 : getRecVersion().hashCode());
        return result;
    }
}