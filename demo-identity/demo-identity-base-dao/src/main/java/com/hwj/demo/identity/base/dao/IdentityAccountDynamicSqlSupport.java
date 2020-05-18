package com.hwj.demo.identity.base.dao;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class IdentityAccountDynamicSqlSupport {
    public static final IdentityAccount identityAccount = new IdentityAccount();

    /**
     * 主键id
     */
    public static final SqlColumn<Long> recId = identityAccount.recId;

    /**
     * 记录创建人
     */
    public static final SqlColumn<String> recCreatedBy = identityAccount.recCreatedBy;

    /**
     * 记录创建机构
     */
    public static final SqlColumn<String> recCreatedOrg = identityAccount.recCreatedOrg;

    /**
     * 记录创建时间
     */
    public static final SqlColumn<LocalDateTime> recCreatedTime = identityAccount.recCreatedTime;

    /**
     * 记录最后修改人
     */
    public static final SqlColumn<String> recModifiedBy = identityAccount.recModifiedBy;

    /**
     * 记录最后修改机构
     */
    public static final SqlColumn<String> recModifiedOrg = identityAccount.recModifiedOrg;

    /**
     * 记录最后修改时间
     */
    public static final SqlColumn<LocalDateTime> recModifiedTime = identityAccount.recModifiedTime;

    /**
     * 记录版本号
     */
    public static final SqlColumn<Integer> recVersion = identityAccount.recVersion;

    /**
     * 身份账号
     */
    public static final SqlColumn<String> accout = identityAccount.accout;

    /**
     * 身份账号名称
     */
    public static final SqlColumn<String> accoutName = identityAccount.accoutName;

    /**
     * 密码摘要
     */
    public static final SqlColumn<String> password = identityAccount.password;

    /**
     * 手机号码
     */
    public static final SqlColumn<String> mobile = identityAccount.mobile;

    /**
     * 邮箱
     */
    public static final SqlColumn<String> email = identityAccount.email;

    /**
     * 是否启用
     */
    public static final SqlColumn<Boolean> enabled = identityAccount.enabled;

    /**
     * 最后登录时间
     */
    public static final SqlColumn<LocalDateTime> lastLoginTime = identityAccount.lastLoginTime;

    /**
     * 状态：0正常 1锁定
     */
    public static final SqlColumn<Boolean> lockStatus = identityAccount.lockStatus;

    /**
     * 登录错误次数
     */
    public static final SqlColumn<Integer> errorCount = identityAccount.errorCount;

    /**
     * 单位标识
     */
    public static final SqlColumn<String> agencyId = identityAccount.agencyId;

    public static final SqlColumn<String> userType = identityAccount.userType;

    /**
     * 解锁时间
     */
    public static final SqlColumn<LocalDateTime> unlockTime = identityAccount.unlockTime;

    public static final class IdentityAccount extends SqlTable {
        public final SqlColumn<Long> recId = column("rec_id", JDBCType.BIGINT);

        public final SqlColumn<String> recCreatedBy = column("rec_created_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recCreatedOrg = column("rec_created_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recCreatedTime = column("rec_created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> recModifiedBy = column("rec_modified_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recModifiedOrg = column("rec_modified_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recModifiedTime = column("rec_modified_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> recVersion = column("rec_version", JDBCType.INTEGER);

        public final SqlColumn<String> accout = column("accout", JDBCType.VARCHAR);

        public final SqlColumn<String> accoutName = column("accout_name", JDBCType.VARCHAR);

        public final SqlColumn<String> password = column("password", JDBCType.VARCHAR);

        public final SqlColumn<String> mobile = column("mobile", JDBCType.VARCHAR);

        public final SqlColumn<String> email = column("email", JDBCType.VARCHAR);

        public final SqlColumn<Boolean> enabled = column("enabled", JDBCType.BIT);

        public final SqlColumn<LocalDateTime> lastLoginTime = column("last_login_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Boolean> lockStatus = column("lock_status", JDBCType.BIT);

        public final SqlColumn<Integer> errorCount = column("error_count", JDBCType.INTEGER);

        public final SqlColumn<String> agencyId = column("agency_id", JDBCType.VARCHAR);

        public final SqlColumn<String> userType = column("user_type", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> unlockTime = column("unlock_time", JDBCType.TIMESTAMP);

        public IdentityAccount() {
            super("iden_identity_account");
        }
    }
}