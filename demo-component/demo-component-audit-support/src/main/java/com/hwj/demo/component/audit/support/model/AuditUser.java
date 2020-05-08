package com.hwj.demo.component.audit.support.model;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class AuditUser {

    /**
     * 用户id
     */
    private String userId;

    /**
     * 机构id
     */
    private String orgId;

    public AuditUser(String userId, String orgId) {
        this.userId = userId;
        this.orgId = orgId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }
}
