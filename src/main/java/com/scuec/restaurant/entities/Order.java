package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Data
@Component
public class Order {
    private String orderId;

    private String tableNo;

    private String orderStatus;

    private double orderPrice;

    private Date orderDate;

    private List<Orderdetail> orderdetail;

}
