package com.hwj.demo.operation.server.api.impl;

import com.bosssoft.nontax.commons.groovy.mapper.Mapper;
import com.hwj.demo.user.authority.api.IUserManageApi;
import com.hwj.demo.user.authority.api.dto.UserApiDTO;
import com.hwj.demo.user.base.entity.User;
import com.hwj.demo.user.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Service
public class UserManageApi implements IUserManageApi {

    @Autowired
    private IUserService userService;
    @Autowired
    private Mapper mapper;

    @Override
    public Long saveUser(UserApiDTO userApiDTO) {
        User user = mapper.map(userApiDTO,User.class);
        User result = userService.save(user);
        return result.getRecId();
    }

    @Override
    public void deleteUser(Long recId) {
        userService.delete(recId);
    }

    @Override
    public void updateUser(UserApiDTO userApiDTO) {
        User user = mapper.map(userApiDTO,User.class);
        userService.update(user);
    }
}
