package com.scuec.restaurant.entities.vo;

import com.scuec.restaurant.service.MessageService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Data
@Component
/**
 * 菜品VO
 */
public class FoodVO {
    private String mdId;  // 关联表Id
    private String orderId;  // 订单号
    private String tableName; // 桌位名
    private String foodId;  // 菜品编号
    private String foodType;  // 菜品类型
    private String foodName;  // 菜品名称
    private int foodAmount;  // 菜品数量
    private int foodStatus;  // 菜品状态
    private String orderStatus;  // 订单状态
    private String orderDate;  // 订单时间
    private int urgeSum; // 催单总数
    private int msgSum; //消息总数
}
