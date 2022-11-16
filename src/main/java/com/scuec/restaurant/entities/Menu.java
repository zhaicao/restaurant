package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Menu {
    private int foodID;

    private String menuType;

    private String menuName;

    private Character menuPrice;

    private String menuImg;

    private int menuPopular;

    private int menuDel;
}
