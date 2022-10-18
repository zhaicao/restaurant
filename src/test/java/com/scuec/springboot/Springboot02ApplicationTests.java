package com.scuec.springboot;

import com.scuec.springboot.entity.Person;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
class Springboot02ApplicationTests {

    //定义日志记录器
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private Person person;

    @Test
    void contextLoads() {
        logger.info("test");
    }

    @Test
    public void testPerson() {
        System.out.println(person);
    }
}
