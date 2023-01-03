package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MenuDao;
import com.scuec.restaurant.entities.Menu;
import com.scuec.restaurant.service.CommonService;
import com.scuec.restaurant.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@Service
public class MenuServiceimpl implements MenuService {

    @Autowired
    private MenuDao menuDao;
    @Autowired
    private CommonService commonService;

    @Override
    public int deleteMenuById(String foodId) {

        return menuDao.updateMenu(foodId,null,null,0,null,0,1);
    }

    @Override
    public IPage<Menu> getMenuList(int currentPage, int pageSize, String menuName,String menuType) {
        return menuDao.getMenuList(new Page<>(currentPage, pageSize),menuName,menuType);
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
    public Menu addMenu(String menuType, String menuName, double menuPrice, MultipartFile file) {
        // 上传图片
        String menuImg = commonService.upload(file);
        Menu menu = new Menu();
        menu.setMenuType(menuType);
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setMenuImg(menuImg);
        menu.setMenuPopular(0);
        menu.setMenuDel(0);
        int res = menuDao.addMenu(menu);
        if (res == 1) {
            return menu;
        } else
            return null;
    }
}
