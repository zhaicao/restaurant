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
     * 获取所有用户
     * @return
     */
    List<User> getAllUser();

    /**
     * 获取所有用户数
     * @return
     */
    Integer getUserCount();
}
