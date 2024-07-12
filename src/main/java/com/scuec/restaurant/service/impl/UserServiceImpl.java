package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.User;
import com.scuec.restaurant.service.UserService;
import com.scuec.restaurant.utils.EncryptUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public User userLogin(String loginName, String password) {
        User user = userDao.getUserByLoginName(loginName);
        if (user != null) {
            if (user.getPassword().equals(EncryptUtil.encrypt(password, "MD5")))
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
    public IPage<User> getUsersList(int currentPage, int pageSize, String uId, String loginName, String realName, int role, String phone) {
        return userDao.getUserList(new Page<>(currentPage, pageSize), uId, loginName, realName, role, phone);
    }

    @Override
    public int updateUser(String userId, String loginName, String password, String realName, int role, String phone) {
        return userDao.updateUser(userId, loginName, password, realName, role, phone, 0);
    }

    @Override
    public int deleteUserById(String userId) {
        return userDao.updateUser(userId, null, null, null, -1, null, 1);
    }

    @Override
    public int deleteActualUserById(String userId) {
        return userDao.deleteActualUser(userId);
    }

    @Override
    public int updateUserPassword(String userId, String oldPassword, String newPassword) {
        User user = userDao.getUserById(userId);
        if (user != null){
            if (user.getPassword().equals(EncryptUtil.md5(oldPassword))){
                int res = userDao.updateUser(userId, null, EncryptUtil.md5(newPassword),null,-1,null,-1);
                return res;
            }else
                return -1;
        }else
            return -1;
    }

    @Override
    public int addUser(String loginName, String realName, String password, int role, String phone) {
        User user = userDao.getUserByLoginName(loginName);
        if ( user!=null )
            return -1;
        else
            return userDao.insertUser(loginName, EncryptUtil.md5(password), realName, role, phone);
    }

    @Override
    public int resetUserPassword(String userId) {
        User user = userDao.getUserById(userId);
        if (user != null) {
            int res = userDao.updateUser(userId, null, EncryptUtil.md5("123456"),null,-1,null,-1);
            return res;
        } else
            return -1;
    }
}
