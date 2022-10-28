package com.scuec.restaurant.config.mvc;

import com.scuec.restaurant.constant.handlers.GlobalHandlerInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.w3c.dom.UserDataHandler;

@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * 注册拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new GlobalHandlerInterceptor());
    }
}
