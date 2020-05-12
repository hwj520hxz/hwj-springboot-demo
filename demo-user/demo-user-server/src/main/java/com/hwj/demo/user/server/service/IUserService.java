package com.hwj.demo.user.server.service;

import com.hwj.demo.user.base.entity.User;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户管理服务接口
 */
public interface IUserService {

    /**
     * 保存用户信息
     * @param user
     */
    User save(User user);
}
