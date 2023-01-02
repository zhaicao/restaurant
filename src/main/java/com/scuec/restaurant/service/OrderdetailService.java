package com.scuec.restaurant.service;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public interface OrderdetailService {
    int deleteOrderdetId(String orderId);


//    void addOrderdet(String odId, String orderId, String foodId, int odAmount, double odPrice, int odStatus);

    void addOrderdet(String orderId, String foodId, int odAmount, double odPrice, int odStatus);

}
