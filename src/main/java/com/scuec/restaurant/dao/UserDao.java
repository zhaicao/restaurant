package com.scuec.restaurant.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<User> {
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
    User getUserById(String userId);

    /**
     * 根据loginName查询用户信息
     */
    User getUserByLoginName(String loginName);

    /**
     * 获取所有用户,混合查询
     * @param uId 用户编号，-1查全部用户编号
     * @param loginName 用户登录名，精确查询
     * @param realName 用户真实姓名，模糊查询
     * @param role 用户角色 0管理员，1服务员，2厨师，-1查全部角色
     * @param phone 用户手机，精确
     * @return
     */
    IPage<User> getAllUser(@Param("page") Page<User> page,
                           String uId,
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
    Integer getUserCount(String uId,
                         String loginName,
                         String realName,
                         int role,
                         String phone);
}
