package com.scuec.restaurant;

import com.scuec.restaurant.dao.AttendanceDao;
import com.scuec.restaurant.dao.MessageDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class AttDaoTest {

    @Autowired
    private AttendanceDao attendanceDao;

//    @Test
//    public void testAddStart() {
//        int res = attendanceDao.addStart("2");
//        log.warn(String.valueOf("add:" + res));
//    }


//    @Test
//    public void testAddLeave() {
//        int res = attendanceDao.addLeave("100"
//        );
//        log.warn(String.valueOf("insert:" + res));
//    }

    @Test
    public void updateFinish(){
        int res  = attendanceDao.updateFinish("8");
        log.warn(String.valueOf("update:" + res));
    }
}

