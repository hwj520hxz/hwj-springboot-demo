package com.hwj.demo.operation.server.web;


import com.bosssoft.nontax.commons.groovy.mapper.Mapper;
import com.hwj.demo.operation.base.entity.Org;
import com.hwj.demo.operation.server.service.IOrgService;
import com.hwj.demo.operation.server.web.dto.OrgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：组织管理
 */

@RestController
@RequestMapping("/hwj/operation/org")
public class OrgController {

    @Autowired
    private Mapper mapper;
    @Autowired
    private IOrgService orgService;


    /**
     * 保存组织管理信息
     *
     * @param orgDTO
     */
    @PostMapping()
    public void saveOrg(@RequestBody OrgDTO orgDTO) {
        Org org = mapper.map(orgDTO, Org.class);
        orgService.save(org);
    }
}
