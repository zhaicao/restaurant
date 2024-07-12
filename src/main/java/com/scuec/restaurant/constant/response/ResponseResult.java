package com.scuec.restaurant.constant.response;

import lombok.Data;

/**
 * 自定义接口统一返回格式
 */
@Data
public class ResponseResult<T> {
    /**
     * 状态码
     */
    protected int code;
    /**
     * 响应信息
     */
    protected String message;
    /**
     * 返回数据
     */
    private T data;

    /**
     * 封装成功和失败的调用方法
     * @param <T>
     * @return
     */
    public static <T> ResponseResult<T> success() {
        return new ResponseResult<>();
    }

    public static <T> ResponseResult<T> success(T data) {
        return new ResponseResult<>(data);
    }

    public static <T> ResponseResult<T> error(String message) {
        return new ResponseResult<T>(ResponseCode.ERROR.getCode(), message);
    }

    public static <T> ResponseResult<T> error(ResponseCode code, String message) {
        return new ResponseResult<T>(code.getCode(), message);
    }

    /**
     * 默认构造方法，返回成功
     */
    public ResponseResult() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMsg();
    }

    /**
     * 带ResponseCode参数的构造方法
     * @param responseCode
     */
    public ResponseResult(ResponseCode responseCode) {
        this.code = responseCode.getCode();
        this.message = responseCode.getMsg();
    }
    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     */
    public ResponseResult(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
    /**
     * 有数据返回时，状态码为200，默认提示信息为“SUCCESS”
     */
    public ResponseResult(T data) {
        this.data = data;
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMsg();
    }
    /**
     * 有数据返回，状态码为 200，需指定提示信息
     */
    public ResponseResult(T data, String msg) {
        this.data = data;
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = msg;
    }

    /**
     * @param code {@link ResponseCode#getCode()}
     * @return
     */
    public ResponseResult<T> code(int code) {
        this.code = code;
        return this;
    }

    public ResponseResult<T> message(String message) {
        this.message = message;
        return this;
    }

    public ResponseResult<T> data(T data) {
        this.data = data;
        return this;
    }
}


