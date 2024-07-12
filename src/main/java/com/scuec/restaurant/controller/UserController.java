package com.scuec.restaurant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.annotation.PassToken;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.User;
import com.scuec.restaurant.service.UserService;
import com.scuec.restaurant.utils.JwtUtils;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/user")
@ApiOperation(value = "用户管理", notes = "用户管理相关业务")
@Slf4j
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/login")
    @PassToken
    @ApiOperation(value = "用户登录", notes = "用户登录")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "loginName", value = "用户登录名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "password", value = "用户登录密码", required = true, dataType = "String", paramType = "query")
    })
    public Map<String, Object> login(String loginName, String password) {
        Map<String, Object> map = new HashMap<>();
        User user = userService.userLogin(loginName, password);
        if (user != null) {
            map.put("user", user);
            map.put("token", JwtUtils.createToken(user.getUId(), user.getPassword()));
        }else
            throw new GlobalException(ResponseCode.ERROR, "用户不存在或密码错误");
        return map;
    }

    @GetMapping("/getUserById")
    @ApiOperation(value = "通过Id获取用户信息", notes = "用户密码已加密，不必设置为空")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    public User getUserById(String userId){
        User user = userService.getUserById(userId);
        return user;
    }

    /**
     * 获取用户list
     * @param currentPage
     * @param pageSize
     * @param userId 用户UUID，精确
     * @param loginName 用户登录名，精确
     * @param realName 用户真实姓名，模糊查询
     * @param role 用户角色 0：管理员；1：服务员；2：厨师。-1查询全部
     * @param phone 用户联系电话，精确
     * @return
     */
    @GetMapping("/getUserList")
    @ApiOperation(value = "多条件获取用户List", notes = "多条件查询用户list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "userId", value = "用户ID，精确查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "loginName", value = "用户登录名，精确查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "realName", value = "用户真实姓名，模糊查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "role", value = "用户角色，-1表示查询全部", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "phone", value = "用户联系方式，精确查询", required = true, dataType = "String", paramType = "query")
    })
    public IPage<User> getUserList(int currentPage,
                                   int pageSize,
                                   String userId,
                                   String loginName,
                                   String realName,
                                   int role,
                                   String phone){

        return userService.getUsersList(currentPage, pageSize, userId, loginName, realName, role, phone);
    }

    @PostMapping("/addUser")
    @ApiOperation(value = "新增单个用户", notes = "新增一个用户")
    public String addUser(@RequestBody User user){
        int result = userService.addUser(user.getLoginName(),
                                        user.getRealName(),
                                        user.getPassword(),
                                        user.getRole(),
                                        user.getPhone());
        if (result == 1)
            return "success";
        else if (result == -1)
            throw new GlobalException(ResponseCode.ERROR, "UserLoginName already exists, userName:" + user.getLoginName());
        else
            throw new GlobalException(ResponseCode.ERROR, "Add User Error");
    }


    @DeleteMapping("/deleteUserById/{userId}")
    @ApiOperation(value = "通过用户Id删除（更新isdel）用户", notes = "通过用户Id删除（更新isdel）用户")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    public String deleteUserById(@PathVariable String userId){
        int res = userService.deleteUserById(userId);
        if (res != 0 )
            return "success";
        else
            throw new GlobalException(ResponseCode.ERROR, "Delete User Error, userId:" + userId);
    }

    @PutMapping("/updateUser/{userId}")
    @ApiOperation(value = "通过用户Id更新用户信息", notes = "真实姓名，角色和联系方式可修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query"),
    })
    public String updateUser(@PathVariable(value = "userId") String userId, User user){
        int res = userService.updateUser(userId, null, null, user.getRealName(), user.getRole(), user.getPhone());
        if (res == 1)
            return "success";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update User Error, userId:" + userId);
    }

    @PutMapping("/updateUserPassword")
    @ApiOperation(value = "通过用户Id更新用户密码", notes = "仅修改用户密码")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "oldPassword", value = "用户旧密码", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "newPassword", value = "用户新密码", required = true, dataType = "String", paramType = "query"),
    })
    public String updateUserPassword(String userId, String oldPassword, String newPassword){
        int res = userService.updateUserPassword(userId, oldPassword, newPassword);
        if (res == 1)
            return "success";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update UserPassword Error, userId:" + userId);
    }

    @PutMapping("/resetUserPassword/{userId}")
    @ApiOperation(value = "通过用户Id重置用户密码", notes = "密码重置为123456")
    @ApiImplicitParam(name = "userId", value = "用户ID", required = true, dataType = "String", paramType = "query")
    public String resetUserPassword(@PathVariable String userId){
        int res = userService.resetUserPassword(userId);
        if (res == 1)
            return "success";
        else
            throw new GlobalException(ResponseCode.ERROR, "Reset UserPassword Error, userId:" + userId);
    }


    /**
     * 模拟异常测试接口
     * @return
     */
    @GetMapping("/error")
    public Map<String, Object> simulatedError(){
        int i = 9 / 0;
        return new HashMap<>();
//        throw new GlobalException(ResponseCode.ERROR, "自定义异常");
    }
}
