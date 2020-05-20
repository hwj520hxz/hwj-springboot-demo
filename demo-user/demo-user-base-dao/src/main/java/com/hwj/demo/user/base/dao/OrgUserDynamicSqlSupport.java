package com.hwj.demo.user.base.dao;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrgUserDynamicSqlSupport {
    public static final OrgUser orgUser = new OrgUser();

    /**
     * id
     */
    public static final SqlColumn<Long> recId = orgUser.recId;

    /**
     * 用户id
     */
    public static final SqlColumn<Long> userId = orgUser.userId;

    /**
     * 组织id
     */
    public static final SqlColumn<Long> orgId = orgUser.orgId;

    /**
     * 用户账号
     */
    public static final SqlColumn<String> userCode = orgUser.userCode;

    /**
     * 用户姓名
     */
    public static final SqlColumn<String> userName = orgUser.userName;

    /**
     * 用户手机号
     */
    public static final SqlColumn<String> userMobile = orgUser.userMobile;

    /**
     * 用户邮箱
     */
    public static final SqlColumn<String> userEmail = orgUser.userEmail;

    /**
     * 记录创建人
     */
    public static final SqlColumn<String> recCreatedBy = orgUser.recCreatedBy;

    /**
     * 记录创建机构
     */
    public static final SqlColumn<String> recCreatedOrg = orgUser.recCreatedOrg;

    /**
     * 记录创建时间
     */
    public static final SqlColumn<LocalDateTime> recCreatedTime = orgUser.recCreatedTime;

    /**
     * 记录最后修改人
     */
    public static final SqlColumn<String> recModifiedBy = orgUser.recModifiedBy;

    /**
     * 记录最后修改机构
     */
    public static final SqlColumn<String> recModifiedOrg = orgUser.recModifiedOrg;

    /**
     * 记录最后修改时间
     */
    public static final SqlColumn<LocalDateTime> recModifiedTime = orgUser.recModifiedTime;

    /**
     * 记录版本号
     */
    public static final SqlColumn<Integer> recVersion = orgUser.recVersion;

    public static final class OrgUser extends SqlTable {
        public final SqlColumn<Long> recId = column("rec_id", JDBCType.BIGINT);

        public final SqlColumn<Long> userId = column("user_id", JDBCType.BIGINT);

        public final SqlColumn<Long> orgId = column("org_id", JDBCType.BIGINT);

        public final SqlColumn<String> userCode = column("user_code", JDBCType.VARCHAR);

        public final SqlColumn<String> userName = column("user_name", JDBCType.VARCHAR);

        public final SqlColumn<String> userMobile = column("user_mobile", JDBCType.VARCHAR);

        public final SqlColumn<String> userEmail = column("user_email", JDBCType.VARCHAR);

        public final SqlColumn<String> recCreatedBy = column("rec_created_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recCreatedOrg = column("rec_created_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recCreatedTime = column("rec_created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> recModifiedBy = column("rec_modified_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recModifiedOrg = column("rec_modified_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recModifiedTime = column("rec_modified_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> recVersion = column("rec_version", JDBCType.INTEGER);

        public OrgUser() {
            super("oper_org_user");
        }
    }
}