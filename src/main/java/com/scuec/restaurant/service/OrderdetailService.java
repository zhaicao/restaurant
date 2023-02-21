package com.scuec.restaurant.service;

import com.scuec.restaurant.entities.Orderdetail;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public interface OrderdetailService {
    int deleteOrderdetId(String orderId);


//    void addOrderdet(String odId, String orderId, String foodId, int odAmount, double odPrice, int odStatus);

    void addOrderdet(String orderId, String foodId, int odAmount, double odPrice, int odStatus);


    int updateOrderamo(String orderId, String foodId, int odAmount, double odprice);
}
