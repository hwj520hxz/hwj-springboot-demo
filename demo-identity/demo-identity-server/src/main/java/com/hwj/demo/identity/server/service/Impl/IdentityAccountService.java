package com.hwj.demo.identity.server.service.Impl;

import com.hwj.demo.component.encryption.PasswordUtil;
import com.hwj.demo.component.exception.entity.MessageContext;
import com.hwj.demo.component.exception.enums.IdentityEnums;
import com.hwj.demo.component.exception.exception.BusinessException;
import com.hwj.demo.component.id.generator.UidGenerator;
import com.hwj.demo.identity.base.dao.IdentityAccountBaseMapper;
import com.hwj.demo.identity.base.entity.IdentityAccount;
import com.hwj.demo.identity.base.entity.IdentityAttr;
import com.hwj.demo.identity.server.dao.IdentityAccountMapper;
import com.hwj.demo.identity.server.service.IIdentityAccountService;
import com.hwj.demo.identity.server.service.IIdentityAttrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
@Service
public class IdentityAccountService implements IIdentityAccountService {

    private static Integer errorCount = 5;

    @Autowired
    private UidGenerator uidGenerator;

    @Autowired
    private IIdentityAttrService identityAttrService;
    @Autowired
    private IdentityAccountMapper identityAccountMapper;
    @Autowired
    private IdentityAccountBaseMapper identityAccountBaseMapper;

    @Override
    public IdentityAccount checkUser(String code, String password, String type) {
        Optional<IdentityAccount> identityAccount = identityAccountMapper.getIdentityAccountByCodeAndType(code, type);
        if (!identityAccount.isPresent()) {
            throw new BusinessException(IdentityEnums.ACCOUNT_IS_NOT_EXISTS);
        }
        IdentityAccount identity = identityAccount.get();
        // 判断是否需要解锁
        if (identity.getUnlockTime() != null && identity.getUnlockTime().isBefore(LocalDateTime.now())) {
            _unlockIdentityAccount(identity);
        }
        // 当前时间小于解锁时间，说明账户还处于锁定状态
        if (identity.getUnlockTime() != null && identity.getUnlockTime().isAfter(LocalDateTime.now())) {
            Duration duration = Duration.between(LocalDateTime.now(), identity.getUnlockTime());
            // 加1分钟的目的在于 系统时间和解锁时间肯定是小于5分钟（在精确到毫秒的情况下） 但是在页面上需要展示5分钟，而且可以避免当两者毫秒数不一致的时候显示剩余0分钟的问题
            throw new BusinessException(IdentityEnums.ACCOUNT_IS_LOCK, MessageContext.buildContext("content", duration.plusMinutes(1).toMinutes()));
        }
        // 密码错误
        if (!PasswordUtil.verify(password, identityAccount.get().getPassword())) {
            IdentityAccount identity2 = new IdentityAccount();
            identity2.setRecId(identity.getRecId());
            // 密码错误次数未达到上限次数需要加1
            if (identity.getErrorCount() < errorCount) {
                identity2.setErrorCount(identity.getErrorCount() + 1);
            } else {
                // 密码错误次数已达到上限次数不需要加1，需要更新状态及设置解锁时间
                identity2.setLockStatus(true);
                identity2.setUnlockTime(LocalDateTime.now().plusMinutes(5).withNano(0));
                identityAccountBaseMapper.updateByPrimaryKeySelective(identity2);
                Duration duration = Duration.between(LocalDateTime.now(), identity2.getUnlockTime());
                throw new BusinessException(IdentityEnums.ACCOUNT_IS_LOCK, MessageContext.buildContext("content", duration.plusMinutes(1).toMinutes()));
            }
            identityAccountBaseMapper.updateByPrimaryKeySelective(identity2);
            throw new BusinessException(IdentityEnums.ACCOUNT_PASSWORD_ERROR, MessageContext.buildContext("content", errorCount));
        }
        if (!identityAccount.get().getEnabled()) {
            throw new BusinessException(IdentityEnums.ACCOUNT_UNABLED);
        }
        updateLoginTime(identityAccount.get().getRecId());
        return identityAccount.get();
    }


    @Override
    public void updateLoginTime(Long recId) {
        Optional<IdentityAccount> optional = identityAccountBaseMapper.selectByPrimaryKey(recId);
        if (!optional.isPresent()) {
            throw new BusinessException(IdentityEnums.ACCOUNT_IS_NOT_EXISTS);
        }
        IdentityAccount account = optional.get();
        account.setLastLoginTime(LocalDateTime.now());
        if (account.getErrorCount() != 0 || account.getLockStatus()) {
            account.setErrorCount(0);
            account.setLockStatus(false);
            account.setUnlockTime(null);
        }
        identityAccountBaseMapper.updateByPrimaryKey(account);
    }

    @Override
    public Long saveIdentityAccount(IdentityAccount identityAccount, List<IdentityAttr> attrList, String type) {

        return save(identityAccount, attrList, type).getRecId();
    }

    @Override
    public void deleteUser(Long accountOpenId) {
        Optional<IdentityAccount> optional = identityAccountBaseMapper.selectByPrimaryKey(accountOpenId);
        if (!optional.isPresent()) {
            return;
        }
        IdentityAccount identityAccount = optional.get();
        if (identityAccount.getLastLoginTime() != null) {
            throw new BusinessException("当前用户已经登录过，不能删除！");
        }
        identityAccountBaseMapper.deleteByPrimaryKey(accountOpenId);
    }

    @Override
    public IdentityAccount save(IdentityAccount identityAccount, List<IdentityAttr> attrList, String type) {
        identityAccount.setRecId(uidGenerator.getUID());
        identityAccount.setUserType(type);
        identityAccount.setLockStatus(false);
        identityAccount.setEnabled(Boolean.TRUE);
        identityAccount.setErrorCount(0);
        identityAccountBaseMapper.insert(identityAccount);
        for (IdentityAttr identityAttr : attrList) {
            identityAttr.setRecId(uidGenerator.getUID());
            identityAttr.setIdentityId(identityAccount.getRecId());
            identityAttrService.saveIdentityAttr(identityAttr);
        }
        return identityAccount;
    }

    /**
     * 解锁账户
     **/
    private void _unlockIdentityAccount(IdentityAccount identityAccount) {
        identityAccount.setUnlockTime(null);
        identityAccount.setErrorCount(0);
        identityAccount.setLockStatus(false);
        identityAccountBaseMapper.updateByPrimaryKey(identityAccount);
    }
}
