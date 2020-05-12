package com.hwj.demo.user.server.web;

import com.bosssoft.nontax.commons.groovy.mapper.Mapper;
import com.hwj.demo.user.base.entity.User;
import com.hwj.demo.user.server.service.IUserService;
import com.hwj.demo.user.server.web.dto.UserDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户管理
 */
@RestController
@RequestMapping("/hwj/user/user")
public class UserController {

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private IUserService userService;
    @Autowired
    private Mapper mapper;

    /**
     * 新增用户
     * @param userDTO 用户对象DTO
     */
    @PostMapping
    public Long saveUser(@RequestBody UserDTO userDTO) {
        User user = mapper.map(userDTO,User.class);
        User result = userService.save(user);
        return result.getRecId();
    }

}
