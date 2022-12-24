package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Order;

public interface OrderService {

    int deleteOrderById(String orderId);

    int updateOrdersta(String orderId);

    int updateOrdersta1(String orderId);

    IPage<Order> getOrderList(int currentPage, int pageSize, String orderStatus);


    int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus);

    Order getOrderById(String orderId);
}
