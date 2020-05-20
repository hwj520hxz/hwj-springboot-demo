package com.hwj.demo.operation.server.web;

import com.bosssoft.nontax.commons.groovy.mapper.Mapper;

import com.hwj.demo.operation.server.entity.OrgUserEntity;
import com.hwj.demo.operation.server.service.IOrgUserService;
import com.hwj.demo.operation.server.web.dto.AuthUserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@RestController
@RequestMapping("/hwj/user/orgUser")
public class OrgUserController {
    @Autowired
    private IOrgUserService orgUserService;
    @Autowired
    private Mapper mapper;

    /**
     * 新增用户
     * @param userDTO 用户对象DTO
     */
    @PostMapping
    public void saveUser(@RequestBody AuthUserDTO userDTO) {
        OrgUserEntity user = mapper.map(userDTO, OrgUserEntity.class);
        orgUserService.save(user);
    }
    /**
     * 修改用户
     * @param userDTO 用户对象DTO
     */
    @PutMapping
    public void updateUser(@RequestBody AuthUserDTO userDTO) {
        OrgUserEntity user = mapper.map(userDTO,OrgUserEntity.class);
        orgUserService.update(user);
    }
    /**
     * 删除用户
     * @param userId
     */
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId) {
        orgUserService.delete(userId);
    }





}
