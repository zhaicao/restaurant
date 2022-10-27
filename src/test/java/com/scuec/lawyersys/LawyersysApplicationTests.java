package com.scuec.lawyersys;

import com.scuec.lawyersys.constant.response.ResponseResult;
import com.scuec.lawyersys.entities.Person;
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
class LawyersysApplicationTests {

    //定义日志记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Person person;

    @Autowired
    private MessageSource messageSource;

    @Test
    void contextLoads() {
        logger.info("test");
    }

    @Test
    public void testPerson() {
        System.out.println(person);
    }

    @Test
    public void testEnumApi(){
        Person person =new Person();
        person.setLastName("zhangsan");
        person.setAge(20);
        System.out.println(ResponseResult.success("成功"));
        System.out.println(ResponseResult.success(person));
        System.out.println(ResponseResult.error("成功"));


//        logger.info(String.valueOf(ResponseResult.success("成功")));
//        logger.info(ResponseResult.success(person));
//        logger.error(ResponseResult.error("账号或密码错误").toString());
    }

    /**
     * 国际化测试
     */
    @Test
    public void test() {
        String message = messageSource.getMessage("login.tip", new String[0], Locale.CHINA);
        log.info("获取的值：【" + message + "】");
    }
}
