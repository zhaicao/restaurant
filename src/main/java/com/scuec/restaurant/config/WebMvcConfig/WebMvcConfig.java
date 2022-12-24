package com.scuec.restaurant.config.WebMvcConfig;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
    public class WebMvcConfig implements WebMvcConfigurer {
        @Value("${upload.file.location}")
        private String fileLocation;
        @Value("${upload.file.path}")
        private String filePath;

        @Override
        public void addResourceHandlers(ResourceHandlerRegistry registry) {
            //注册配置类，使用addResourceHandlers方法，将本地路径fileLocation映射到filePath路由上。
            registry.addResourceHandler(filePath).addResourceLocations(fileLocation);
            WebMvcConfigurer.super.addResourceHandlers(registry);
        }
    }

