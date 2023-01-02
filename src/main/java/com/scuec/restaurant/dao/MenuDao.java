package com.scuec.restaurant.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Menu;
import com.scuec.restaurant.entities.Table;
import com.scuec.restaurant.entities.User;
import javafx.scene.control.Tab;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface MenuDao {
    int updateMenu(String foodId,
                    String menuType,
                    String menuName,
                   double menuPrice,
                    String menuImg,
                    int menuPopular,
                    int menuDel);

//    int addMenu(String menuType,
//                 String menuName,
//                 double menuPrice,
//                 String menuImg);

    int addMenu(String menuType,
                 String menuName,
                 double menuPrice,
                 String menuImg,
                 int menuPopular,
                 int menuDel);

    IPage<Menu> getMenuList(@Param("page") Page<Menu> page,
                             String menuName,
                             String menuType);

    Menu getMenuById(String foodId);
}
