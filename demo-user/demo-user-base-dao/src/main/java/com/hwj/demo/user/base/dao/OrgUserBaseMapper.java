package com.hwj.demo.user.base.dao;

import static com.hwj.demo.user.base.dao.OrgUserDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;


import com.hwj.demo.component.audit.support.AuditFactory;
import com.hwj.demo.user.base.entity.OrgUser;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.DeleteProvider;
import org.apache.ibatis.annotations.InsertProvider;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.SelectProvider;
import org.apache.ibatis.annotations.UpdateProvider;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.dynamic.sql.BasicColumn;
import org.mybatis.dynamic.sql.delete.DeleteDSLCompleter;
import org.mybatis.dynamic.sql.delete.render.DeleteStatementProvider;
import org.mybatis.dynamic.sql.insert.render.InsertStatementProvider;
import org.mybatis.dynamic.sql.insert.render.MultiRowInsertStatementProvider;
import org.mybatis.dynamic.sql.select.CountDSLCompleter;
import org.mybatis.dynamic.sql.select.SelectDSLCompleter;
import org.mybatis.dynamic.sql.select.render.SelectStatementProvider;
import org.mybatis.dynamic.sql.update.UpdateDSLCompleter;
import org.mybatis.dynamic.sql.update.render.UpdateStatementProvider;
import org.mybatis.dynamic.sql.util.SqlProviderAdapter;
import org.mybatis.dynamic.sql.util.mybatis3.MyBatis3Utils;

@Mapper
public interface OrgUserBaseMapper {
    BasicColumn[] selectList = BasicColumn.columnList(recId, userId, orgId, userCode, userName, userMobile, userEmail, recCreatedBy, recCreatedOrg, recCreatedTime, recModifiedBy, recModifiedOrg, recModifiedTime, recVersion);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<OrgUser> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<OrgUser> multipleInsertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrgUserResult")
    Optional<OrgUser> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrgUserResult", value = {
        @Result(column="rec_id", property="recId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="user_id", property="userId", jdbcType=JdbcType.BIGINT),
        @Result(column="org_id", property="orgId", jdbcType=JdbcType.BIGINT),
        @Result(column="user_code", property="userCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_name", property="userName", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_mobile", property="userMobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_email", property="userEmail", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_by", property="recCreatedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_org", property="recCreatedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_time", property="recCreatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_modified_by", property="recModifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_org", property="recModifiedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_time", property="recModifiedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_version", property="recVersion", jdbcType=JdbcType.INTEGER)
    })
    List<OrgUser> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, orgUser, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, orgUser, completer);
    }

    default int deleteByPrimaryKey(Long recId_) {
        return delete(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int insert(OrgUser record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, orgUser, c ->
            c.map(recId).toProperty("recId")
            .map(userId).toProperty("userId")
            .map(orgId).toProperty("orgId")
            .map(userCode).toProperty("userCode")
            .map(userName).toProperty("userName")
            .map(userMobile).toProperty("userMobile")
            .map(userEmail).toProperty("userEmail")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
        );
    }

    default int insertMultiple(Collection<OrgUser> records) {
        AuditFactory.fill(records);
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, orgUser, c ->
            c.map(recId).toProperty("recId")
            .map(userId).toProperty("userId")
            .map(orgId).toProperty("orgId")
            .map(userCode).toProperty("userCode")
            .map(userName).toProperty("userName")
            .map(userMobile).toProperty("userMobile")
            .map(userEmail).toProperty("userEmail")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
        );
    }

    default int insertSelective(OrgUser record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, orgUser, c ->
            c.map(recId).toPropertyWhenPresent("recId", record::getRecId)
            .map(userId).toPropertyWhenPresent("userId", record::getUserId)
            .map(orgId).toPropertyWhenPresent("orgId", record::getOrgId)
            .map(userCode).toPropertyWhenPresent("userCode", record::getUserCode)
            .map(userName).toPropertyWhenPresent("userName", record::getUserName)
            .map(userMobile).toPropertyWhenPresent("userMobile", record::getUserMobile)
            .map(userEmail).toPropertyWhenPresent("userEmail", record::getUserEmail)
            .map(recCreatedBy).toPropertyWhenPresent("recCreatedBy", record::getRecCreatedBy)
            .map(recCreatedOrg).toPropertyWhenPresent("recCreatedOrg", record::getRecCreatedOrg)
            .map(recCreatedTime).toPropertyWhenPresent("recCreatedTime", record::getRecCreatedTime)
            .map(recModifiedBy).toPropertyWhenPresent("recModifiedBy", record::getRecModifiedBy)
            .map(recModifiedOrg).toPropertyWhenPresent("recModifiedOrg", record::getRecModifiedOrg)
            .map(recModifiedTime).toPropertyWhenPresent("recModifiedTime", record::getRecModifiedTime)
            .map(recVersion).toPropertyWhenPresent("recVersion", record::getRecVersion)
        );
    }

    default Optional<OrgUser> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, orgUser, completer);
    }

    default List<OrgUser> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, orgUser, completer);
    }

    default List<OrgUser> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, orgUser, completer);
    }

    default Optional<OrgUser> selectByPrimaryKey(Long recId_) {
        return selectOne(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, orgUser, c -> completer.apply(c
                .set(recModifiedBy).equalTo(AuditFactory::getRecordModifiedBy)
                .set(recModifiedOrg).equalTo(AuditFactory::getRecordModifiedOrg)
                .set(recModifiedTime).equalTo(AuditFactory::getRecordModifiedTime)
                .set(recVersion).equalToConstant(recVersion.name() + "+1")
        ));
    }

    default int updateByPrimaryKey(OrgUser record) {
        return update(c ->
            c.set(userId).equalTo(record::getUserId)
            .set(orgId).equalTo(record::getOrgId)
            .set(userCode).equalTo(record::getUserCode)
            .set(userName).equalTo(record::getUserName)
            .set(userMobile).equalTo(record::getUserMobile)
            .set(userEmail).equalTo(record::getUserEmail)
            .where(recId, isEqualTo(record::getRecId))
        );
    }

    default int updateByPrimaryKeySelective(OrgUser record) {
        return update(c ->
            c.set(userId).equalToWhenPresent(record::getUserId)
            .set(orgId).equalToWhenPresent(record::getOrgId)
            .set(userCode).equalToWhenPresent(record::getUserCode)
            .set(userName).equalToWhenPresent(record::getUserName)
            .set(userMobile).equalToWhenPresent(record::getUserMobile)
            .set(userEmail).equalToWhenPresent(record::getUserEmail)
            .where(recId, isEqualTo(record::getRecId))
        );
    }
}
