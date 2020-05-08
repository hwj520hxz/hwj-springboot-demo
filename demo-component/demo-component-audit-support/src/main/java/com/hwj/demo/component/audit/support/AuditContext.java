package com.hwj.demo.component.audit.support;

import com.hwj.demo.component.audit.support.model.AuditUser;

import java.util.Optional;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class AuditContext {
    private static ThreadLocal<AuditUser> auditLocal = new ThreadLocal<>();

    public static void set(AuditUser audit) {
        auditLocal.set(audit);
    }

    public static void remove() {
        auditLocal.remove();
    }

    public static Optional<AuditUser> get() {
        return Optional.ofNullable(auditLocal.get());
    }

}
