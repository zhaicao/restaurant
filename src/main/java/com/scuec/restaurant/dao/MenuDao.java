package com.scuec.restaurant.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Menu;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao {
    int updateMenu(Menu menu);

//    int addMenu(String menuType,
//                 String menuName,
//                 double menuPrice,
//                 String menuImg);

    /**
     * 通过menu对象添加菜品
     * @param menu
     * @return 返回插入成功的记录数
     */
    int addMenu(Menu menu);

    IPage<Menu> getMenuList(@Param("page") Page<Menu> page,
                             String menuName,
                             String menuType);

    Menu getMenuById(String foodId);
}
