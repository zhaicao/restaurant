package com.scuec.springboot.constant;

public class RetException extends RuntimeException {
    private static final long serialVersionUID = -649224303322031383L;

    private RetCodeEnum retCode;

    public RetException() {
        super();
    }

    public RetException(RetCodeEnum retCode) {
        super(retCode.getMsg());
        this.retCode = retCode;
    }

    public RetException(RetCodeEnum retCode, String msg) {
        super(msg);
        this.retCode = retCode;
    }

    public RetCodeEnum getRetCode() {
        return this.retCode;
    }

    public void setRetCode(RetCodeEnum retCode) {
        this.retCode = retCode;
    }
}
