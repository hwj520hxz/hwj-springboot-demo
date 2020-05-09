package com.hwj.demo.component.exception.entity;

/**
 * @author ：hwj
 * @version 版本号：V1.0
 * @Description ：
 */
final public class MessageContext {

    private String k;
    private Object v;

    public String getK() {
        return k;
    }

    public Object getV() {
        return v;
    }

    public MessageContext(String k, Object v) {
        this.k = k;
        this.v = v;
    }

    public static MessageContext buildContext(String k, Object v) {
        return new MessageContext(k, v);
    }
}
