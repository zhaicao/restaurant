package com.scuec.restaurant;

import com.scuec.restaurant.dao.MenuDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MenuDaoTest {

    @Autowired
    private MenuDao menuDao;

    @Test
    public void testUpdate(){
        int res  = menuDao.updateMenu("2",
                null,
                null,
                1.11,
                null,
                1,
                0);
        log.warn(String.valueOf("update:" + res));
    }
}
