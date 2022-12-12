package com.scuec.restaurant.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OrderDao orderDao;


    @Override
    public int deleteOrderById(String orderId) {
        return orderDao.deleteOrder(orderId);
    }

    @Override
    public int updateOrdersta(String orderId) {
        return orderDao.updateOrdersta(orderId);
    }

    @Override
    public int updateOrdersta1(String orderId) {
        return orderDao.updateOrdersta1(orderId);
    }


    @Override
    public IPage<Order> getOrderList(int currentPage, int pageSize, String orderStatus) {
        return orderDao.getOrderList(new Page<>(currentPage, pageSize),orderStatus);
    }

    @Override
    public int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus) {
        return orderDao.addOrder(orderId,tableNo,orderPrice,orderStatus);
    }

    @Override
    public Order getOrderById(String orderId) {
        return orderDao.getOrderById(orderId);
    }


}
