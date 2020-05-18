package com.hwj.demo.component.exception.enums;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
public enum IdentityEnums implements EnumsInterface{

    /* 登录模块 */
    ACCOUNT_IS_NOT_EXISTS(CommonEnums.BUSINESS_ERROR.getCode(),"用户不存在!"),
    ACCOUNT_IS_LOCK(CommonEnums.BUSINESS_ERROR.getCode(),"该账户已被锁定，请${content}分钟后重试!"),
    ACCOUNT_PASSWORD_ERROR(CommonEnums.BUSINESS_ERROR.getCode(),"密码错误!错误次数达到${content}次将被锁定!"),
    ACCOUNT_UNABLED(CommonEnums.BUSINESS_ERROR.getCode(),"该用户已被禁用，请联系管理员!"),
    PASSWORD_ERROR(CommonEnums.BUSINESS_ERROR.getCode(),"密码错误!"),
    ;

    IdentityEnums(int code, String message) {
        this.code = code;
        this.message = message;
    }

    private int code;
    private String message;

    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }}
