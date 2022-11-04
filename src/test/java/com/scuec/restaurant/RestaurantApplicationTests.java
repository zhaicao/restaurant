package com.scuec.restaurant;

import com.scuec.restaurant.constant.response.ResponseResult;
import com.scuec.restaurant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;

import java.util.Locale;

@SpringBootTest
@Slf4j
class RestaurantApplicationTests {

    //定义日志记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private User user;

    @Autowired
    private MessageSource messageSource;

    @Test
    void contextLoads() {
        logger.info("test");
    }

    @Test
    public void testPerson() {
        System.out.println(user);
    }

    @Test
    public void testEnumApi(){
        User user =new User();
        user.setLoginName("zhangsan");
        user.setLoginName("20");
        System.out.println(ResponseResult.success("成功"));
        System.out.println(ResponseResult.success(user));
        System.out.println(ResponseResult.error("成功"));
    }

    /**
     * 国际化测试
     */
    @Test
    public void test() {
        String message = messageSource.getMessage("login.tip", new String[0], Locale.CHINA);
        log.info("获取的值：【" + message + "】");
    }

    /**
     * 用户服务测试
     */
    @Test
    public void testUserService(){

    }
}
