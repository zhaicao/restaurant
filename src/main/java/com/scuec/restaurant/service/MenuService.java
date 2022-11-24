package com.scuec.restaurant.service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Menu;

public interface MenuService {
    int deleteMenuById(String foodId);

//    int addMenu(String menuType, String menuName, double menuPrice, String menuImg);

    IPage<Menu> getMenuList(int currentPage, int pageSize, String menuName);

    int updateMenu(String foodId, String menuType, String menuName, double menuPrice, String menuImg);

    Menu getMenuById(String foodId);

    int addMenu(String menuType, String menuName, double menuPrice, String menuImg);
}
