package com.hwj.demo.user.base.dao;

import static com.hwj.demo.user.base.dao.ApplicationDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;


import com.hwj.demo.component.audit.support.AuditFactory;
import com.hwj.demo.user.base.entity.Application;
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
public interface ApplicationBaseMapper {
    BasicColumn[] selectList = BasicColumn.columnList(recId, code, name, description, recCreatedBy, recCreatedOrg, recCreatedTime, recModifiedBy, recModifiedOrg, recModifiedTime, recVersion);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Application> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Application> multipleInsertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("ApplicationResult")
    Optional<Application> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="ApplicationResult", value = {
        @Result(column="rec_id", property="recId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="code", property="code", jdbcType=JdbcType.VARCHAR),
        @Result(column="name", property="name", jdbcType=JdbcType.VARCHAR),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_by", property="recCreatedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_org", property="recCreatedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_time", property="recCreatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_modified_by", property="recModifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_org", property="recModifiedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_time", property="recModifiedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_version", property="recVersion", jdbcType=JdbcType.INTEGER)
    })
    List<Application> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, application, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, application, completer);
    }

    default int deleteByPrimaryKey(Long recId_) {
        return delete(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int insert(Application record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, application, c ->
            c.map(recId).toProperty("recId")
            .map(code).toProperty("code")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
        );
    }

    default int insertMultiple(Collection<Application> records) {
        AuditFactory.fill(records);
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, application, c ->
            c.map(recId).toProperty("recId")
            .map(code).toProperty("code")
            .map(name).toProperty("name")
            .map(description).toProperty("description")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
        );
    }

    default int insertSelective(Application record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, application, c ->
            c.map(recId).toPropertyWhenPresent("recId", record::getRecId)
            .map(code).toPropertyWhenPresent("code", record::getCode)
            .map(name).toPropertyWhenPresent("name", record::getName)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
            .map(recCreatedBy).toPropertyWhenPresent("recCreatedBy", record::getRecCreatedBy)
            .map(recCreatedOrg).toPropertyWhenPresent("recCreatedOrg", record::getRecCreatedOrg)
            .map(recCreatedTime).toPropertyWhenPresent("recCreatedTime", record::getRecCreatedTime)
            .map(recModifiedBy).toPropertyWhenPresent("recModifiedBy", record::getRecModifiedBy)
            .map(recModifiedOrg).toPropertyWhenPresent("recModifiedOrg", record::getRecModifiedOrg)
            .map(recModifiedTime).toPropertyWhenPresent("recModifiedTime", record::getRecModifiedTime)
            .map(recVersion).toPropertyWhenPresent("recVersion", record::getRecVersion)
        );
    }

    default Optional<Application> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, application, completer);
    }

    default List<Application> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, application, completer);
    }

    default List<Application> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, application, completer);
    }

    default Optional<Application> selectByPrimaryKey(Long recId_) {
        return selectOne(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, application, c -> completer.apply(c
                .set(recModifiedBy).equalTo(AuditFactory::getRecordModifiedBy)
                .set(recModifiedOrg).equalTo(AuditFactory::getRecordModifiedOrg)
                .set(recModifiedTime).equalTo(AuditFactory::getRecordModifiedTime)
                .set(recVersion).equalToConstant(recVersion.name() + "+1")
        ));
    }

    default int updateByPrimaryKey(Application record) {
        return update(c ->
            c.set(code).equalTo(record::getCode)
            .set(name).equalTo(record::getName)
            .set(description).equalTo(record::getDescription)
            .where(recId, isEqualTo(record::getRecId))
        );
    }

    default int updateByPrimaryKeySelective(Application record) {
        return update(c ->
            c.set(code).equalToWhenPresent(record::getCode)
            .set(name).equalToWhenPresent(record::getName)
            .set(description).equalToWhenPresent(record::getDescription)
            .where(recId, isEqualTo(record::getRecId))
        );
    }
}
