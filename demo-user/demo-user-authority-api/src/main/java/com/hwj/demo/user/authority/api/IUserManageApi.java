package com.hwj.demo.user.authority.api;

import com.hwj.demo.user.authority.api.dto.UserApiDTO;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface IUserManageApi {
    /**
     * 新增用户
     * @param userApiDTO
     * @return
     */
    Long saveUser(@RequestBody UserApiDTO userApiDTO);

    /**
     * 删除用户
     * @param recId
     */
    void deleteUser(@PathVariable Long recId);

    /**
     * 修改用户
     * @param userApiDTO
     */
    void updateUser(@RequestBody UserApiDTO userApiDTO);

}
