package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.dao.StatisticsDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.vo.FoodVO;
import com.scuec.restaurant.entities.vo.RevenueDetailVO;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootTest
@Slf4j
public class StatisticsDaoTest {

    @Autowired
    private StatisticsDao statisticsDao;

    @Test
    public void testGetRevenueList(){
        int current = 1;
        int size = 10;
        IPage<RevenueDetailVO> order  = statisticsDao.getRevenueList(new Page<>(current, size), "", "2022-11-26");
        order.getRecords().forEach(System.out::println);
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
}
