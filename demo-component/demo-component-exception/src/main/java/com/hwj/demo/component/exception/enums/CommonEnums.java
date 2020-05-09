package com.hwj.demo.component.exception.enums;

import org.springframework.http.HttpStatus;

public enum CommonEnums implements EnumsInterface {

    /**
     * HTTP 相关
     */
    SUCCESS(HttpStatus.OK.value(), "success"),
    NO_LOGIN_ERROR(HttpStatus.UNAUTHORIZED.value(), "未授权，请先登录"),
    FORBIDDEN(HttpStatus.FORBIDDEN.value(), "服务器拒绝您的连接"),
    NOT_FOUND(HttpStatus.NOT_FOUND.value(), "未找到对应内容"),
    LOOP_DETECTED(HttpStatus.LOOP_DETECTED.value(), "请求超时，请重试"),
    GONE(HttpStatus.GONE.value(), "内容已删除"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR.value(), "服务器异常"),


    /**
     * 自定义内容
     */
    PARAMETER_ERROR(HttpStatus.BAD_REQUEST.value(), "参数错误"),
    ENUMS_ERROR(HttpStatus.BAD_REQUEST.value(), "枚举参数错误"),
    BUSINESS_ERROR(INTERNAL_SERVER_ERROR.getCode(), "业务处理异常"),
    ;

    CommonEnums(int code, String message) {
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
        return this.message;
    }

}
