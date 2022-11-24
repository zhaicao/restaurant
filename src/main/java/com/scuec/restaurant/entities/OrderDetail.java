package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class OrderDetail {
    private String odId;

    private String orderId;

    private String foodId;

    private int odAmount;

    private double odPrice;

    private int odStatus;

    private int odDel;
}
