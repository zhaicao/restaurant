package com.scuec.restaurant.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Menu;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

public interface MenuService {
    int deleteMenuById(String foodId);

    IPage<Menu> getMenuList(int currentPage, int pageSize, String menuName,String menuType);

    Menu updateMenu(String foodId, String menuType, String menuName, double menuPrice, MultipartFile file);

    Menu getMenuById(String foodId);

    /**
     * 增加菜品
     * @param menuType
     * @param menuName
     * @param menuPrice
     * @param file
     * @return 成功返回Menu对象，失败返回Null
     */
    Menu addMenu(String menuType, String menuName, double menuPrice, MultipartFile file);
}
