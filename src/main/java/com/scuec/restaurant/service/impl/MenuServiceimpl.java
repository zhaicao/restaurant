package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MenuDao;
import com.scuec.restaurant.entities.Menu;
import com.scuec.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MenuServiceimpl implements MenuService {

    @Autowired
    private MenuDao menuDao;

    @Override
    public int deleteMenuById(String foodId) {

        return menuDao.updateMenu(foodId,null,null,0,null,0,1);
    }



    @Override
    public IPage<Menu> getMenuList(int currentPage, int pageSize, String menuName) {
        return menuDao.getMenuList(new Page<>(currentPage, pageSize),menuName);
    }

    @Override
    public int updateMenu(String foodId, String menuType, String menuName, double menuPrice, String menuImg) {
        return menuDao.updateMenu(foodId, menuType, menuName, menuPrice, menuImg, 0,0);
    }

    @Override
    public Menu getMenuById(String foodId) {
        return menuDao.getMenuById(foodId);
    }

    @Override
    public int addMenu( String menuType, String menuName, double menuPrice, String menuImg) {
        return menuDao.addMenu(menuType,menuName,menuPrice,menuImg,0,0);
    }
}
