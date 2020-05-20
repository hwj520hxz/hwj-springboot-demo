package com.hwj.demo.user.server.dao;

import com.hwj.demo.component.pagehelper.PageMapper;
import com.hwj.demo.user.base.dao.OrgUserBaseMapper;
import com.hwj.demo.user.base.dao.OrgUserDynamicSqlSupport;
import com.hwj.demo.user.base.entity.OrgUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.Optional;

import static org.mybatis.dynamic.sql.SqlBuilder.isEqualTo;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@Mapper
public interface OrgUserMapper extends OrgUserBaseMapper, PageMapper {

    /**
     * 根据用户ID查询机构用户关系
     * @param userId
     * @return
     */
    default OrgUser getOrgUserByUserId(Long userId){
        Optional<OrgUser> orgUserOptional = selectOne(c -> c.where(OrgUserDynamicSqlSupport.userId, isEqualTo(userId)));
        return orgUserOptional.orElse(null);
    }

    /**
     * 根据用户Id删除机构用户关系
     * @param userId
     * @return
     */
    default int deleteOrgUserByUserId(Long userId) {
        return delete(c -> c.where(OrgUserDynamicSqlSupport.userId, isEqualTo(userId)));
    }
}
