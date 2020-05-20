package com.hwj.demo.operation.server.service.Impl;

import com.bosssoft.nontax.commons.groovy.mapper.Mapper;
import com.hwj.demo.component.encryption.PasswordUtil;
import com.hwj.demo.component.exception.enums.UserCenterEnums;
import com.hwj.demo.component.exception.exception.BusinessException;
import com.hwj.demo.component.id.generator.UidGenerator;

import com.hwj.demo.identity.dto.IdentityDTO;
import com.hwj.demo.operation.server.api.impl.UserManageApi;
import com.hwj.demo.operation.server.entity.OrgUserEntity;
import com.hwj.demo.operation.server.service.IOrgUserService;
import com.hwj.demo.user.authority.api.dto.UserApiDTO;
import com.hwj.demo.user.base.entity.OrgUser;

import com.hwj.demo.user.server.dao.OrgUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Service
public class OrgUserService implements IOrgUserService {

    @Autowired
    private UidGenerator uidGenerator;
    @Autowired
    private UserManageApi userManageApi;
    @Autowired
    private OrgUserMapper orgUserMapper;
    @Autowired
    private Mapper mapper;

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void save(OrgUserEntity orgUser) {

        UserApiDTO userApiDTO = orgUser.getUserApiDTO();
        userApiDTO.setPassword(PasswordUtil.encode(PasswordUtil.DEFAULT_PASSWORD));

        //调用认证中心保存用户数据
        IdentityDTO dto = mapper.map(userApiDTO, IdentityDTO.class);
        dto.setUserId(null);
        //Long userId = identityProviderApi.save(dto);

        //调用用户权限中心保存用户数据
        //userApiDTO.setRecId(userId);
        userManageApi.saveUser(userApiDTO);

        //保存用户组织关系
        orgUser.setRecId(uidGenerator.getUID());
        //orgUser.setUserId(userId);
        orgUserMapper.insert(orgUser);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void update(OrgUserEntity orgUser) {
        OrgUser oldOrgUser = orgUserMapper.getOrgUserByUserId(orgUser.getUserId());
        if (oldOrgUser == null){
            throw new BusinessException(UserCenterEnums.ORG_USER_NOT_EXISTS);
        }
        //更新用户信息
        UserApiDTO userApiDTO = orgUser.getUserApiDTO();
        userManageApi.updateUser(userApiDTO);
        //更新机构用户信息
        oldOrgUser.setUserName(userApiDTO.getName());
        oldOrgUser.setUserCode(userApiDTO.getCode());
        oldOrgUser.setUserEmail(userApiDTO.getEmail());
        oldOrgUser.setUserMobile(userApiDTO.getMobile());
        orgUserMapper.updateByPrimaryKey(oldOrgUser);
    }

    @Override
    @Transactional(rollbackFor = Throwable.class)
    public void delete(Long userId) {
        //认证中心删除用户信息
        //identityProviderApi.deleteUser(userId);

        //用户权限中心删除用户信息
        userManageApi.deleteUser(userId);

        //删除用户机构关系
        orgUserMapper.deleteOrgUserByUserId(userId);
    }
}
