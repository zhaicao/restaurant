package com.scuec.restaurant.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface OrderdetDao {

    int deleteOrderdetId(String orderId);

//    void addOrderdet(String odId,String orderId, String foodId, int odAmount, double odPrice, int odStatus);

    void addOrderdet(String orderId, String foodId, int odAmount, double odPrice, int odStatus);
}
