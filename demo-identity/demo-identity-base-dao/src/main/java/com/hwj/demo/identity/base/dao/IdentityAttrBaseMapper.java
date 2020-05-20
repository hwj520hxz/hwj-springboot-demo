package com.hwj.demo.identity.base.dao;

import static com.hwj.demo.identity.base.dao.IdentityAttrDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.hwj.demo.component.audit.support.AuditFactory;
import com.hwj.demo.identity.base.entity.IdentityAttr;
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
public interface IdentityAttrBaseMapper {
    BasicColumn[] selectList = BasicColumn.columnList(recId, recCreatedBy, recCreatedOrg, recCreatedTime, recModifiedBy, recModifiedOrg, recModifiedTime, recVersion, identityId, attributeKey, attributeValue, attributeDescription);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<IdentityAttr> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<IdentityAttr> multipleInsertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("IdentityAttrResult")
    Optional<IdentityAttr> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="IdentityAttrResult", value = {
        @Result(column="rec_id", property="recId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="rec_created_by", property="recCreatedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_org", property="recCreatedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_time", property="recCreatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_modified_by", property="recModifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_org", property="recModifiedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_time", property="recModifiedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_version", property="recVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="identity_id", property="identityId", jdbcType=JdbcType.BIGINT),
        @Result(column="attribute_key", property="attributeKey", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute_value", property="attributeValue", jdbcType=JdbcType.VARCHAR),
        @Result(column="attribute_description", property="attributeDescription", jdbcType=JdbcType.VARCHAR)
    })
    List<IdentityAttr> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, identityAttr, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, identityAttr, completer);
    }

    default int deleteByPrimaryKey(Long recId_) {
        return delete(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int insert(IdentityAttr record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, identityAttr, c ->
            c.map(recId).toProperty("recId")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
            .map(identityId).toProperty("identityId")
            .map(attributeKey).toProperty("attributeKey")
            .map(attributeValue).toProperty("attributeValue")
            .map(attributeDescription).toProperty("attributeDescription")
        );
    }

    default int insertMultiple(Collection<IdentityAttr> records) {
        AuditFactory.fill(records);
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, identityAttr, c ->
            c.map(recId).toProperty("recId")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
            .map(identityId).toProperty("identityId")
            .map(attributeKey).toProperty("attributeKey")
            .map(attributeValue).toProperty("attributeValue")
            .map(attributeDescription).toProperty("attributeDescription")
        );
    }

    default int insertSelective(IdentityAttr record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, identityAttr, c ->
            c.map(recId).toPropertyWhenPresent("recId", record::getRecId)
            .map(recCreatedBy).toPropertyWhenPresent("recCreatedBy", record::getRecCreatedBy)
            .map(recCreatedOrg).toPropertyWhenPresent("recCreatedOrg", record::getRecCreatedOrg)
            .map(recCreatedTime).toPropertyWhenPresent("recCreatedTime", record::getRecCreatedTime)
            .map(recModifiedBy).toPropertyWhenPresent("recModifiedBy", record::getRecModifiedBy)
            .map(recModifiedOrg).toPropertyWhenPresent("recModifiedOrg", record::getRecModifiedOrg)
            .map(recModifiedTime).toPropertyWhenPresent("recModifiedTime", record::getRecModifiedTime)
            .map(recVersion).toPropertyWhenPresent("recVersion", record::getRecVersion)
            .map(identityId).toPropertyWhenPresent("identityId", record::getIdentityId)
            .map(attributeKey).toPropertyWhenPresent("attributeKey", record::getAttributeKey)
            .map(attributeValue).toPropertyWhenPresent("attributeValue", record::getAttributeValue)
            .map(attributeDescription).toPropertyWhenPresent("attributeDescription", record::getAttributeDescription)
        );
    }

    default Optional<IdentityAttr> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, identityAttr, completer);
    }

    default List<IdentityAttr> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, identityAttr, completer);
    }

    default List<IdentityAttr> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, identityAttr, completer);
    }

    default Optional<IdentityAttr> selectByPrimaryKey(Long recId_) {
        return selectOne(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, identityAttr, c -> completer.apply(c
                .set(recModifiedBy).equalTo(AuditFactory::getRecordModifiedBy)
                .set(recModifiedOrg).equalTo(AuditFactory::getRecordModifiedOrg)
                .set(recModifiedTime).equalTo(AuditFactory::getRecordModifiedTime)
                .set(recVersion).equalToConstant(recVersion.name() + "+1")
        ));
    }

    default int updateByPrimaryKey(IdentityAttr record) {
        return update(c ->
            c.set(identityId).equalTo(record::getIdentityId)
            .set(attributeKey).equalTo(record::getAttributeKey)
            .set(attributeValue).equalTo(record::getAttributeValue)
            .set(attributeDescription).equalTo(record::getAttributeDescription)
            .where(recId, isEqualTo(record::getRecId))
        );
    }

    default int updateByPrimaryKeySelective(IdentityAttr record) {
        return update(c ->
            c.set(identityId).equalToWhenPresent(record::getIdentityId)
            .set(attributeKey).equalToWhenPresent(record::getAttributeKey)
            .set(attributeValue).equalToWhenPresent(record::getAttributeValue)
            .set(attributeDescription).equalToWhenPresent(record::getAttributeDescription)
            .where(recId, isEqualTo(record::getRecId))
        );
    }
}
