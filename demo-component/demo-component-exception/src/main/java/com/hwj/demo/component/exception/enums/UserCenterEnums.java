package com.hwj.demo.component.exception.enums;

public enum UserCenterEnums implements EnumsInterface {
    /* 应用模块 */
    APPLICATION_IS_EXISTS(CommonEnums.BUSINESS_ERROR.getCode(),"该${content}已经存在，请重新维护!"),
    APPLICATION_IS_NOT_EXISTS(CommonEnums.BUSINESS_ERROR.getCode(),"未找到对应的应用!请刷新页面重试!"),
    APPLICATION_FIELD_IS_MUST(CommonEnums.BUSINESS_ERROR.getCode(),"${content}不能为空!请重新输入!"),
    APPLICATION_OPERATION_ERROR(CommonEnums.BUSINESS_ERROR.getCode(),"${content}失败!请重试!"),

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
