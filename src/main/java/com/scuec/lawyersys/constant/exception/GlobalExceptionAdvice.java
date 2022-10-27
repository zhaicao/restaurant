package com.scuec.lawyersys.constant.exception;

import com.scuec.lawyersys.constant.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionAdvice {
    // 处理其他所有异常
    @ExceptionHandler(Exception.class)
    public ResponseResult<Object> handleException(Exception e) throws Exception {
        log.info("RuntimeException");
        log.error(e.getMessage(), e);
        // 如果某个自定义异常有@ResponseStatus注解，就继续抛出
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        // 防止用户看到详细的异常信息
        return new ResponseResult<>().message(e.getMessage());
    }

    // 处理自定义异常
    @ExceptionHandler(GlobalException.class)
    public ResponseResult<Object> handleGlobleException(Exception e) throws Exception {
        log.info("自定义异常");
        log.error(e.getMessage(), e);
        // 如果某个自定义异常有@ResponseStatus注解，就继续抛出
        if (AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class) != null) {
            throw e;
        }
        // 防止用户看到详细的异常信息
        return new ResponseResult().error(e.getMessage());
    }
}
