package com.hwj.demo.operation.base.dao;

import java.sql.JDBCType;
import java.time.LocalDate;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class OrgDynamicSqlSupport {
    public static final Org org = new Org();

    /**
     * 主键id
     */
    public static final SqlColumn<Long> recId = org.recId;

    /**
     * 记录创建人
     */
    public static final SqlColumn<String> recCreatedBy = org.recCreatedBy;

    /**
     * 记录创建机构
     */
    public static final SqlColumn<String> recCreatedOrg = org.recCreatedOrg;

    /**
     * 记录创建时间
     */
    public static final SqlColumn<LocalDateTime> recCreatedTime = org.recCreatedTime;

    /**
     * 记录最后修改人
     */
    public static final SqlColumn<String> recModifiedBy = org.recModifiedBy;

    /**
     * 记录最后修改机构
     */
    public static final SqlColumn<String> recModifiedOrg = org.recModifiedOrg;

    /**
     * 记录最后修改时间
     */
    public static final SqlColumn<LocalDateTime> recModifiedTime = org.recModifiedTime;

    /**
     * 记录版本号
     */
    public static final SqlColumn<Integer> recVersion = org.recVersion;

    /**
     * 编制日期
     */
    public static final SqlColumn<LocalDate> recDate = org.recDate;

    /**
     * 编制人
     */
    public static final SqlColumn<String> recAuthor = org.recAuthor;

    /**
     * 组织结构类型(销售组织、实施组织)
     */
    public static final SqlColumn<String> orgType = org.orgType;

    /**
     * 组织编码
     */
    public static final SqlColumn<String> orgCode = org.orgCode;

    /**
     * 组织名称
     */
    public static final SqlColumn<String> orgName = org.orgName;

    /**
     * 上级组织id
     */
    public static final SqlColumn<Long> parentId = org.parentId;

    /**
     * 组织描述
     */
    public static final SqlColumn<String> description = org.description;

    public static final class Org extends SqlTable {
        public final SqlColumn<Long> recId = column("rec_id", JDBCType.BIGINT);

        public final SqlColumn<String> recCreatedBy = column("rec_created_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recCreatedOrg = column("rec_created_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recCreatedTime = column("rec_created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> recModifiedBy = column("rec_modified_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recModifiedOrg = column("rec_modified_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recModifiedTime = column("rec_modified_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> recVersion = column("rec_version", JDBCType.INTEGER);

        public final SqlColumn<LocalDate> recDate = column("rec_date", JDBCType.DATE);

        public final SqlColumn<String> recAuthor = column("rec_author", JDBCType.VARCHAR);

        public final SqlColumn<String> orgType = column("org_type", JDBCType.VARCHAR);

        public final SqlColumn<String> orgCode = column("org_code", JDBCType.VARCHAR);

        public final SqlColumn<String> orgName = column("org_name", JDBCType.VARCHAR);

        public final SqlColumn<Long> parentId = column("parent_id", JDBCType.BIGINT);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public Org() {
            super("oper_org");
        }
    }
}