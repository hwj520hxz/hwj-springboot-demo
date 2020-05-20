package com.hwj.demo.identity.server.service.Impl;

import com.hwj.demo.component.id.generator.UidGenerator;
import com.hwj.demo.identity.base.dao.IdentityAttrBaseMapper;
import com.hwj.demo.identity.base.entity.IdentityAttr;
import com.hwj.demo.identity.server.service.IIdentityAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */

@Service
public class IdentityAttrService implements IIdentityAttrService {

    @Autowired
    private UidGenerator uidGenerator;

    @Autowired
    private IdentityAttrBaseMapper identityAttrBaseMapper;

    @Override
    public void saveIdentityAttr(IdentityAttr identityAttr) {
        identityAttr.setRecId(uidGenerator.getUID());
        identityAttrBaseMapper.insert(identityAttr);
    }
}
