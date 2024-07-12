package com.scuec.restaurant.constant.response;

/**
 * 返回code定义
 */
public enum ResponseCode {
    SUCCESS(200,"SUCCESS"),
    ERROR(500,"ERROR");

    private Integer code;
    private String msg;

    ResponseCode(Integer code, String message) {
        this.code = code;
        this.msg = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMsg() {
        return this.msg;
    }
}
