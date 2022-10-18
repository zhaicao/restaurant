package com.scuec.springboot.constant;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler extends BaseController {
    /**
     * 基础异常
     */
    @ExceptionHandler(RetException.class)
    public String baseException(RetException e) {
        return error(e.getRetCode(), e.getMessage());
    }
}
