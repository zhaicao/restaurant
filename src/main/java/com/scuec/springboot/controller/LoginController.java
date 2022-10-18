package com.scuec.springboot.controller;

import com.scuec.springboot.constant.BaseController;
import com.scuec.springboot.constant.RetCodeEnum;
import com.scuec.springboot.constant.RetException;
import com.scuec.springboot.entity.LawyerInfo;
import com.scuec.springboot.service.LawyerInfoService;
import com.scuec.springboot.service.LoginLogService;
import com.scuec.springboot.service.ManagerInfoService;
import com.scuec.springboot.service.UserRoleService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.security.auth.Subject;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.Session;


@RestController
@RequestMapping(value = "/lawyer")
@ApiOperation(value = "律师登录", notes = "获取lawyer或manager信息")
@Slf4j
public class LoginController extends BaseController {

    @Autowired
    private LawyerInfoService lawyerInfoService;
    @Autowired
    private ManagerInfoService managerInfoService;
    @Autowired
    private UserRoleService userRoleService;
    @Autowired
    private LoginLogService loginLogService;

    @RequestMapping("/login")
    public String login(HttpServletRequest request, String username, String password, int loginType) {
        LawyerInfo lawyerInfo = lawyerInfoService.getLawyerInfo();
        if (lawyerInfo != null) {
            if(lawyerInfo.getPassword().equals(password)){
                Subject currentUser = SecurityUtils.getSubject();
                Session session = currentUser.getSession();
                if (session)
                    throw new RetException(RetCodeEnum.ERROR);
                log.info( username + "login successfully");
                return success(lawyerInfo);
            }else
                return error(RetCodeEnum.ERROR,"账号或密码错误");
        } else
            return error(RetCodeEnum.ERROR,"账号或密码错误");
    }
}
