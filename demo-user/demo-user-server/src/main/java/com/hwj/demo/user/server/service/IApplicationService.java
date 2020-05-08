package com.hwj.demo.user.server.service;

import com.hwj.demo.user.base.entity.Application;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface IApplicationService {

    /**
     * 新增应用管理
     * @param application 应用对象
     **/
    void save(Application application);

    /**
     * 修改应用管理
     * @param application 应用对象
     **/
    void update(Application application);

    /**
     * 删除应用管理
     * @param recId 应用ID
     **/
    void remove(Long recId);

    /**
     * 查询应用管理
     * @param appId 应用id
     **/
    Page<Application> listApplication(Long appId, Pageable page);


}
