package com.scuec.springboot.constant;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 统一接口返回参数
 */
@Slf4j
public abstract class BaseController {
    public static final String CODE = "code";
    public static final String MSG = "msg";
    public static final String DATA = "data";

    protected static String success() {
        final Map<String, Object> result = Maps.newHashMap();
        result.put(CODE, RetCodeEnum.SUCCESS.getCode());
        result.put(MSG, RetCodeEnum.SUCCESS.getMsg());
        return JSON.toJSONString(result);
    }

    protected static String success(Object data) {
        final Map<String, Object> result = Maps.newHashMap();
        result.put(CODE, RetCodeEnum.SUCCESS.getCode());
        result.put(MSG, RetCodeEnum.SUCCESS.getMsg());
        result.put(DATA, data);
        return JSON.toJSONString(result);
    }

    protected static String error(RetCodeEnum code) {
        final Map<String, Object> result = Maps.newHashMap();
        result.put(CODE, code.getCode());
        result.put(MSG, code.getMsg());
        return JSON.toJSONString(result);
    }

    protected static String error(RetCodeEnum code, String msg) {
        final Map<String, Object> result = Maps.newHashMap();
        result.put(CODE, code.getCode());
        result.put(MSG, msg);
        return JSON.toJSONString(result);
    }
}