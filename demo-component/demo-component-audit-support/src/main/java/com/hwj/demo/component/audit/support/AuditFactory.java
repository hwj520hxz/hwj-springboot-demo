package com.hwj.demo.component.audit.support;

import com.hwj.demo.component.audit.support.model.AuditSupport;
import com.hwj.demo.component.audit.support.model.AuditUser;

import java.time.LocalDateTime;
import java.util.Collection;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class AuditFactory {

    private static final AuditUser SYSTEM = new AuditUser("system", "system");

    public static String getRecordCreatedBy() {
        return AuditContext.get().orElse(SYSTEM).getUserId();
    }

    public static String getRecordCreatedOrg() {
        return AuditContext.get().orElse(SYSTEM).getOrgId();
    }

    public static LocalDateTime getRecordCreatedTime() {
        return LocalDateTime.now();
    }

    public static String getRecordModifiedBy() {
        return AuditContext.get().orElse(SYSTEM).getUserId();
    }

    public static String getRecordModifiedOrg() {
        return AuditContext.get().orElse(SYSTEM).getOrgId();
    }

    public static LocalDateTime getRecordModifiedTime() {
        return LocalDateTime.now();
    }

    public static void fill(AuditSupport auditSupport) {
        auditSupport.setRecCreatedBy(getRecordCreatedBy());
        auditSupport.setRecCreatedOrg(getRecordCreatedOrg());
        auditSupport.setRecCreatedTime(getRecordCreatedTime());
        auditSupport.setRecModifiedBy(getRecordModifiedBy());
        auditSupport.setRecModifiedOrg(getRecordModifiedOrg());
        auditSupport.setRecModifiedTime(getRecordModifiedTime());
        auditSupport.setRecVersion(1);
    }

    public static void fill(Collection<? extends AuditSupport> records) {
        String createBy = getRecordCreatedBy();
        String createByOrg = getRecordCreatedOrg();
        LocalDateTime createTime = getRecordCreatedTime();
        String modifiedBy = getRecordModifiedBy();
        String modifiedByOrg = getRecordModifiedOrg();
        LocalDateTime modifiedTime = getRecordModifiedTime();
        records.forEach(auditSupport -> {
            auditSupport.setRecCreatedBy(createBy);
            auditSupport.setRecCreatedOrg(createByOrg);
            auditSupport.setRecCreatedTime(createTime);
            auditSupport.setRecModifiedBy(modifiedBy);
            auditSupport.setRecModifiedOrg(modifiedByOrg);
            auditSupport.setRecModifiedTime(modifiedTime);
            auditSupport.setRecVersion(1);
        });
    }
}
