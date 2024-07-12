package com.scuec.restaurant.service.impl;


import com.scuec.restaurant.dao.OrderdetDao;
import com.scuec.restaurant.entities.Orderdetail;
import com.scuec.restaurant.service.OrderdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderdetailServiceimpl implements OrderdetailService {

    @Autowired
    private OrderdetDao orderdetDao;

    @Override
    public int deleteOrderdetId(String orderId) {
        return orderdetDao.deleteOrderdetId(orderId);
    }

    @Override
    public void addOrderdet(String orderId, String foodId, int odAmount, double odPrice, int odStatus) {
        orderdetDao.addOrderdet(orderId, foodId, odAmount, odPrice, odStatus);
    }

    @Override
    public int updateOrderamo(String orderId, String foodId, int odAmount,double odprice) {
        return orderdetDao.updateOrderamo(orderId, foodId, odAmount,odprice);
    }


}
