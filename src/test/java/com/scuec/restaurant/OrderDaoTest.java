package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.dao.UserDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class OrderDaoTest {

    @Autowired
    private OrderDao orderDao;

//    @Test
//    public void testInsert(){
//        int res  = orderDao.addOrder("11", 11, "1");
//        log.warn(String.valueOf("insert:" + res));
//    }

    @Test
    public void testGetAllOrder(){
        int current = 1;
        int size = 10;
        IPage<Order> order  = orderDao.getOrderList(new Page<>(current, size), "");
        order.getRecords().forEach(person -> log.warn(String.valueOf("searchAll:" + person)));
        // 打印分页数据
        System.out.println("TotalPages："+order.getPages());
        System.out.println("Total："+order.getTotal());
        System.out.println("Current："+order.getCurrent());
        System.out.println("Size："+order.getSize());
    }
}
