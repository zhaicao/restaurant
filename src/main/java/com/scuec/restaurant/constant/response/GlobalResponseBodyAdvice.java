package com.scuec.restaurant.constant.response;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 重写ResponseBodyAdvice，对controller返回的值做包装加工
 * @author Zc
 * @date 2022/10/26
 *
 * comment:RestControllerAdvice对@RestController做增强
 * 1. 全局异常处理
 * 2. 全局数据绑定
 * 3. 全局数据预处理
 */
@RestControllerAdvice(basePackages = "com.scuec.restaurant.controller")
public class GlobalResponseBodyAdvice implements ResponseBodyAdvice<Object> {

    @Autowired
    private ObjectMapper objectMapper;

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof String) {
            // 若返回值为String类型，需要包装为String类型返回。否则会报错
            try {
                ResponseResult<Object> result = new ResponseResult<>().data(body);
                return objectMapper.writeValueAsString(result);
            } catch (JsonProcessingException e) {
                throw new RuntimeException("全局异常处理——序列化String错误");
            }
        } else if (body instanceof ResponseResult) {
            return body;
        }
        return new ResponseResult<>().data(body);
    }
}
