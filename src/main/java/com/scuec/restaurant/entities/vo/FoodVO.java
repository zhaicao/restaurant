package com.scuec.restaurant.entities.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
/**
 * 菜品VO
 */
public class FoodVO {
    String mdId;  // 关联表Id
    String orderId;  // 订单号
    String foodId;  // 菜品编号
    String foodType;  // 菜品类型
    String foodName;  // 菜品名称
    int foodAmount;  // 菜品数量
    int foodStatus;  // 菜品状态
    String orderStatus;  // 订单状态
    String orderDate;  // 订单时间
}
