package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.User;

public interface UserService {
    /**
     * 验证用户登录
     * @return 成功返回用户信息，失败返回空
     */
    User userLogin(String loginName, String password);

    /**
     * 根据用户UUId查找用户
     * @return 成功返回用户信息，失败返回空
     */
    User getUserById(String userId);

    /**
     * 获取用户信息
     * @param currentPage 当前页
     * @param pageSize 每页显示多少条
     * @param useId 用户编号
     * @param loginName 用户登录名
     * @param realName 用户真实姓名
     * @param role 用户角色
     * @param phone 用户电话
     * @return IPage对象
     */
    IPage<User> getUsersList(int currentPage, int pageSize, String useId, String loginName, String realName, int role, String phone);

    /**
     * 更新用户信息
     * @param userId 用户Id不可修改,必填
     * @param loginName 登录名，可修改。不修改参数：null
     * @param password 密码，可修改。不修改参数：null
     * @param realName 真实姓名，可修改。不修改参数：null
     * @param userRole 用户角色，可修改。不修改参数:-1
     * @param userPhone 用户角色，可修改。不修改参数：null
     * @return
     */
    int updateUser(String userId, String loginName, String password, String realName, int userRole, String userPhone);

    /**
     * 逻辑删除用户，isdel设置为1
     * @param userId 用户Id
     * @return
     */
    int deleteUserById(String userId);

    /**
     * 实际删除用户
     * @param userId 用户Id
     * @return
     */
    int deleteActualUserById(String userId);

    /**
     * 修改用户密码
     * @param userId 用户IP
     * @param oldPassword 用户旧密码
     * @param newPassword 用户新密码
     * @return
     */
    int updateUserPassword(String userId, String oldPassword, String newPassword);

    /**
     *
     * @param loginName 登录名
     * @param realName  真实姓名
     * @param password 登录密码
     * @param role 角色
     * @param phone 联系电话
     * @return
     */
    int addUser(String loginName, String realName, String password, int role, String phone);

    /**
     * 重置用户密码
     * @param userId 用户Id
     * @return 成功返回1，失败返回-1
     */
    int resetUserPassword(String userId);
}
