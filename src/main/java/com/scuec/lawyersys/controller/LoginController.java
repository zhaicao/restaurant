package com.scuec.lawyersys.controller;

import com.scuec.lawyersys.constant.exception.GlobalException;
import com.scuec.lawyersys.constant.response.ResponseCode;
import com.scuec.lawyersys.service.LawyerInfoService;
import com.scuec.lawyersys.service.LoginLogService;
import com.scuec.lawyersys.service.ManagerInfoService;
import com.scuec.lawyersys.service.UserRoleService;
import com.scuec.lawyersys.utils.JwtUtils;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping(value = "/lawyer")
@ApiOperation(value = "律师登录", notes = "获取lawyer或manager信息")
@Slf4j
public class LoginController {

    @Autowired
    private LawyerInfoService lawyerInfoService;
    @Autowired
    private ManagerInfoService managerInfoService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private LoginLogService loginLogService;

    @GetMapping("/login")
    public Map<String, Object> login(HttpServletRequest request, String username, String password, int loginType) {
//        LawyerInfo lawyerInfo = lawyerInfoService.getLawyerInfo();
        Map<String, Object> map = new HashMap<>();
        map.put("token", JwtUtils.createToken(username, password));
//        return ResponseResult.success(map);
        return map;

//        if (lawyerInfo != null) {
//            if(lawyerInfo.getPassword().equals(password)){
//                log.info( username + "login successfully");
//                return ResponseResult.success(JwtUtils.createToken(lawyerInfo.getLiid().toString(), lawyerInfo.getName(), lawyerInfo.getPassword()));
//            }else
//                return ResponseResult.error("密码错误");
//        } else
//            return ResponseResult.error("用户不存在");
    }

    @GetMapping("/error")
    public Map<String, Object> simulatedError(){
//        Map<String, Object> map = new HashMap<>();
//        map.put("comment", "正经消息");
//        return map;
        throw new GlobalException(ResponseCode.ERROR, "自定义异常");
    }
}
