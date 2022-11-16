package com.scuec.restaurant.service.impl;

import com.scuec.restaurant.dao.MenuDao;
import com.scuec.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceimpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public int deleteMenuById(String foodId) {

        return menuDao.updateMenu(foodId,null,null,null,null,0,1);
    }

    @Override
    public int addMenu(String menuType, String menuName, Character menuPrice, String menuImg) {
        return menuDao.addTable(menuType, menuName, menuPrice,menuImg);
    }
}
