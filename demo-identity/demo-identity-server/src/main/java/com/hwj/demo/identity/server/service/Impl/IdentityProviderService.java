package com.hwj.demo.identity.server.service.Impl;

import com.bosssoft.nontax.commons.groovy.mapper.Mapper;
import com.google.common.collect.Lists;
import com.hwj.demo.identity.base.entity.IdentityAccount;
import com.hwj.demo.identity.base.entity.IdentityAttr;
import com.hwj.demo.identity.server.web.dto.IdentityAttributeDTO;
import com.hwj.demo.identity.server.web.dto.IdentityDTO;
import com.hwj.demo.identity.server.service.IIdentityAccountService;
import com.hwj.demo.identity.server.service.IIdentityProviderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Service
public class IdentityProviderService implements IIdentityProviderService {

    private static final Logger LOGGER = LoggerFactory.getLogger(IdentityProviderService.class);

    @Autowired
    private Mapper mapper;

    @Autowired
    private IIdentityAccountService identityAccountService;

    @Override
    public Long save(@RequestBody IdentityDTO identityDTO) {
        LOGGER.debug("新增运营平台用户，用户名:{},密码:{},手机号:{}", identityDTO.getAccount(), identityDTO.getPassword(), identityDTO.getMobile());
        IdentityAccount identityAccount = mapper.map(identityDTO, IdentityAccount.class);
        List<IdentityAttr> attrList = convert2Local(identityDTO);
        identityAccount.setUserType(IIdentityAccountService.USER_TYPE1);
        return identityAccountService.saveIdentityAccount(identityAccount, attrList, IIdentityAccountService.USER_TYPE1);
    }

    @Override
    public void deleteUser(Long userId) {
        LOGGER.debug("删除用户id={}的用户", userId);
        identityAccountService.deleteUser(userId);
    }

    private List<IdentityAttr> convert2Local(IdentityDTO identityDTO) {
        List<IdentityAttr> attrList = Lists.newArrayList();
        if (!CollectionUtils.isEmpty(identityDTO.getAttributes())) {
            for (IdentityAttributeDTO identityAttributeDTO : identityDTO.getAttributes()) {
                IdentityAttr identityAttr = mapper.map(identityAttributeDTO, IdentityAttr.class);
                BeanUtils.copyProperties(identityDTO, identityAttr);
                attrList.add(identityAttr);
            }
        }
        return attrList;
    }
}
