package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
//extends Order
@Data
@Component
public class Orderdetail {

        private String odId;

        private String orderId;

        private String foodId;

        private int odAmount;

        private double odPrice;

        private int odStatus;

        private int odDel;

        private Menu menu;


}
