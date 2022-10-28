package com.scuec.restaurant;

import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class UserDaoTest {

    @Autowired
    private UserDao userDao;

    @Test
    public void testInsert(){
        int res  = userDao.insertUser();
        log.warn(String.valueOf("insert:" + res));
    }

    @Test
    public void testUpdate(){
        int res  = userDao.updateUser();
        log.warn(String.valueOf("update:" + res));
    }

    @Test
    public void testDelete(){
        int res  = userDao.deleteUser();
        log.warn(String.valueOf("delete:" + res));
    }

    @Test
    public void testGetUserById(){
        User user  = userDao.getUserById(4);
        log.warn(String.valueOf("seachOne:" + user.toString()));
    }

    @Test
    public void testGetAllUser(){
        List<User> users  = userDao.getAllUser();
        users.forEach(person -> log.warn(String.valueOf("searchAll:" + person)));
    }

    @Test
    public void testGetUserCount(){
        int count = userDao.getUserCount();
        log.warn("searchCount:" + count);
    }
}
