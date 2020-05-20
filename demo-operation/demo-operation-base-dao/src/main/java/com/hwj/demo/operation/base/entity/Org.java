package com.hwj.demo.operation.base.entity;

import com.hwj.demo.component.audit.support.model.AuditSupport;
import java.io.Serializable;
import java.time.LocalDate;
import javax.validation.constraints.Size;

/**
 * oper_org
 */
public class Org extends AuditSupport implements Serializable {
    /**
     * 编制日期
     */
    private LocalDate recDate;

    /**
     * 编制人
     */
    @Size(min = 0, max = 255 , message = "编制人的长度必须大于{min}，小于{max}！")
    private String recAuthor;

    /**
     * 组织结构类型(销售组织、实施组织)
     */
    @Size(min = 0, max = 64 , message = "组织结构类型(销售组织、实施组织)的长度必须大于{min}，小于{max}！")
    private String orgType;

    /**
     * 组织编码
     */
    @Size(min = 0, max = 64 , message = "组织编码的长度必须大于{min}，小于{max}！")
    private String orgCode;

    /**
     * 组织名称
     */
    @Size(min = 0, max = 120 , message = "组织名称的长度必须大于{min}，小于{max}！")
    private String orgName;

    /**
     * 上级组织id
     */
    private Long parentId;

    /**
     * 组织描述
     */
    @Size(min = 0, max = 255 , message = "组织描述的长度必须大于{min}，小于{max}！")
    private String description;

    private static final long serialVersionUID = 1L;

    public LocalDate getRecDate() {
        return recDate;
    }

    public void setRecDate(LocalDate recDate) {
        this.recDate = recDate;
    }

    public String getRecAuthor() {
        return recAuthor;
    }

    public void setRecAuthor(String recAuthor) {
        this.recAuthor = recAuthor;
    }

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
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

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
        Org other = (Org) that;
        return (this.getRecId() == null ? other.getRecId() == null : this.getRecId().equals(other.getRecId()))
            && (this.getRecCreatedBy() == null ? other.getRecCreatedBy() == null : this.getRecCreatedBy().equals(other.getRecCreatedBy()))
            && (this.getRecCreatedOrg() == null ? other.getRecCreatedOrg() == null : this.getRecCreatedOrg().equals(other.getRecCreatedOrg()))
            && (this.getRecCreatedTime() == null ? other.getRecCreatedTime() == null : this.getRecCreatedTime().equals(other.getRecCreatedTime()))
            && (this.getRecModifiedBy() == null ? other.getRecModifiedBy() == null : this.getRecModifiedBy().equals(other.getRecModifiedBy()))
            && (this.getRecModifiedOrg() == null ? other.getRecModifiedOrg() == null : this.getRecModifiedOrg().equals(other.getRecModifiedOrg()))
            && (this.getRecModifiedTime() == null ? other.getRecModifiedTime() == null : this.getRecModifiedTime().equals(other.getRecModifiedTime()))
            && (this.getRecVersion() == null ? other.getRecVersion() == null : this.getRecVersion().equals(other.getRecVersion()))
            && (this.getRecDate() == null ? other.getRecDate() == null : this.getRecDate().equals(other.getRecDate()))
            && (this.getRecAuthor() == null ? other.getRecAuthor() == null : this.getRecAuthor().equals(other.getRecAuthor()))
            && (this.getOrgType() == null ? other.getOrgType() == null : this.getOrgType().equals(other.getOrgType()))
            && (this.getOrgCode() == null ? other.getOrgCode() == null : this.getOrgCode().equals(other.getOrgCode()))
            && (this.getOrgName() == null ? other.getOrgName() == null : this.getOrgName().equals(other.getOrgName()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
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
        result = prime * result + ((getRecDate() == null) ? 0 : getRecDate().hashCode());
        result = prime * result + ((getRecAuthor() == null) ? 0 : getRecAuthor().hashCode());
        result = prime * result + ((getOrgType() == null) ? 0 : getOrgType().hashCode());
        result = prime * result + ((getOrgCode() == null) ? 0 : getOrgCode().hashCode());
        result = prime * result + ((getOrgName() == null) ? 0 : getOrgName().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }
}