package com.hwj.demo.component.exception.enums;

public enum UserCenterEnums implements EnumsInterface {
    /* 应用模块 */
    APPLICATION_IS_EXISTS(CommonEnums.BUSINESS_ERROR.getCode(),"该${content}已经存在，请重新维护!"),
    APPLICATION_IS_NOT_EXISTS(CommonEnums.BUSINESS_ERROR.getCode(),"未找到对应的应用!请刷新页面重试!"),
    APPLICATION_FIELD_IS_MUST(CommonEnums.BUSINESS_ERROR.getCode(),"${content}不能为空!请重新输入!"),
    APPLICATION_OPERATION_ERROR(CommonEnums.BUSINESS_ERROR.getCode(),"${content}失败!请重试!"),
    /* 用户模块 */
    USER_IS_NOT_EXISTS(CommonEnums.BUSINESS_ERROR.getCode(),"未找到对应的用户!请刷新页面重试!"),
    USER_FIELD_NO_UPDATE(CommonEnums.BUSINESS_ERROR.getCode(),"${content}不能修改"),
    USER_FIELD_IS_MUST(CommonEnums.BUSINESS_ERROR.getCode(),"${content}不能为空!请重新输入!"),
    USER_OLD_PASSWORD_ERROR(CommonEnums.BUSINESS_ERROR.getCode(),"原密码不正确，请重新输入"),
    USER_INTERNAL_NO_EDIT(CommonEnums.BUSINESS_ERROR.getCode(),"内置用户${content}，请重新选择"),

    /*机构用户模块*/
    ORG_USER_NOT_EXISTS(CommonEnums.GONE.getCode(),"该组织下的用户信息不存在,请重试"),
    ;


    UserCenterEnums(int code, String message) {
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
    }
}
