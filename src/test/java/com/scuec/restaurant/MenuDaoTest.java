package com.scuec.restaurant;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MenuDao;
import com.scuec.restaurant.entities.Menu;
import com.scuec.restaurant.entities.User;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class MenuDaoTest {

    @Autowired
    private MenuDao menuDao;

    @Autowired
    Menu menu;


//    @Test
//    public void testUpdate(){
//        int res  = menuDao.updateMenu("2",
//                null,
//                null,
//                1.11,
//                null,
//                1,
//                0);
//        log.warn(String.valueOf("update:" + res));
//    }

    @Test
    public void testAdd(){
        Menu menu = new Menu();
        menu.setMenuType("staple");
        menu.setMenuName("测试1");
        menu.setMenuPrice(20.5);
        menu.setMenuImg("123.jsp");
        menu.setMenuPopular(0);
        menu.setMenuDel(0);
        int res  = menuDao.addMenu(menu);
        System.out.println(String.valueOf("add:" + res));
        // 获取selectKey
        System.out.println(String.valueOf("addMenu:" + menu.getFoodId()));
    }

//    @Test
//    public void testGetMenuList(){
//        int current = 1;
//        int size = 10;
//        IPage<Menu> menus  = menuDao.getMenuList(new Page<>(current, size), "");
//        menus.getRecords().forEach(menu -> log.warn(String.valueOf("searchAll:" + menu)));
//        // 打印分页数据
//        System.out.println("TotalPages："+menus.getPages());
//        System.out.println("Total："+menus.getTotal());
//        System.out.println("Current："+menus.getCurrent());
//        System.out.println("Size："+menus.getSize());
//    }
}
