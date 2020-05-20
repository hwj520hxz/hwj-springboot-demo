package com.hwj.demo.operation.server.web.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @program: nontax3-saas
 * @description: 组织管理新增DTO
 * @author: ydr
 * @create: 2020-04-20 15:03
 **/
public class OrgDTO {

    /**
     * 上级组织ID
     */
    private Long parentId;
    /**
     * 组织编码
     */
    @NotBlank(message = "组织编码不能为空")
    @Size(min = 1, max = 64, message = "组织编码长度应在1~64字符")
    private String orgCode;

    /**
     * 组织名称
     */
    @NotBlank(message = "组织名称不能为空")
    @Size(min = 1, max = 120, message = "组织名称长度应在1~120字符")
    private String orgName;

    /**
     * 组织类型
     */
    private String orgType;

    /**
     * 组织描述
     */
    private String description;


    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
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

    public String getOrgType() {
        return orgType;
    }

    public void setOrgType(String orgType) {
        this.orgType = orgType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
