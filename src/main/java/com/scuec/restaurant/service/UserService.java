package com.scuec.restaurant.service;

import com.scuec.restaurant.entities.User;

public interface UserService {
    /**
     * 验证用户登录
     * @return 成功返回用户信息，失败返回空
     */
    public User userLogin();
}
