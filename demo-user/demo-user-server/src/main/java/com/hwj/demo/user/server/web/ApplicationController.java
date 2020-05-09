package com.hwj.demo.user.server.web;

import com.hwj.demo.user.base.entity.Application;
import com.hwj.demo.user.server.service.IApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

    /**
     * 查询应用
     */
    @GetMapping(value = {"/{appId}", ""})
    public Page<Application> listApplication(@PathVariable(required = false) Long appId, Pageable page){
        return applicationService.listApplication(appId, page);
    }
}
