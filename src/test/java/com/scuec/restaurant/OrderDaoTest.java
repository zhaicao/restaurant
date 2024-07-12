package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.vo.FoodVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

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
    public void testGetOrderList(){
        int current = 1;
        int size = 10;
        IPage<Order> order  = orderDao.getOrderList(new Page<>(current, size), "", "2022-11-25", "");
        order.getRecords().forEach(detail -> log.warn(String.valueOf("searchAll:" + detail)));
        // 打印分页数据
        System.out.println("TotalPages："+order.getPages());
        System.out.println("Total："+order.getTotal());
        System.out.println("Current："+order.getCurrent());
        System.out.println("Size："+order.getSize());
    }

    @Test
    public void testGetNewFoodList(){
        int current = 1;
        int size = 10;
        IPage<FoodVO> order  = orderDao.getFoodList(new Page<>(current, size), 0, "", "米苏", "2022-11-26", "");
        order.getRecords().forEach(foods -> log.warn(String.valueOf("searchAll:" + foods)));
    }

    @Test
    public void testUnitCollection() {
        List<String> l1 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        l1.add("c");

        List<String> l2 = new ArrayList<>();
        l1.add("a");
        l1.add("b");
        l1.add("c");
        l1.add("d");
        l1.add("e");

        // 使用steam去重
        List<String> result = (List<String>) Stream.of(l1, l2)
                .flatMap(Collection::stream)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(result);
    }

    @Test
    public void testGetOrderSumAndPrice() {
        Map<String, Object>  map = orderDao.getOrderSumAndPriceByDate("2023-02-18","2023-02-18");
        System.out.println(Double.parseDouble(map.get("orderTotalPrice").toString()));
    }
}
