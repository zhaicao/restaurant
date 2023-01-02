package com.scuec.restaurant.dao;

import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderdetDao {

    int deleteOrderdetId(String orderId);

//    void addOrderdet(String odId,String orderId, String foodId, int odAmount, double odPrice, int odStatus);

    void addOrderdet(String orderId, String foodId, int odAmount, double odPrice, int odStatus);

    int updateOrdermenusta(String orderId, String foodId);
}
