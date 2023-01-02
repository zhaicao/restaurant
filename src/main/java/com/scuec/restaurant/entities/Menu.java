package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Data
@Component
public class Menu {
    private String foodId;

    private String menuType;

    private String menuName;

    private double menuPrice;

    private String menuImg;

    private int menuPopular;

    private int menuDel;

}
