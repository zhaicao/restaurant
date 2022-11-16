package com.scuec.restaurant.dao;

import org.springframework.stereotype.Repository;

@Repository
public interface MenuDao {
    int updateMenu(String foodId,
                    String menuType,
                    String menuName,
                    Character menuPrice,
                    String menuImg,
                    int menuPopular,
                    int menuDel);

    int addTable(String menuType,
                 String menuName,
                 Character menuPrice,
                 String menuImg);
}
