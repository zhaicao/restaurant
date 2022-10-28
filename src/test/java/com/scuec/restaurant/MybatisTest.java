package com.scuec.restaurant;

import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class MybatisTest {

    @Resource
    private UserDao userDao;

    @Test
    public void testInsert(){
        int res  = userDao.insertPerson();
        log.warn(String.valueOf("insert:" + res));
    }

    @Test
    public void testUpdate(){
        int res  = userDao.updatePerson();
        log.warn(String.valueOf("update:" + res));
    }

    @Test
    public void testDelete(){
        int res  = userDao.deletePerson();
        log.warn(String.valueOf("delete:" + res));
    }

    @Test
    public void testSeachById(){
        User user  = userDao.getPersonById();
        log.warn(String.valueOf("seachOne:" + user.toString()));
    }

    @Test
    public void testAllSeach(){
        List<User> persons  = userDao.getAllPerson();
        persons.forEach(person -> log.warn(String.valueOf("searchAll:" + person)));
    }

    @Test
    public void testGetPersonCount(){
        int count = userDao.getPersonCount();
        log.warn("searchCount:" + count);
    }
}
