package com.scuec.restaurant.constant.handlers;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.constant.annotation.PassToken;
import com.scuec.restaurant.entities.User;
import com.scuec.restaurant.service.UserService;
import com.scuec.restaurant.utils.JwtUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;

/**
 * JWT拦截实现
 */
@Slf4j
public class JwtAuthenticationInterceptor implements HandlerInterceptor {

    @Value("${jwt.header}")
    private String JWTHeader;
    @Autowired
    private UserService userService;

    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object object) throws Exception {
        // 从请求头中取出token
        String token = httpServletRequest.getHeader(JWTHeader);
        // 如果不是映射到方法直接通过
        if (!(object instanceof HandlerMethod)) {
            return true;
        }
        HandlerMethod handlerMethod = (HandlerMethod) object;
        Method method = handlerMethod.getMethod();
        //检查是否有passtoken注释，有则跳过认证
        if (method.isAnnotationPresent(PassToken.class)) {
            PassToken passToken = method.getAnnotation(PassToken.class);
            if (passToken.required()) {
                return true;
            }
        }
        //默认全部检查
        else {
            String requestURL = httpServletRequest.getRequestURI();
            log.info("RequestURL： {} 被jwt拦截需验证", requestURL);
            // 执行认证
            if (token == null) {
                throw new GlobalException(ResponseCode.ERROR, "JWT登录失效");
            }
            // 获取 token 中的 userId
            String userIdByToken = JwtUtils.getAudience(token);
            User user = userService.getUserById(userIdByToken);
            if (user == null)
                throw new GlobalException(ResponseCode.ERROR, "JWT用户不存在");
            // 验证 token
            JwtUtils.verifyToken(token, userIdByToken+user.getPassword());
            //获取载荷内容
            String userId = JwtUtils.getClaimByName(token, "userId").asString();
            //放入attribute以便后面调用
            httpServletRequest.setAttribute("userId", userId);
            return true;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest,
                           HttpServletResponse httpServletResponse,
                           Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest,
                                HttpServletResponse httpServletResponse,
                                Object o, Exception e) throws Exception {
    }

}
