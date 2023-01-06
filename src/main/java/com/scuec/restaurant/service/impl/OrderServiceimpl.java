package com.scuec.restaurant.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.dao.OrderdetDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.service.OrderService;
import com.scuec.restaurant.service.OrderdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;


@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderdetailService orderdetailService;

    @Autowired
    private OrderdetDao orderdetDao;


    @Override
    public int deleteOrderById(String tableId) {
        Order order = orderDao.getOrderBytableId(tableId);
        if (order != null){
            String orderId = order.getOrderId();
            orderDao.deleteOrder(orderId);
            orderdetailService.deleteOrderdetId(orderId);
            return 0;
        }else
            return -1;
    }

//    @Override
//    public int deleteOrderById(String orderId,String tableId) {
//        Order order = orderDao.getOrderBytableId(tableId);
//        if (order == null){
//            return orderDao.deleteOrder(orderId);
//        }else
//            return -1;
//    }

    @Override
    public int updateOrdersta(String orderId,String foodId) {
        orderDao.updateOrdersta(orderId);
        return orderdetDao.updateOrdermenusta(orderId,foodId);
    }

    @Override
    public int updateOrdersta1(String orderId) {
        return orderDao.updateOrdersta1(orderId);
    }


    @Override
    public IPage<Order> getOrderList(int currentPage, int pageSize, String orderStatus, String startDate, String endDate) {
        return orderDao.getOrderList(new Page<>(currentPage, pageSize), orderStatus, startDate, endDate);
    }


    @Override
    public int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus) {
        return orderDao.addOrder(orderId,tableNo,orderPrice,orderStatus);
    }

    @Override
    public Order getOrderBytableId(String tableId) {
        return orderDao.getOrderBytableId(tableId);
    }

    @Override
    public int addOrdermenu(String orderId,double orderPrice) {
        double price = orderDao.getOrderPrice(orderId);
        orderPrice = price + orderPrice;
        return orderDao.updateOrderByorderId(orderId,orderPrice);
    }


}
