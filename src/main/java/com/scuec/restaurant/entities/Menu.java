package com.scuec.restaurant.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.scuec.restaurant.utils.PropertiesUtil;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Menu {
    private String foodId;

    private String menuType;

    private String menuName;

    private double menuPrice;

    private String menuImg;

    private int menuPopular;

    private int menuDel;

    /**
     * 直接设定默认值
     * 由于Mybatis未使用springboot自动装配，@Value设置默认值方式无效
     */
    @TableField(exist = false) //标识不是数据库表字段
    private String menuImgBasicPath = PropertiesUtil.getProperty("upload.file.basic-path");

}
