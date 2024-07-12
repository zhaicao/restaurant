package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MessageDao;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.dao.StatisticsDao;
import com.scuec.restaurant.dao.TableDao;
import com.scuec.restaurant.entities.vo.RevenueChartVO;
import com.scuec.restaurant.entities.vo.RevenueDetailVO;
import com.scuec.restaurant.entities.vo.RevenuePanelGroupVO;
import com.scuec.restaurant.service.StatisticsService;
import com.scuec.restaurant.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class StatisticsServiceImpl implements StatisticsService {

    @Autowired
    private StatisticsDao statisticsDao;
    @Autowired
    private OrderDao orderDao;
    @Autowired
    private MessageDao messageDao;
    @Autowired
    private TableDao tableDao;

    @Override
    public IPage<RevenueDetailVO> getRevenueOrderList(int currentPage, int pageSize, String startDate, String endDate) {
        IPage<RevenueDetailVO> revenueDetailVOIPage =
                statisticsDao.getRevenueList(
                        new Page<>(currentPage, pageSize),
                        startDate,
                        endDate);
        return revenueDetailVOIPage;
    }

    @Override
    public RevenueChartVO getRevenueChart(String startDate, String endDate) {
        List<String> legendList = new ArrayList<>();
        List<String> categoryList = new ArrayList<>();
        List<Double> revenueList = new ArrayList<>();
        List<Integer> orderQuantityList = new ArrayList<>();
        legendList.add("营业额（元）");
        legendList.add("订单数（笔）");
        if (startDate == null) startDate = "";
        if (endDate == null) endDate = "";
        // ==========category时间处理============
        LocalDate currentDate = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        // 开始和结束时间均为空
        if (startDate.isEmpty() && endDate.isEmpty()) {
            categoryList = DateUtil.getDateList(30, currentDate.format(formatter));
        } else if (!startDate.isEmpty() && endDate.isEmpty()) {
            // 开始时间距今相差超过30天
            if (DateUtil.daysGap(startDate, currentDate.format(formatter)) > 30) {
                categoryList = DateUtil.getDateList(30, currentDate.format(formatter));
            }
            else {
                categoryList = DateUtil.getDateList(startDate, currentDate.format(formatter));
            }
        } else if (startDate.isEmpty() && !endDate.isEmpty()) {
            categoryList = DateUtil.getDateList(30, endDate);
        } else {
            // 开始和结束时间相差超过30天
            if (DateUtil.daysGap(startDate, endDate) > 30) {
                categoryList = DateUtil.getDateList(30, endDate);
            }
            else {
                categoryList = DateUtil.getDateList(startDate, endDate);
            }
        }
        // ==========revenue和orderQuantity处理============
        List<RevenueDetailVO> revenueDate =
                getRevenueOrderList(1, 1000, startDate, endDate).getRecords();
        int flap = 0;
        for (String date : categoryList) {
            flap = 0;
            for (RevenueDetailVO revenueItem: revenueDate) {
                if (date.equals(revenueItem.getOrderDate())) {
                    revenueList.add(revenueItem.getOrderTotalPrice());
                    orderQuantityList.add(revenueItem.getOrderQuantity());
                    flap += 1;
                }
            }
            if (flap == 0) {
                revenueList.add(0.00);
                orderQuantityList.add(0);
            }
        }
        return new RevenueChartVO(legendList, categoryList, revenueList, orderQuantityList);
    }

    @Override
    public RevenuePanelGroupVO getRevenuePanelGroup(String startDate,
                                                    String endDate) {
        Map<String, Object> orderSumAndPrice =
                orderDao.getOrderSumAndPriceByDate(startDate, endDate);

        return new RevenuePanelGroupVO(
                tableDao.getUsedTableSum(),
                Integer.parseInt(orderSumAndPrice.get("orderSum").toString()),
                messageDao.getMsgCount(null, 1, startDate, endDate),
                Double.parseDouble(orderSumAndPrice.get("orderTotalPrice").toString())
        );
    }
}
