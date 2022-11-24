package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MessageDao;
import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MesDaoTest {

    @Autowired
    private MessageDao messageDao;

    @Test
    public void testAddMessage(){
        int res  = messageDao.addMessage("1",
                "1",
                "777"
        );
        log.warn(String.valueOf("insert:" + res));
    }
}
