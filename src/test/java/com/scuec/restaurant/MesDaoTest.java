package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MessageDao;
import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.Message;
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
                "28",
                1,
                "28号再次催单"
        );
        log.warn(String.valueOf("insert:" + res));
    }

    @Test
    public void testGetMsgSum() {
        int res= messageDao.getMsgCount("1", 2, null, null);
        log.info(String.valueOf(res));
    }

    @Test
    public void testCompleteUrgeMsg() {
        int res = messageDao.updateMessage("'0f359886967011ed856902004c4f4f50', '2c79d90a9fa911ed945302004c4f4f50'",
                "",
                1,
                -1);
    }

    @Test
    public void testGetMesSum() {
        System.out.println(messageDao.getMsgCount(null,1, null, null));
    }
}
