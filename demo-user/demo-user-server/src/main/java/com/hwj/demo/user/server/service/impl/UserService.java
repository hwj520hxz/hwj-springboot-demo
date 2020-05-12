package com.hwj.demo.user.server.service.impl;

import com.hwj.demo.component.id.generator.UidGenerator;
import com.hwj.demo.user.base.entity.User;
import com.hwj.demo.user.server.dao.UserMapper;
import com.hwj.demo.user.server.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户管理服务实现类
 */
@Service
public class UserService implements IUserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public User save(User user) {
        _beforeInsert(user);
        //插入用户信息
        userMapper.insert(user);
        return user;
    }

    /**
     * 插入前操作
     *
     * @param user
     */
    private void _beforeInsert(User user) {
        if (user.getRecId() == null) {
            user.setRecId(uidGenerator.getUID());
        }
        user.setInternal(false);
        user.setEnabled(true);
    }
}
