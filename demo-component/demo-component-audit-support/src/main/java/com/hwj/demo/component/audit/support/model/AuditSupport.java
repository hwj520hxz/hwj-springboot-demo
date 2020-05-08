package com.hwj.demo.component.audit.support.model;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class AuditSupport {
    /**
     * ID
     */
    @NotNull(message = "ID不能为空！")
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
    @NotNull(message = "记录创建时间不能为空！")
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
    @NotNull(message = "记录最后修改时间不能为空！")
    private LocalDateTime recModifiedTime;

    /**
     * 记录版本号
     */
    @NotNull(message = "记录版本号不能为空！")
    @Max(value=2147483647,message="记录版本号的长度不能大于{value}！")
    @Min(value=1,message="记录版本号的长度不能小于{value}！")
    private Integer recVersion;

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
