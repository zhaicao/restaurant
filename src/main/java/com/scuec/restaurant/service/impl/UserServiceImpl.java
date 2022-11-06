package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.User;
import com.scuec.restaurant.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User userLogin(String loginName, String password) {
        User user = userDao.getUserByLoginName(loginName);
        if (user != null) {
            if (user.getPassword().equals(password))
                return user;
            else
                return null;
        }else
            return null;
    }

    @Override
    public User getUserById(String userId){
        return userDao.getUserById(userId);
    }

    @Override
    public IPage<User> getUsers(Page page, String uId, String loginName, String realName, int role, String phone) {
        return userDao.getAllUser(page, uId, loginName, realName, role, phone);
    }
}
