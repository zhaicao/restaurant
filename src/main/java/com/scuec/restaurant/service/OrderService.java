package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Order;

import java.math.BigDecimal;

public interface OrderService {

    int deleteOrderById(String tableId);

    int updateOrdersta(String orderId,String foodId);

    int updateOrdersta1(String orderId);

    /**
     * 获取订单列表
     * @param currentPage
     * @param pageSize
     * @param orderStatus 订单状态
     * @param startDate 检索开始时间
     * @param endDate 检索结束时间
     * @return
     */
    IPage<Order> getOrderList(int currentPage, int pageSize, String orderStatus, String startDate, String endDate);


    int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus);


    Order getOrderBytableId(String tableId);

    int addOrdermenu(String orderId,double orderPrice);
}
