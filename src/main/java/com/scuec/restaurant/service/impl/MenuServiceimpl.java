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

        Menu menu = new Menu();
        menu.setFoodId(foodId);
        menu.setMenuType(null);
        menu.setMenuName(null);
        menu.setMenuPrice(0);
        menu.setMenuImg(null);
        menu.setMenuPopular(0);
        menu.setMenuDel(1);
        int res = menuDao.updateMenu(menu);
        if (res == 1) {
            return 1;
        } else
            return 0;
    }

    @Override
    public IPage<Menu> getMenuList(int currentPage, int pageSize, String menuName,String menuType) {
        return menuDao.getMenuList(new Page<>(currentPage, pageSize),menuName,menuType);
    }

    @Override
    public Menu updateMenu(
            String foodId, String menuType, String menuName, double menuPrice,MultipartFile file) {
        // 上传图片
        Menu menu = new Menu();
        menu.setFoodId(foodId);
        menu.setMenuType(menuType);
        menu.setMenuName(menuName);
        menu.setMenuPrice(menuPrice);
        menu.setMenuPopular(0);
        menu.setMenuDel(0);
        if(file.isEmpty() || file == null)
            menu.setMenuImg(menuDao.getMenuById(foodId).getMenuImg()); //获取旧图片数据
        else {
            // 上传图片
            String menuImg = commonService.upload(file);
            menu.setMenuImg(menuImg);
        }
        int res = menuDao.updateMenu(menu);
        if (res == 1)
            return menu;
        else
            return null;
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
