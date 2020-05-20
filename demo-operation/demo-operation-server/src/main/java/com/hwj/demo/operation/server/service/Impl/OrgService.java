package com.hwj.demo.operation.server.service.Impl;

import com.hwj.demo.component.id.generator.UidGenerator;
import com.hwj.demo.operation.base.entity.Org;
import com.hwj.demo.operation.server.dao.OrgMapper;
import com.hwj.demo.operation.server.service.IOrgService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：组织管理
 */
@Service
public class OrgService implements IOrgService {

    @Autowired
    private OrgMapper orgMapper;

    @Autowired
    private UidGenerator uidGenerator;

    @Override
    public void save(Org org) {

        orgMapper.insert(org);

    }
}
