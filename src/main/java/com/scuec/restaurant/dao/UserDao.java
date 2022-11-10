package com.scuec.restaurant.dao;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao extends BaseMapper<User> {
    /**
     * 新增用户，UserId由mybatis自动生成
     * @param loginName 登录名
     * @param password 密码
     * @param realName 真实姓名
     * @param userRole 用户角色
     * @param userPhone 用户联系电话
     * @return
     */
    int insertUser(String loginName,
                   @Param ("userPassword") String password,
                   String realName,
                   int userRole,
                   String userPhone);

    /**
     * 更新用户信息
     * @param userId 用户Id不可修改,必填
     * @param loginName 登录名，可修改。不修改参数：null
     * @param password 密码，可修改。不修改参数：null
     * @param realName 真实姓名，可修改。不修改参数：null
     * @param userRole 用户角色，可修改。不修改参数:-1
     * @param userPhone 用户角色，可修改。不修改参数：null
     * @param isDel 是否逻辑删除，可修改。不修改参数:-1
     * @return
     */
    int updateUser(String userId,
                   String loginName,
                   @Param ("userPassword") String password,
                   String realName,
                   int userRole,
                   String userPhone,
                   int isDel);

    /**
     * 物理删除用户
     * @return
     */
    @Delete("delete from `employee` where empid = #{userId}")
    int deleteActualUser(String userId);

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
     * @param userId 用户编号，-1查全部用户编号
     * @param loginName 用户登录名，精确查询
     * @param realName 用户真实姓名，模糊查询
     * @param role 用户角色 0管理员，1服务员，2厨师，-1查全部角色
     * @param phone 用户手机，精确
     * @return
     */
    IPage<User> getUserList(@Param("page") Page<User> page,
                           String userId,
                           String loginName,
                           String realName,
                           int role,
                           String phone);

    /**
     * 获取所有用户数,混合查询
     * @param userId 用户编号，-1查全部用户编号
     * @param loginName 用户登录名，精确查询
     * @param realName 用户真实姓名，模糊查询
     * @param role 用户角色 0管理员，1服务员，2厨师，-1查全部角色
     * @param phone 用户手机，精确
     * @return
     */
    Integer getUserCount(String userId,
                         String loginName,
                         String realName,
                         int role,
                         String phone);
}
