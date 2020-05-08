package com.hwj.demo.user.base.dao;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class ApplicationDynamicSqlSupport {
    public static final Application application = new Application();

    /**
     * id
     */
    public static final SqlColumn<Long> recId = application.recId;

    /**
     * 编码
     */
    public static final SqlColumn<String> code = application.code;

    /**
     * 名称
     */
    public static final SqlColumn<String> name = application.name;

    /**
     * 描述
     */
    public static final SqlColumn<String> description = application.description;

    /**
     * 记录创建人
     */
    public static final SqlColumn<String> recCreatedBy = application.recCreatedBy;

    /**
     * 记录创建机构
     */
    public static final SqlColumn<String> recCreatedOrg = application.recCreatedOrg;

    /**
     * 记录创建时间
     */
    public static final SqlColumn<LocalDateTime> recCreatedTime = application.recCreatedTime;

    /**
     * 记录最后修改人
     */
    public static final SqlColumn<String> recModifiedBy = application.recModifiedBy;

    /**
     * 记录最后修改机构
     */
    public static final SqlColumn<String> recModifiedOrg = application.recModifiedOrg;

    /**
     * 记录最后修改时间
     */
    public static final SqlColumn<LocalDateTime> recModifiedTime = application.recModifiedTime;

    /**
     * 记录版本号
     */
    public static final SqlColumn<Integer> recVersion = application.recVersion;

    public static final class Application extends SqlTable {
        public final SqlColumn<Long> recId = column("rec_id", JDBCType.BIGINT);

        public final SqlColumn<String> code = column("code", JDBCType.VARCHAR);

        public final SqlColumn<String> name = column("name", JDBCType.VARCHAR);

        public final SqlColumn<String> description = column("description", JDBCType.VARCHAR);

        public final SqlColumn<String> recCreatedBy = column("rec_created_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recCreatedOrg = column("rec_created_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recCreatedTime = column("rec_created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> recModifiedBy = column("rec_modified_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recModifiedOrg = column("rec_modified_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recModifiedTime = column("rec_modified_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> recVersion = column("rec_version", JDBCType.INTEGER);

        public Application() {
            super("auth_application");
        }
    }
}