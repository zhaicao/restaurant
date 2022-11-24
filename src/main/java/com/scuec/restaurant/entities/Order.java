package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Order {
    private String orderId;

    private String tableNo;

    private String orderStatus;

    private double orderPrice;

    private Date orderDateg;

}
