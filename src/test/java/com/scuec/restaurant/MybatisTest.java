package com.scuec.restaurant;

import com.scuec.restaurant.dao.PersonDao;
import com.scuec.restaurant.entities.Person;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.List;

@SpringBootTest
@Slf4j
public class MybatisTest {

    @Resource
    private PersonDao personMapper;

    @Test
    public void testInsert(){
        int res  = personMapper.insertPerson();
        log.warn(String.valueOf("insert:" + res));
    }

    @Test
    public void testUpdate(){
        int res  = personMapper.updatePerson();
        log.warn(String.valueOf("update:" + res));
    }

    @Test
    public void testDelete(){
        int res  = personMapper.deletePerson();
        log.warn(String.valueOf("delete:" + res));
    }

    @Test
    public void testSeachById(){
        Person person  = personMapper.getPersonById();
        log.warn(String.valueOf("seachOne:" + person.toString()));
    }

    @Test
    public void testAllSeach(){
        List<Person> persons  = personMapper.getAllPerson();
        persons.forEach(person -> log.warn(String.valueOf("searchAll:" + person)));
    }

    @Test
    public void testGetPersonCount(){
        int count = personMapper.getPersonCount();
        log.warn("searchCount:" + count);
    }
}
