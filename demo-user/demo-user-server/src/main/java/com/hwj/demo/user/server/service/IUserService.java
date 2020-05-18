package com.hwj.demo.user.server.service;

import com.hwj.demo.user.base.entity.User;

import java.util.List;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户管理服务接口
 */
public interface IUserService {

    /**
     * 新增用户
     * @param user 用户信息
     */
    User save(User user);

    /**
     * 删除用户
     * @param userId 用户ID
     */
    void delete(Long userId);

    /**
     * 修改用户
     * @param user 用户信息
     */
    void update(User user);

    /**
     * 查询用户（不带分页）
     */
    List<User> queryUser();

    /**
     * 根据用户ID查询用户信息
     * @param userId
     * @return
     */
    User queryOne(Long userId);

}
