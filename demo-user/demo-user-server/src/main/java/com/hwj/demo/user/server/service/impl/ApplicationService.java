package com.hwj.demo.user.server.service.impl;


import com.hwj.demo.component.exception.entity.MessageContext;
import com.hwj.demo.component.exception.enums.UserCenterEnums;
import com.hwj.demo.component.exception.exception.BusinessException;
import com.hwj.demo.component.id.generator.UidGenerator;
import com.hwj.demo.user.base.entity.Application;
import com.hwj.demo.user.server.dao.ApplicationMapper;
import com.hwj.demo.user.server.service.IApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Objects;

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

    @Autowired
    private UidGenerator uidGenerator;

    /**
     * 新增应用管理
     * @param application 应用对象
     **/
    @Override
    public void save(Application application) {
        // 校验字段是否存在
        _validateApplication(application);
        // 校验应用是否存在(根据名称和编码唯一确定一条数据)
        Application exist = applicationMapper.applicationIsExist(application.getCode(), application.getName());
        if(Objects.nonNull(exist)){
            if(LOGGER.isDebugEnabled()){
                LOGGER.debug("编码为[{}]的应用已存在!",application.getCode());
            }
            throw new BusinessException(UserCenterEnums.APPLICATION_IS_EXISTS, MessageContext.buildContext("content","应用编码或名称"));
        }
        application.setRecId(uidGenerator.getUID());          //主键
        int sucCount = applicationMapper.insert(application);
        if(sucCount < 1){
            throw new BusinessException(UserCenterEnums.APPLICATION_OPERATION_ERROR, MessageContext.buildContext("content","新增应用"));
        }
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

    /**
     * 校验应用的必填项是否填写
     * @param application 应用对象
     **/
    private void _validateApplication(Application application){
        if(Objects.isNull(application)){
            throw new BusinessException(UserCenterEnums.APPLICATION_IS_NOT_EXISTS);
        }
        if(StringUtils.isEmpty(application.getCode())){
            throw new BusinessException(UserCenterEnums.APPLICATION_FIELD_IS_MUST,MessageContext.buildContext("content","编码"));
        }
        if(StringUtils.isEmpty(application.getName())){
            throw new BusinessException(UserCenterEnums.APPLICATION_FIELD_IS_MUST,MessageContext.buildContext("content","名称"));
        }
    }

    /**
     * 校验应用是否存在
     * 如果待修改的数据和修改后的数据一致则不需要校验编码或名称是否已经存在
     * @param application 应用对象
     **/
    private void _validateApplicationExist(Application application){
        Application item = applicationMapper.applicationIsExist(application.getCode(), application.getName());
        if(Objects.nonNull(item)) {
            if (!StringUtils.isEmpty(item.getRecId()) && !StringUtils.isEmpty(application.getRecId())) {
                if (item.getRecId().longValue() != application.getRecId().longValue()) {
                    if (LOGGER.isDebugEnabled()) {
                        LOGGER.debug("编码或名称已存在，请重新维护!");
                    }
                    throw new BusinessException(UserCenterEnums.APPLICATION_IS_EXISTS, MessageContext.buildContext("content","应用编码或名称"));
                }
            }
        }
    }
}
