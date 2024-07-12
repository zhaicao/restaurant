package com.scuec.restaurant.entities.vo;

import com.scuec.restaurant.entities.Order;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class RevenueDetailVO {
    private String orderDate;
    private double orderTotalPrice;
    private int orderQuantity;
    private List<Order> orderList;
}
