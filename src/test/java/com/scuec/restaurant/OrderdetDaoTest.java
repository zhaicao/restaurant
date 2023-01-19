package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.dao.OrderdetDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.Orderdetail;
import com.scuec.restaurant.entities.vo.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class OrderdetDaoTest {

    @Autowired
    private OrderdetDao orderdetDao;

    @Test
    public void testGetOrderList(){
        List<Orderdetail> foods  = orderdetDao.getFoodListByOrderId("e02aa47b966911ed856902004c4f4f51");
        foods.forEach(food -> log.warn(String.valueOf("searchAll:" + food.getMenu())));
    }
}
