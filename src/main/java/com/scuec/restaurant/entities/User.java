package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 将配置文件中配置的每一个属性的值，映射到这个组件中
 * @ConfigurationProperties()：告诉springboot将奔雷中的所有属性和配置文件中相关的配置进行绑定
 * prefix 配置文件中那个属性下的值
 * @PropertySource(value = {"classpath:person.properties"}) //指定配置文件
 */
//
@Data
@Component
public class User {
    private String uId;

    private String loginName;

    private String password;

    private String realName;

    private int role;

    private String phone;

    private int idDel;

}
