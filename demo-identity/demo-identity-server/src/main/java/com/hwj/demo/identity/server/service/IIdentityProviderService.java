package com.hwj.demo.identity.server.service;

import com.hwj.demo.identity.server.web.dto.IdentityDTO;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public interface IIdentityProviderService {

    /**
     * 新增身份信息
     *
     * @param identityDTO
     */
    Long save(IdentityDTO identityDTO);

    /**
     * 删除身份信息
     */
    void deleteUser(@PathVariable("userId") Long userId);
}
