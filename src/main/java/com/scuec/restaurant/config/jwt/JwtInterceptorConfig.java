package com.scuec.restaurant.config.jwt;

import com.scuec.restaurant.constant.handlers.JwtAuthenticationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * JWT拦截器配置
 * WebMvcConfigurerAdapter已废用，平替实现WebMvcConfigurer
 */
@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        //排除登录
        registry.addInterceptor(authenticationInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/*")
                //配置不拦截Swagger
                .excludePathPatterns("/swagger-resources/**", "/webjars/**", "/v2/**", "/swagger-ui.html/**", "/doc.html", "/error");
    }
    //JwtAuthenticationInterceptor拦截器注册到ioc容器中
    @Bean
    public JwtAuthenticationInterceptor authenticationInterceptor() {
        return new JwtAuthenticationInterceptor();
    }
}
