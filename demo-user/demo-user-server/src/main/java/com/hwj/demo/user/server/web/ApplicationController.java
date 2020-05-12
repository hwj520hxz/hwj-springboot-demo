package com.hwj.demo.user.server.web;

import com.bosssoft.nontax.commons.groovy.mapper.Mapper;
import com.hwj.demo.user.base.entity.Application;
import com.hwj.demo.user.server.service.IApplicationService;
import com.hwj.demo.user.server.web.dto.ApplicationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：应用管理
 */

@RestController
@RequestMapping("/hwj/user/application")
public class ApplicationController {

    @Autowired
    private IApplicationService applicationService;

    @Autowired
    private Mapper mapper;


    /**
     * 新增应用
     * @param applicationDTO 应用实例
     */
    @PostMapping()
    public void saveApplication(@RequestBody ApplicationDTO applicationDTO){
        Application application = mapper.map(applicationDTO,Application.class);
        applicationService.save(application);
    }

    /**
     * 查询应用（分页）
     */
    @GetMapping(value = {"/{appId}", ""})
    public Page<Application> listApplication(@PathVariable(required = false) Long appId, Pageable page){
        return applicationService.listApplication(appId, page);
    }
}
