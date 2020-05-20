package com.hwj.demo.identity.server.web.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.LocalDateTime;

public class IdentityAttributeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 属性描述
     */
    private String attributeDescription;

    /**
     * 属性键
     */
    private String attributeKey;

    /**
     * 属性值
     */
    private String attributeValue;

    /**
     * ID
     */
    private Long recId;

    /**
     * 记录创建人
     */
    @Size(min = 0, max = 64 , message = "记录创建人的长度必须大于{min}，小于{max}！")
    private String recCreatedBy;

    /**
     * 记录创建机构
     */
    @Size(min = 0, max = 64 , message = "记录创建机构的长度必须大于{min}，小于{max}！")
    private String recCreatedOrg;

    /**
     * 记录创建时间
     */
    private LocalDateTime recCreatedTime;

    /**
     * 记录最后修改人
     */
    @Size(min = 0, max = 64 , message = "记录最后修改人的长度必须大于{min}，小于{max}！")
    private String recModifiedBy;

    /**
     * 记录最后修改机构
     */
    @Size(min = 0, max = 64 , message = "记录最后修改机构的长度必须大于{min}，小于{max}！")
    private String recModifiedOrg;

    /**
     * 记录最后修改时间
     */
    private LocalDateTime recModifiedTime;

    /**
     * 记录版本号
     */
    @Max(value=2147483647,message="记录版本号的长度不能大于{value}！")
    @Min(value=-2147483648,message="记录版本号的长度不能小于{value}！")
    private Integer recVersion;

    public String getAttributeDescription() {
        return attributeDescription;
    }

    public void setAttributeDescription(String attributeDescription) {
        this.attributeDescription = attributeDescription;
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

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
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
