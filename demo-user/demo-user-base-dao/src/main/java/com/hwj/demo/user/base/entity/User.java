package com.hwj.demo.user.base.entity;

import com.hwj.demo.component.audit.support.model.AuditSupport;
import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * auth_user
 */
public class User extends AuditSupport implements Serializable {
    /**
     * 账号
     */
    @Size(min = 0, max = 50 , message = "账号的长度必须大于{min}，小于{max}！")
    private String code;

    /**
     * 姓名
     */
    @Size(min = 0, max = 200 , message = "姓名的长度必须大于{min}，小于{max}！")
    private String name;

    /**
     * 密码
     */
    @Size(min = 0, max = 200 , message = "密码的长度必须大于{min}，小于{max}！")
    private String password;

    /**
     * 是否内置
     */
    private Boolean internal;

    /**
     * 手机号
     */
    @Size(min = 0, max = 20 , message = "手机号的长度必须大于{min}，小于{max}！")
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

    private static final long serialVersionUID = 1L;

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
        User other = (User) that;
        return (this.getRecId() == null ? other.getRecId() == null : this.getRecId().equals(other.getRecId()))
            && (this.getCode() == null ? other.getCode() == null : this.getCode().equals(other.getCode()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getInternal() == null ? other.getInternal() == null : this.getInternal().equals(other.getInternal()))
            && (this.getMobile() == null ? other.getMobile() == null : this.getMobile().equals(other.getMobile()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getEnabled() == null ? other.getEnabled() == null : this.getEnabled().equals(other.getEnabled()))
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
        result = prime * result + ((getCode() == null) ? 0 : getCode().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getInternal() == null) ? 0 : getInternal().hashCode());
        result = prime * result + ((getMobile() == null) ? 0 : getMobile().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getEnabled() == null) ? 0 : getEnabled().hashCode());
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
