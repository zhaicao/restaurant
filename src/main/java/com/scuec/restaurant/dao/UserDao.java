package com.scuec.restaurant.dao;


import com.scuec.restaurant.entities.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserDao {
    /**
     *
     * @return
     */
    int insertPerson();

    int updatePerson();

    int deletePerson();

    /**
     * 根据id查询用户信息
     */
    User getPersonById();

    /**
     * 获取所有用户
     * @return
     */
    List<User> getAllPerson();

    Integer getPersonCount();
}
