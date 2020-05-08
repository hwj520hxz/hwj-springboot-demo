package com.hwj.demo.user.server.dao;

import com.hwj.demo.component.pagehelper.PageMapper;
import com.hwj.demo.user.base.dao.ApplicationBaseMapper;
import com.hwj.demo.user.base.dao.ApplicationDynamicSqlSupport;
import com.hwj.demo.user.base.entity.Application;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface ApplicationMapper extends ApplicationBaseMapper, PageMapper {

    /**
     * 根据编码和名称查询应用
     * @param code 应用编码
     * @param name 应用名称
     **/
    default Application applicationIsExist(String code, String name){
        Optional<Application> application = selectOne(c -> c
                .where(ApplicationDynamicSqlSupport.code, isEqualTo(code))
                .or(ApplicationDynamicSqlSupport.name, isEqualTo(name)));
        return application.orElse(null);
    }

    /**
     * 根据应用ID查询应用
     * @param appId 应用ID
     **/
    default List<Application> queryApplication(Long appId){
        List<Application> applications = select(c -> c
                .where(ApplicationDynamicSqlSupport.recId, isEqualTo(appId).when((a) -> appId != null))
                .orderBy(ApplicationDynamicSqlSupport.recCreatedTime));
        return (applications.size() > 0 ? applications : null);
    }




}
