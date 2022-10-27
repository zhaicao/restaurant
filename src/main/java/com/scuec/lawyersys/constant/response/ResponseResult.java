package com.scuec.lawyersys.constant.response;

import lombok.Data;

/**
 *
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
    public ResponseResult() {
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMsg();
    }
    public ResponseResult(ResponseCode resCode) {
        this.code = resCode.getCode();
        this.message = resCode.getMsg();
    }
    /**
     * 若没有数据返回，可以人为指定状态码和提示信息
     */
    public ResponseResult(int code, String msg) {
        this.code = code;
        this.message = msg;
    }
    /**
     * 有数据返回时，状态码为200，默认提示信息为“操作成功！”
     */
    public ResponseResult(T data) {
        this.data = data;
        this.code = ResponseCode.SUCCESS.getCode();
        this.message = ResponseCode.SUCCESS.getMsg();
    }
    /**
     * 有数据返回，状态码为 200，人为指定提示信息
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


