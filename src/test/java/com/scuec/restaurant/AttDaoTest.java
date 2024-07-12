package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.AttendanceDao;
import com.scuec.restaurant.dao.MessageDao;
import com.scuec.restaurant.entities.Attendance;
import com.scuec.restaurant.entities.User;
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

//    @Test
//    public void testGetAllUser(){
//        int current = 1;
//        int size = 10;
//        IPage<Attendance> attendance = attendanceDao.getAttendanceList(new Page<>(current, size), "", 1);
//        attendance.getRecords().forEach(person -> log.warn(String.valueOf("searchAll:" + person)));
//        // 打印分页数据
////        System.out.println("TotalPages："+users.getPages());
////        System.out.println("Total："+users.getTotal());
////        System.out.println("Current："+users.getCurrent());
////        System.out.println("Size："+users.getSize());
//    }
}

