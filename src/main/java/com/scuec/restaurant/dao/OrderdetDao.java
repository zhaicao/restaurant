package com.scuec.restaurant.dao;

import com.scuec.restaurant.entities.Orderdetail;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderdetDao {

    /**
     * 删除订单下菜品
     * @param orderId
     * @return
     */
    int deleteOrderdetId(String orderId);

//    void addOrderdet(String odId,String orderId, String foodId, int odAmount, double odPrice, int odStatus);

    /**
     * 增加指定订单下菜品
     * @param orderId 订单Id
     * @param foodId 菜品Id
     * @param odAmount 菜品数量
     * @param odPrice 菜品总价格
     * @param odStatus 菜品状态
     */
    void addOrderdet(String orderId, String foodId, int odAmount, double odPrice, int odStatus);

    /**
     * 更新订单下菜品状态
     * @param orderId 订单号
     * @param foodId 菜品Id
     * @param foodStatus 菜品状态 0:未上菜；1:已上菜
     * @return 更新数
     */
    int updateOrderFoodStatus(String orderId, String foodId, int foodStatus);

    /**
     * 通过订单Id查询该订单下所有菜品
     * Menu懒加载
     * @param orderId 订单号
     * @return List
     */
    List<Orderdetail> getFoodListByOrderId(String orderId);


    int updateOrderamo(String orderId, String foodId, int odAmount,double odprice);

    int updateOrderdetSta(String orderId);

    void addOrderdetlist(List<Orderdetail> orderdetailList);
}
