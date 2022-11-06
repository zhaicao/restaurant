package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.User;

public interface UserService {
    /**
     * 验证用户登录
     * @return 成功返回用户信息，失败返回空
     */
    User userLogin(String loginName, String password);

    /**
     * 根据用户Id查找用户
     * @return 成功返回用户信息，失败返回空
     */
    User getUserById(String loginId);

    /**
     * 获取用户信息
     * @param page 分页对象，new page<>(current, size)
     * @param uId 用户编号
     * @param loginName 用户登录名
     * @param realName 用户真实姓名
     * @param role 用户角色
     * @param phone 用户电话
     * @return IPage对象
     */
    IPage<User> getUsers(Page page, String uId, String loginName, String realName, int role, String phone);
}
