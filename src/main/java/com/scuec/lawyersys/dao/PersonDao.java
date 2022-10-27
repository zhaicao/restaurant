package com.scuec.lawyersys.dao;

import com.scuec.lawyersys.entities.Person;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonDao {
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
    Person getPersonById();

    /**
     * 获取所有用户
     * @return
     */
    List<Person> getAllPerson();

    Integer getPersonCount();
}
