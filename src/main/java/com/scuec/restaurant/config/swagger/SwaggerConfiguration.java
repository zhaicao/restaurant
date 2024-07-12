package com.scuec.restaurant.config.swagger;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * Swagger配置
 */
@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    //标题
    @Value("${swagger.title}")
    private String title;
    //描述
    @Value("${swagger.description}")
    private String description;
    //版本
    @Value("${swagger.version}")
    private String version;
    //作者
    @Value("${swagger.author}")
    private String author;

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("接口文档")
                .apiInfo(apiInfo())
                .select()
                //.apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                .apis(RequestHandlerSelectors.basePackage("com.scuec.restaurant.controller")) // 指定路径
                .paths(PathSelectors.any())
                .build()
                //是否使用默认响应消息
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title(title)
                .description(description)
                .contact(new Contact(author, "", ""))
                .version(version)
                .build();
    }
}
