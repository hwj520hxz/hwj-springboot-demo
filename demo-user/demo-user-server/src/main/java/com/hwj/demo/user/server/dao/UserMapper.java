package com.hwj.demo.user.server.dao;

import com.hwj.demo.component.pagehelper.PageMapper;
import com.hwj.demo.user.base.dao.UserBaseMapper;
import com.hwj.demo.user.base.dao.UserDynamicSqlSupport;
import com.hwj.demo.user.base.entity.User;

import java.util.List;
import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：用户管理mapper
 */
public interface UserMapper extends UserBaseMapper, PageMapper {

    /**
     * 根据用户ID查询用户
     **/
    default User getUserById(Long userId){
        Optional<User> user = selectOne(c -> c.where(UserDynamicSqlSupport.recId, isEqualTo(userId)));
        return user.orElse(null);
    }


}
