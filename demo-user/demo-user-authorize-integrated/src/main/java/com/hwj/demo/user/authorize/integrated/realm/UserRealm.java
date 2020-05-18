package com.hwj.demo.user.authorize.integrated.realm;

import com.hwj.demo.component.encryption.jwt.JWTUtil;
import com.hwj.demo.user.authority.api.IUserAuthorizeApi;
import com.hwj.demo.user.authority.api.dto.UserApiDTO;
import com.hwj.demo.user.authorize.integrated.shiro.JWTToken;
import com.hwj.demo.user.authorize.integrated.shiro.UserContext;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：安全实体数据源
 */
@Service
public class UserRealm extends AuthorizingRealm {

    // 权限相关API接口
    private final IUserAuthorizeApi userAuthorizeApi;

    // 构造函数
    public UserRealm(IUserAuthorizeApi userAuthorizeApi) {
        this.userAuthorizeApi = userAuthorizeApi;
    }

    /**
     * 是否支持token
     * AuthorizingRealm只支持UsernamePasswordToken类型的token，传入其他token会报错,如果需要自定义的Realm支持其他token，需要重写以下方法
     **/
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JWTToken;
    }


    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principal) {
        return null;
    }

    /**
     * 用户认证
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken auth) throws AuthenticationException {
        String token = (String) auth.getCredentials();  //获取token
        Long userId = JWTUtil.getUserId(token);  //获取账户
        if(userId == null){
            throw new AuthenticationException("无效的token");
        }
        UserApiDTO user = userAuthorizeApi.getUser(userId);
        if(Objects.isNull(user)){
            throw new AuthenticationException("用户信息不存在!");
        }
        UserContext.set(userId); //将用户ID存储到线程中
        return new SimpleAuthenticationInfo(token, token, "user_realm");
    }

}
