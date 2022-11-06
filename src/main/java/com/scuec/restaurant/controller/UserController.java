package com.scuec.restaurant.controller;

import com.scuec.restaurant.constant.annotation.PassToken;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.User;
import com.scuec.restaurant.service.UserService;
import com.scuec.restaurant.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping(value = "/user")
@ApiOperation(value = "用户管理", notes = "用户管理相关业务")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @PassToken
    @GetMapping("/login")
    public Map<String, Object> login(HttpServletRequest request, String loginName, String password, int loginType) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.userLogin(loginName, password);
        if (user != null) {
            user.setPassword(""); //设置密码为空，安全
            map.put("user", user);
            map.put("token", JwtUtils.createToken(String.valueOf(user.getUId()), password));
        }else
            throw new GlobalException(ResponseCode.ERROR, "用户不存在或密码错误");
        return map;
    }

    @GetMapping("/error")
    public Map<String, Object> simulatedError(){
        int i = 9 / 0;
        return new HashMap<>();
//        throw new GlobalException(ResponseCode.ERROR, "自定义异常");
    }

    @GetMapping("/success")
    public List<User> simulatedSuccess(){
        List<User> userList = new ArrayList<>();
        userList.add(new User(1,
                "zhangsan",
                "123456",
                "张三",
                1,
                "15800000000",
                0));
        userList.add(new User(2, "lisi", "123456", "李四", 1, "15800000000", 0));
        userList.add(new User(3, "wangwu", "123456", "王五", 1, "15800000000", 0));
        return userList;
    }
}
