package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
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
        int res  = userDao.insertUser("ZhangSan", "123456", "张三", 1, "15888888888");
        log.warn(String.valueOf("insert:" + res));
    }

    @Test
    public void testUpdate(){
        int res  = userDao.updateUser("6",
                null,
                null,
                null,
                -1,
                null,
                0);
        log.warn(String.valueOf("update:" + res));
    }

    @Test
    public void testDelete(){
        int res  = userDao.deleteActualUser("6");
        log.warn(String.valueOf("delete:" + res));
    }

    @Test
    public void testGetUserById(){
        User user  = userDao.getUserById("4");
        log.warn(String.valueOf("seachOne:" + user.toString()));
    }

    @Test
    public void testGetAllUser(){
        int current = 1;
        int size = 10;
        IPage<User> users  = userDao.getUserList(new Page<>(current, size), "", "", "", 1, "");
        users.getRecords().forEach(person -> log.warn(String.valueOf("searchAll:" + person)));
        // 打印分页数据
        System.out.println("TotalPages："+users.getPages());
        System.out.println("Total："+users.getTotal());
        System.out.println("Current："+users.getCurrent());
        System.out.println("Size："+users.getSize());
    }

    @Test
    public void testGetUserCount(){
        int count = userDao.getUserCount("-1", "", "", -1, "");
        log.warn("searchCount:" + count);
    }

    @Test
    public void testGetUserByLoginName(){
        User user = userDao.getUserByLoginName("admin");
        log.info(user.toString());
    }

}
