package com.scuec.restaurant.service;

public interface MenuService {
    int deleteMenuById(String foodId);

    int addMenu(String menuType, String menuName, Character menuPrice, String menuImg);
}
