package com.hwj.demo.identity.base.entity;

import com.hwj.demo.component.audit.support.model.AuditSupport;
import java.io.Serializable;
import javax.validation.constraints.Size;

/**
 * iden_identity_attr
 */
public class IdentityAttr extends AuditSupport implements Serializable {
    /**
     * 身份ID
     */
    private Long identityId;

    /**
     * 属性键
     */
    @Size(min = 0, max = 50 , message = "属性键的长度必须大于{min}，小于{max}！")
    private String attributeKey;

    /**
     * 属性值
     */
    @Size(min = 0, max = 50 , message = "属性值的长度必须大于{min}，小于{max}！")
    private String attributeValue;

    /**
     * 属性描述
     */
    @Size(min = 0, max = 50 , message = "属性描述的长度必须大于{min}，小于{max}！")
    private String attributeDescription;

    private static final long serialVersionUID = 1L;

    public Long getIdentityId() {
        return identityId;
    }

    public void setIdentityId(Long identityId) {
        this.identityId = identityId;
    }

    public String getAttributeKey() {
        return attributeKey;
    }

    public void setAttributeKey(String attributeKey) {
        this.attributeKey = attributeKey;
    }

    public String getAttributeValue() {
        return attributeValue;
    }

    public void setAttributeValue(String attributeValue) {
        this.attributeValue = attributeValue;
    }

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
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
        IdentityAttr other = (IdentityAttr) that;
        return (this.getRecId() == null ? other.getRecId() == null : this.getRecId().equals(other.getRecId()))
            && (this.getRecCreatedBy() == null ? other.getRecCreatedBy() == null : this.getRecCreatedBy().equals(other.getRecCreatedBy()))
            && (this.getRecCreatedOrg() == null ? other.getRecCreatedOrg() == null : this.getRecCreatedOrg().equals(other.getRecCreatedOrg()))
            && (this.getRecCreatedTime() == null ? other.getRecCreatedTime() == null : this.getRecCreatedTime().equals(other.getRecCreatedTime()))
            && (this.getRecModifiedBy() == null ? other.getRecModifiedBy() == null : this.getRecModifiedBy().equals(other.getRecModifiedBy()))
            && (this.getRecModifiedOrg() == null ? other.getRecModifiedOrg() == null : this.getRecModifiedOrg().equals(other.getRecModifiedOrg()))
            && (this.getRecModifiedTime() == null ? other.getRecModifiedTime() == null : this.getRecModifiedTime().equals(other.getRecModifiedTime()))
            && (this.getRecVersion() == null ? other.getRecVersion() == null : this.getRecVersion().equals(other.getRecVersion()))
            && (this.getIdentityId() == null ? other.getIdentityId() == null : this.getIdentityId().equals(other.getIdentityId()))
            && (this.getAttributeKey() == null ? other.getAttributeKey() == null : this.getAttributeKey().equals(other.getAttributeKey()))
            && (this.getAttributeValue() == null ? other.getAttributeValue() == null : this.getAttributeValue().equals(other.getAttributeValue()))
            && (this.getAttributeDescription() == null ? other.getAttributeDescription() == null : this.getAttributeDescription().equals(other.getAttributeDescription()));
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
        result = prime * result + ((getIdentityId() == null) ? 0 : getIdentityId().hashCode());
        result = prime * result + ((getAttributeKey() == null) ? 0 : getAttributeKey().hashCode());
        result = prime * result + ((getAttributeValue() == null) ? 0 : getAttributeValue().hashCode());
        result = prime * result + ((getAttributeDescription() == null) ? 0 : getAttributeDescription().hashCode());
        return result;
    }
}