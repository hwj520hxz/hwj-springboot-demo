package com.hwj.demo.user.authority.api;

import com.hwj.demo.user.authority.api.dto.UserApiDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface IUserAuthorizeApi {
    /**
     * 获取用户信息
     * @param userId 用户id
     * @return
     */
    @GetMapping("authorize/user/{userId}")
    UserApiDTO getUser(@PathVariable Long userId);
}
