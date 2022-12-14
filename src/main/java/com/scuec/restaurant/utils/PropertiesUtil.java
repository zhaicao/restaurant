package com.scuec.restaurant.utils;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

/**
 * 非Springboot方式获取application.yml中配置
 */
@Slf4j
public class PropertiesUtil {
    private static Properties props;

    static {
        String fileName = "application.yml";
        props = new Properties();
        try {
            props.load(new InputStreamReader(PropertiesUtil.class.getClassLoader().getResourceAsStream(fileName),"UTF-8"));
        } catch (IOException e) {
            log.error("配置文件读取异常",e);
        }
    }

    public static String getProperty(String key){
        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            return null;
        }
        return value.trim().replace("\"", "");
    }

    public static String getProperty(String key,String defaultValue){

        String value = props.getProperty(key.trim());
        if(StringUtils.isBlank(value)){
            value = defaultValue;
        }
        return value.trim();
    }

    public static void main(String[] args) {
        System.out.println(PropertiesUtil.getProperty("upload.file.basic-path"));
    }
}
