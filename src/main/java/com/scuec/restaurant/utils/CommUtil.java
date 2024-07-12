package com.scuec.restaurant.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.util.StringUtils;

import java.io.IOException;

public class CommUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    // 对象转字符串
    public static <T> String obj2string(T obj) {
        if (obj == null) {
            return null;
        }
        try {
            return obj instanceof String ? (String) obj : objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    // 字符串转对象
    public static <T> T string2Obj(String str, Class<T> clazz) {
        if (StringUtils.hasText(str) || clazz == null) {
            return null;
        }
        try {
            return clazz.equals(String.class) ? (T) str : objectMapper.readValue(str, clazz);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
    /**
     * 数组转字符串
     * @param strArr 格式[1, 2, 3]
     * @return '1','2','3'
     */
    public static String arr2Str(String[] strArr) {
        StringBuffer str = new StringBuffer();
        for (String s : strArr) {
            if (str.length() !=0 )
                str.append(", ");
            str.append("'" + s + "'");
        }
        return str.toString();
    }
}
