package com.hwj.demo.identity.server.dao;


import com.hwj.demo.identity.base.dao.IdentityAccountBaseMapper;
import com.hwj.demo.identity.base.entity.IdentityAccount;
import org.apache.ibatis.annotations.Mapper;
import org.mybatis.dynamic.sql.SqlBuilder;

import java.util.Optional;

import static com.hwj.demo.identity.base.dao.IdentityAccountDynamicSqlSupport.accout;
import static com.hwj.demo.identity.base.dao.IdentityAccountDynamicSqlSupport.userType;


/**
 * @author ：hwj
 */
@Mapper
public interface IdentityAccountMapper extends IdentityAccountBaseMapper {

    /**
     * 查询账户
     * @param code 身份证号
     * @param type 账户类型
     **/
    default Optional<IdentityAccount> getIdentityAccountByCodeAndType(String code, String type){
        return selectOne(c -> c.where(accout, SqlBuilder.isEqualTo(code))
                .and(userType, SqlBuilder.isEqualTo(type)));
    }


}
