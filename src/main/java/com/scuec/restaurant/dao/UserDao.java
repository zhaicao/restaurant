package com.scuec.restaurant.dao;


import com.scuec.restaurant.entities.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     * 新增用户
     * @return
     */
    int insertUser(@Param("userName") String userLoginName,
                   @Param ("userPassword") String userPwd,
                   String userRealName,
                   int userRole,
                   String userPhone);

    /**
     * 更新用户
     * @return
     */
    int updateUser();

    /**
     * 物理删除用户
     * @return
     */
    int deleteUser();

    /**
     * 根据id查询用户信息
     */
    User getUserById(int userId);

    /**
     * 获取所有用户,混合查询
     * @param uId 用户编号，-1查全部用户编号
     * @param loginName 用户登录名，精确查询
     * @param realName 用户真实姓名，模糊查询
     * @param role 用户角色 0管理员，1服务员，2厨师，-1查全部角色
     * @param phone 用户手机，精确
     * @return
     */
    List<User> getAllUser(int uId,
                          String loginName,
                          String realName,
                          int role,
                          String phone);

    /**
     * 获取所有用户数,混合查询
     * @param uId 用户编号，-1查全部用户编号
     * @param loginName 用户登录名，精确查询
     * @param realName 用户真实姓名，模糊查询
     * @param role 用户角色 0管理员，1服务员，2厨师，-1查全部角色
     * @param phone 用户手机，精确
     * @return
     */
    Integer getUserCount(int uId,
                         String loginName,
                         String realName,
                         int role,
                         String phone);
}
