package com.hwj.demo.user.server.api.impl;


import com.bosssoft.nontax.commons.groovy.mapper.Mapper;
import com.hwj.demo.user.authority.api.IUserAuthorizeApi;
import com.hwj.demo.user.authority.api.dto.UserApiDTO;

import com.hwj.demo.user.base.entity.User;
import com.hwj.demo.user.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Component
public class UserAuthorizeApi implements IUserAuthorizeApi {

    @Autowired
    private IUserService userService;

    @Autowired
    private Mapper mapper;

    @Override
    public UserApiDTO getUser(Long userId) {
        User user = userService.queryOne(userId);
        return mapper.map(user, UserApiDTO.class);
    }
}
