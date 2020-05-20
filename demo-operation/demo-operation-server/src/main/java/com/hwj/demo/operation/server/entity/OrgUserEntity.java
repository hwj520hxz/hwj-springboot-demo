package com.hwj.demo.operation.server.entity;


import com.hwj.demo.operation.base.entity.Org;
import com.hwj.demo.user.authority.api.dto.UserApiDTO;
import com.hwj.demo.user.base.entity.OrgUser;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public class OrgUserEntity extends OrgUser {
    /**
     * 机构信息
     */
    private Org org;
    /**
     * 用户信息
     */
    private UserApiDTO userApiDTO;

    public Org getOrg() {
        return org;
    }

    public void setOrg(Org org) {
        this.org = org;
    }

    public UserApiDTO getUserApiDTO() {
        return userApiDTO;
    }

    public void setUserApiDTO(UserApiDTO userApiDTO) {
        this.userApiDTO = userApiDTO;
    }
}
