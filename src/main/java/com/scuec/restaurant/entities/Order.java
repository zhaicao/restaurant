package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@Component
public class Order  {
    private String orderId;

    private String tableNo;

    private String orderStatus;

    private double orderPrice;

    private Date orderDate;

    private int urgeSum; // 催单总数

    private int msgSum; //消息总数

    private List<Orderdetail> orderdetail;


}
