package com.hwj.demo.operation.base.dao;

import static com.hwj.demo.operation.base.dao.OrgDynamicSqlSupport.*;
import static org.mybatis.dynamic.sql.SqlBuilder.*;

import com.hwj.demo.component.audit.support.AuditFactory;
import com.hwj.demo.operation.base.entity.Org;
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
public interface OrgBaseMapper {
    BasicColumn[] selectList = BasicColumn.columnList(recId, recCreatedBy, recCreatedOrg, recCreatedTime, recModifiedBy, recModifiedOrg, recModifiedTime, recVersion, recDate, recAuthor, orgType, orgCode, orgName, parentId, description);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    long count(SelectStatementProvider selectStatement);

    @DeleteProvider(type=SqlProviderAdapter.class, method="delete")
    int delete(DeleteStatementProvider deleteStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insert")
    int insert(InsertStatementProvider<Org> insertStatement);

    @InsertProvider(type=SqlProviderAdapter.class, method="insertMultiple")
    int insertMultiple(MultiRowInsertStatementProvider<Org> multipleInsertStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @ResultMap("OrgResult")
    Optional<Org> selectOne(SelectStatementProvider selectStatement);

    @SelectProvider(type=SqlProviderAdapter.class, method="select")
    @Results(id="OrgResult", value = {
        @Result(column="rec_id", property="recId", jdbcType=JdbcType.BIGINT, id=true),
        @Result(column="rec_created_by", property="recCreatedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_org", property="recCreatedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_created_time", property="recCreatedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_modified_by", property="recModifiedBy", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_org", property="recModifiedOrg", jdbcType=JdbcType.VARCHAR),
        @Result(column="rec_modified_time", property="recModifiedTime", jdbcType=JdbcType.TIMESTAMP),
        @Result(column="rec_version", property="recVersion", jdbcType=JdbcType.INTEGER),
        @Result(column="rec_date", property="recDate", jdbcType=JdbcType.DATE),
        @Result(column="rec_author", property="recAuthor", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_type", property="orgType", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_code", property="orgCode", jdbcType=JdbcType.VARCHAR),
        @Result(column="org_name", property="orgName", jdbcType=JdbcType.VARCHAR),
        @Result(column="parent_id", property="parentId", jdbcType=JdbcType.BIGINT),
        @Result(column="description", property="description", jdbcType=JdbcType.VARCHAR)
    })
    List<Org> selectMany(SelectStatementProvider selectStatement);

    @UpdateProvider(type=SqlProviderAdapter.class, method="update")
    int update(UpdateStatementProvider updateStatement);

    default long count(CountDSLCompleter completer) {
        return MyBatis3Utils.countFrom(this::count, org, completer);
    }

    default int delete(DeleteDSLCompleter completer) {
        return MyBatis3Utils.deleteFrom(this::delete, org, completer);
    }

    default int deleteByPrimaryKey(Long recId_) {
        return delete(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int insert(Org record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, org, c ->
            c.map(recId).toProperty("recId")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
            .map(recDate).toProperty("recDate")
            .map(recAuthor).toProperty("recAuthor")
            .map(orgType).toProperty("orgType")
            .map(orgCode).toProperty("orgCode")
            .map(orgName).toProperty("orgName")
            .map(parentId).toProperty("parentId")
            .map(description).toProperty("description")
        );
    }

    default int insertMultiple(Collection<Org> records) {
        AuditFactory.fill(records);
        return MyBatis3Utils.insertMultiple(this::insertMultiple, records, org, c ->
            c.map(recId).toProperty("recId")
            .map(recCreatedBy).toProperty("recCreatedBy")
            .map(recCreatedOrg).toProperty("recCreatedOrg")
            .map(recCreatedTime).toProperty("recCreatedTime")
            .map(recModifiedBy).toProperty("recModifiedBy")
            .map(recModifiedOrg).toProperty("recModifiedOrg")
            .map(recModifiedTime).toProperty("recModifiedTime")
            .map(recVersion).toProperty("recVersion")
            .map(recDate).toProperty("recDate")
            .map(recAuthor).toProperty("recAuthor")
            .map(orgType).toProperty("orgType")
            .map(orgCode).toProperty("orgCode")
            .map(orgName).toProperty("orgName")
            .map(parentId).toProperty("parentId")
            .map(description).toProperty("description")
        );
    }

    default int insertSelective(Org record) {
        AuditFactory.fill(record);
        return MyBatis3Utils.insert(this::insert, record, org, c ->
            c.map(recId).toPropertyWhenPresent("recId", record::getRecId)
            .map(recCreatedBy).toPropertyWhenPresent("recCreatedBy", record::getRecCreatedBy)
            .map(recCreatedOrg).toPropertyWhenPresent("recCreatedOrg", record::getRecCreatedOrg)
            .map(recCreatedTime).toPropertyWhenPresent("recCreatedTime", record::getRecCreatedTime)
            .map(recModifiedBy).toPropertyWhenPresent("recModifiedBy", record::getRecModifiedBy)
            .map(recModifiedOrg).toPropertyWhenPresent("recModifiedOrg", record::getRecModifiedOrg)
            .map(recModifiedTime).toPropertyWhenPresent("recModifiedTime", record::getRecModifiedTime)
            .map(recVersion).toPropertyWhenPresent("recVersion", record::getRecVersion)
            .map(recDate).toPropertyWhenPresent("recDate", record::getRecDate)
            .map(recAuthor).toPropertyWhenPresent("recAuthor", record::getRecAuthor)
            .map(orgType).toPropertyWhenPresent("orgType", record::getOrgType)
            .map(orgCode).toPropertyWhenPresent("orgCode", record::getOrgCode)
            .map(orgName).toPropertyWhenPresent("orgName", record::getOrgName)
            .map(parentId).toPropertyWhenPresent("parentId", record::getParentId)
            .map(description).toPropertyWhenPresent("description", record::getDescription)
        );
    }

    default Optional<Org> selectOne(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectOne(this::selectOne, selectList, org, completer);
    }

    default List<Org> select(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectList(this::selectMany, selectList, org, completer);
    }

    default List<Org> selectDistinct(SelectDSLCompleter completer) {
        return MyBatis3Utils.selectDistinct(this::selectMany, selectList, org, completer);
    }

    default Optional<Org> selectByPrimaryKey(Long recId_) {
        return selectOne(c ->
            c.where(recId, isEqualTo(recId_))
        );
    }

    default int update(UpdateDSLCompleter completer) {
        return MyBatis3Utils.update(this::update, org, c -> completer.apply(c
                .set(recModifiedBy).equalTo(AuditFactory::getRecordModifiedBy)
                .set(recModifiedOrg).equalTo(AuditFactory::getRecordModifiedOrg)
                .set(recModifiedTime).equalTo(AuditFactory::getRecordModifiedTime)
                .set(recVersion).equalToConstant(recVersion.name() + "+1")
        ));
    }

    default int updateByPrimaryKey(Org record) {
        return update(c ->
            c.set(recDate).equalTo(record::getRecDate)
            .set(recAuthor).equalTo(record::getRecAuthor)
            .set(orgType).equalTo(record::getOrgType)
            .set(orgCode).equalTo(record::getOrgCode)
            .set(orgName).equalTo(record::getOrgName)
            .set(parentId).equalTo(record::getParentId)
            .set(description).equalTo(record::getDescription)
            .where(recId, isEqualTo(record::getRecId))
        );
    }

    default int updateByPrimaryKeySelective(Org record) {
        return update(c ->
            c.set(recDate).equalToWhenPresent(record::getRecDate)
            .set(recAuthor).equalToWhenPresent(record::getRecAuthor)
            .set(orgType).equalToWhenPresent(record::getOrgType)
            .set(orgCode).equalToWhenPresent(record::getOrgCode)
            .set(orgName).equalToWhenPresent(record::getOrgName)
            .set(parentId).equalToWhenPresent(record::getParentId)
            .set(description).equalToWhenPresent(record::getDescription)
            .where(recId, isEqualTo(record::getRecId))
        );
    }
}
