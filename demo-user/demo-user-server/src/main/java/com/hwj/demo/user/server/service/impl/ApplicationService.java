package com.hwj.demo.user.server.service.impl;

import com.hwj.demo.user.base.entity.Application;
import com.hwj.demo.user.server.dao.ApplicationMapper;
import com.hwj.demo.user.server.service.IApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@Service
public class ApplicationService implements IApplicationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ApplicationService.class);

    @Autowired
    private ApplicationMapper applicationMapper;

    @Override
    public void save(Application application) {

    }

    @Override
    public void update(Application application) {

    }

    @Override
    public void remove(Long recId) {

    }

    /**
     * 查询应用管理
     * @param appId 应用id
     **/
    @Override
    public Page<Application> listApplication(Long appId, Pageable page) {
        return applicationMapper.doSelectPage(page, () -> applicationMapper.queryApplication(appId));
    }
}
