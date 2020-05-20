package com.hwj.demo.operation.server.service;


import com.hwj.demo.operation.server.entity.OrgUserEntity;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface IOrgUserService {
    /**
     * 保存机构用户信息
     * @param orgUser
     */
    void save(OrgUserEntity orgUser);

    /**
     * 修改机构用户信息
     * @param orgUser
     */
    void update(OrgUserEntity orgUser);

    /**
     * 删除机构用户信息
     * @param userId
     */
    void delete(Long userId);

}
