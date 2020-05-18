package com.hwj.demo.identity.base.dao;

import java.sql.JDBCType;
import java.time.LocalDateTime;
import org.mybatis.dynamic.sql.SqlColumn;
import org.mybatis.dynamic.sql.SqlTable;

public final class IdentityAttrDynamicSqlSupport {
    public static final IdentityAttr identityAttr = new IdentityAttr();

    /**
     * 主键id
     */
    public static final SqlColumn<Long> recId = identityAttr.recId;

    /**
     * 记录创建人
     */
    public static final SqlColumn<String> recCreatedBy = identityAttr.recCreatedBy;

    /**
     * 记录创建机构
     */
    public static final SqlColumn<String> recCreatedOrg = identityAttr.recCreatedOrg;

    /**
     * 记录创建时间
     */
    public static final SqlColumn<LocalDateTime> recCreatedTime = identityAttr.recCreatedTime;

    /**
     * 记录最后修改人
     */
    public static final SqlColumn<String> recModifiedBy = identityAttr.recModifiedBy;

    /**
     * 记录最后修改机构
     */
    public static final SqlColumn<String> recModifiedOrg = identityAttr.recModifiedOrg;

    /**
     * 记录最后修改时间
     */
    public static final SqlColumn<LocalDateTime> recModifiedTime = identityAttr.recModifiedTime;

    /**
     * 记录版本号
     */
    public static final SqlColumn<Integer> recVersion = identityAttr.recVersion;

    /**
     * 身份ID
     */
    public static final SqlColumn<Long> identityId = identityAttr.identityId;

    /**
     * 属性键
     */
    public static final SqlColumn<String> attributeKey = identityAttr.attributeKey;

    /**
     * 属性值
     */
    public static final SqlColumn<String> attributeValue = identityAttr.attributeValue;

    /**
     * 属性描述
     */
    public static final SqlColumn<String> attributeDescription = identityAttr.attributeDescription;

    public static final class IdentityAttr extends SqlTable {
        public final SqlColumn<Long> recId = column("rec_id", JDBCType.BIGINT);

        public final SqlColumn<String> recCreatedBy = column("rec_created_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recCreatedOrg = column("rec_created_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recCreatedTime = column("rec_created_time", JDBCType.TIMESTAMP);

        public final SqlColumn<String> recModifiedBy = column("rec_modified_by", JDBCType.VARCHAR);

        public final SqlColumn<String> recModifiedOrg = column("rec_modified_org", JDBCType.VARCHAR);

        public final SqlColumn<LocalDateTime> recModifiedTime = column("rec_modified_time", JDBCType.TIMESTAMP);

        public final SqlColumn<Integer> recVersion = column("rec_version", JDBCType.INTEGER);

        public final SqlColumn<Long> identityId = column("identity_id", JDBCType.BIGINT);

        public final SqlColumn<String> attributeKey = column("attribute_key", JDBCType.VARCHAR);

        public final SqlColumn<String> attributeValue = column("attribute_value", JDBCType.VARCHAR);

        public final SqlColumn<String> attributeDescription = column("attribute_description", JDBCType.VARCHAR);

        public IdentityAttr() {
            super("iden_identity_attr");
        }
    }
}