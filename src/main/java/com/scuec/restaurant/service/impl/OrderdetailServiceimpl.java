package com.scuec.restaurant.service.impl;


import com.scuec.restaurant.dao.OrderdetDao;
import com.scuec.restaurant.service.OrderdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

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


//    @Override
//    public void addOrderdet(String odId, String orderId, String foodId, int odAmount, double odPrice, int odStatus) {
//        orderdetDao.addOrderdet(odId,orderId, foodId, odAmount, odPrice, odStatus);
//    }


}
