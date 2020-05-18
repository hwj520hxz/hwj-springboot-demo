package com.hwj.demo.identity.base.dao;

import static com.hwj.demo.identity.base.dao.IdentityAccountDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;


import com.hwj.demo.component.audit.support.AuditFactory;
import com.hwj.demo.identity.base.entity.IdentityAccount;
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
public interface IdentityAccountBaseMapper {
    BasicColumn[] selectList = BasicColumn.columnList(recId, recCreatedBy, recCreatedOrg, recCreatedTime, recModifiedBy, recModifiedOrg, recModifiedTime, recVersion, accout, accoutName, password, mobile, email, enabled, lastLoginTime, lockStatus, errorCount, agencyId, userType, unlockTime);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<IdentityAccount> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<IdentityAccount> multipleInsertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("IdentityAccountResult")
    Optional<IdentityAccount> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="IdentityAccountResult", value = {
        @Result(column="rec_id", property="recId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="rec_created_by", property="recCreatedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_org", property="recCreatedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_time", property="recCreatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_modified_by", property="recModifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_org", property="recModifiedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_time", property="recModifiedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_version", property="recVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="accout", property="accout", jdbcType=JdbcType.VARCHAR),
        @Result(column="accout_name", property="accoutName", jdbcType=JdbcType.VARCHAR),
        @Result(column="password", property="password", jdbcType=JdbcType.VARCHAR),
        @Result(column="mobile", property="mobile", jdbcType=JdbcType.VARCHAR),
        @Result(column="email", property="email", jdbcType=JdbcType.VARCHAR),
        @Result(column="enabled", property="enabled", jdbcType=JdbcType.BIT),
        @Result(column="last_login_time", property="lastLoginTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="lock_status", property="lockStatus", jdbcType=JdbcType.BIT),
        @Result(column="error_count", property="errorCount", jdbcType=JdbcType.INTEGER),
        @Result(column="agency_id", property="agencyId", jdbcType=JdbcType.VARCHAR),
        @Result(column="user_type", property="userType", jdbcType=JdbcType.VARCHAR),
        @Result(column="unlock_time", property="unlockTime", jdbcType=JdbcType.TIMESTAMP)
    })
    List<IdentityAccount> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, identityAccount, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, identityAccount, completer);
    }

    default int deleteByPrimaryKey(Long recId_) {
        return delete(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int insert(IdentityAccount record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, identityAccount, c ->
            c.map(recId).toProperty("recId")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
            .map(accout).toProperty("accout")
            .map(accoutName).toProperty("accoutName")
            .map(password).toProperty("password")
            .map(mobile).toProperty("mobile")
            .map(email).toProperty("email")
            .map(enabled).toProperty("enabled")
            .map(lastLoginTime).toProperty("lastLoginTime")
            .map(lockStatus).toProperty("lockStatus")
            .map(errorCount).toProperty("errorCount")
            .map(agencyId).toProperty("agencyId")
            .map(userType).toProperty("userType")
            .map(unlockTime).toProperty("unlockTime")
        );
    }

    default int insertMultiple(Collection<IdentityAccount> records) {
        AuditFactory.fill(records);
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, identityAccount, c ->
            c.map(recId).toProperty("recId")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
            .map(accout).toProperty("accout")
            .map(accoutName).toProperty("accoutName")
            .map(password).toProperty("password")
            .map(mobile).toProperty("mobile")
            .map(email).toProperty("email")
            .map(enabled).toProperty("enabled")
            .map(lastLoginTime).toProperty("lastLoginTime")
            .map(lockStatus).toProperty("lockStatus")
            .map(errorCount).toProperty("errorCount")
            .map(agencyId).toProperty("agencyId")
            .map(userType).toProperty("userType")
            .map(unlockTime).toProperty("unlockTime")
        );
    }

    default int insertSelective(IdentityAccount record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, identityAccount, c ->
            c.map(recId).toPropertyWhenPresent("recId", record::getRecId)
            .map(recCreatedBy).toPropertyWhenPresent("recCreatedBy", record::getRecCreatedBy)
            .map(recCreatedOrg).toPropertyWhenPresent("recCreatedOrg", record::getRecCreatedOrg)
            .map(recCreatedTime).toPropertyWhenPresent("recCreatedTime", record::getRecCreatedTime)
            .map(recModifiedBy).toPropertyWhenPresent("recModifiedBy", record::getRecModifiedBy)
            .map(recModifiedOrg).toPropertyWhenPresent("recModifiedOrg", record::getRecModifiedOrg)
            .map(recModifiedTime).toPropertyWhenPresent("recModifiedTime", record::getRecModifiedTime)
            .map(recVersion).toPropertyWhenPresent("recVersion", record::getRecVersion)
            .map(accout).toPropertyWhenPresent("accout", record::getAccout)
            .map(accoutName).toPropertyWhenPresent("accoutName", record::getAccoutName)
            .map(password).toPropertyWhenPresent("password", record::getPassword)
            .map(mobile).toPropertyWhenPresent("mobile", record::getMobile)
            .map(email).toPropertyWhenPresent("email", record::getEmail)
            .map(enabled).toPropertyWhenPresent("enabled", record::getEnabled)
            .map(lastLoginTime).toPropertyWhenPresent("lastLoginTime", record::getLastLoginTime)
            .map(lockStatus).toPropertyWhenPresent("lockStatus", record::getLockStatus)
            .map(errorCount).toPropertyWhenPresent("errorCount", record::getErrorCount)
            .map(agencyId).toPropertyWhenPresent("agencyId", record::getAgencyId)
            .map(userType).toPropertyWhenPresent("userType", record::getUserType)
            .map(unlockTime).toPropertyWhenPresent("unlockTime", record::getUnlockTime)
        );
    }

    default Optional<IdentityAccount> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, identityAccount, completer);
    }

    default List<IdentityAccount> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, identityAccount, completer);
    }

    default List<IdentityAccount> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, identityAccount, completer);
    }

    default Optional<IdentityAccount> selectByPrimaryKey(Long recId_) {
        return selectOne(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, identityAccount, c -> completer.apply(c
                .set(recModifiedBy).equalTo(AuditFactory::getRecordModifiedBy)
                .set(recModifiedOrg).equalTo(AuditFactory::getRecordModifiedOrg)
                .set(recModifiedTime).equalTo(AuditFactory::getRecordModifiedTime)
                .set(recVersion).equalToConstant(recVersion.name() + "+1")
        ));
    }

    default int updateByPrimaryKey(IdentityAccount record) {
        return update(c ->
            c.set(accout).equalTo(record::getAccout)
            .set(accoutName).equalTo(record::getAccoutName)
            .set(password).equalTo(record::getPassword)
            .set(mobile).equalTo(record::getMobile)
            .set(email).equalTo(record::getEmail)
            .set(enabled).equalTo(record::getEnabled)
            .set(lastLoginTime).equalTo(record::getLastLoginTime)
            .set(lockStatus).equalTo(record::getLockStatus)
            .set(errorCount).equalTo(record::getErrorCount)
            .set(agencyId).equalTo(record::getAgencyId)
            .set(userType).equalTo(record::getUserType)
            .set(unlockTime).equalTo(record::getUnlockTime)
            .where(recId, isEqualTo(record::getRecId))
        );
    }

    default int updateByPrimaryKeySelective(IdentityAccount record) {
        return update(c ->
            c.set(accout).equalToWhenPresent(record::getAccout)
            .set(accoutName).equalToWhenPresent(record::getAccoutName)
            .set(password).equalToWhenPresent(record::getPassword)
            .set(mobile).equalToWhenPresent(record::getMobile)
            .set(email).equalToWhenPresent(record::getEmail)
            .set(enabled).equalToWhenPresent(record::getEnabled)
            .set(lastLoginTime).equalToWhenPresent(record::getLastLoginTime)
            .set(lockStatus).equalToWhenPresent(record::getLockStatus)
            .set(errorCount).equalToWhenPresent(record::getErrorCount)
            .set(agencyId).equalToWhenPresent(record::getAgencyId)
            .set(userType).equalToWhenPresent(record::getUserType)
            .set(unlockTime).equalToWhenPresent(record::getUnlockTime)
            .where(recId, isEqualTo(record::getRecId))
        );
    }
}
