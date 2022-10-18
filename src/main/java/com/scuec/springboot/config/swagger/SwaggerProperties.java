package com.scuec.springboot.config.swagger;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "swagger")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SwaggerProperties {
    //标题
    private String title;
    //描述
    private String description;
    //版本
    private String version;
    //作者
    private String author;
}
