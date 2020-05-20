package com.hwj.demo.user.server.service.impl;

import com.hwj.demo.component.encryption.PasswordUtil;
import com.hwj.demo.component.exception.entity.MessageContext;
import com.hwj.demo.component.exception.enums.UserCenterEnums;
import com.hwj.demo.component.exception.exception.BusinessException;
import com.hwj.demo.component.id.generator.UidGenerator;
import com.hwj.demo.user.base.entity.User;
import com.hwj.demo.user.server.dao.UserMapper;
import com.hwj.demo.user.server.service.IUserService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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


    /**
     * 新增用户
     **/
    @Override
    public User save(User user) {
        _beforeInsert(user);
        //插入用户信息
        userMapper.insert(user);
        return user;
    }

    /**
     * 删除用户
     **/
    @Override
    public void delete(Long userId) {
        _userIsExist(userId);
        //删除用户信息
        userMapper.deleteByPrimaryKey(userId);
    }

    /**
     * 修改用户
     **/
    @Override
    public void update(User user) {
        _userIsExist(user.getRecId());
        //修改用户信息
        userMapper.updateByPrimaryKey(user);
    }

    /**
     * 查询用户
     **/
    @Override
    public List<User> queryUser() {
        return userMapper.select(c -> c.where());
    }

    @Override
    public User queryOne(Long userId) {
        if (userId == null) {
            throw new BusinessException(UserCenterEnums.USER_FIELD_IS_MUST,
                    MessageContext.buildContext("content", "用户ID"));
        }
        return userMapper.selectByPrimaryKey(userId).orElseThrow(() -> new BusinessException(UserCenterEnums.USER_IS_NOT_EXISTS));
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
        // 如果密码为空则设置默认密码
        if(StringUtils.isEmpty(user.getPassword())){
            user.setPassword(PasswordUtil.encode(PasswordUtil.DEFAULT_PASSWORD));
        }
        user.setInternal(false);
        user.setEnabled(true);
    }

    /**
     * 判断用户是否存在
     *
     * @param userId
     */
    private void _userIsExist(Long userId) {
        User user = userMapper.getUserById(userId);
        if(user == null){
            throw new BusinessException(UserCenterEnums.USER_IS_NOT_EXISTS);
        }
    }


}
