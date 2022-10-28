package com.scuec.restaurant.constant.exception;

import com.scuec.restaurant.constant.response.ResponseCode;

/**
 * 自定义全局异常处理
 */
public class GlobalException extends RuntimeException {

    private static final long serialVersionUID = 1420538474191333026L;

    private ResponseCode code;

    public GlobalException() {
        super();
    }

    public GlobalException(String msg){
      super(msg);
    }

    public GlobalException(ResponseCode code) {
        super(ResponseCode.ERROR.getMsg());
        this.code = code;
    }

    public GlobalException(ResponseCode code, String msg) {
        super(msg);
        this.code = code;
    }

}
