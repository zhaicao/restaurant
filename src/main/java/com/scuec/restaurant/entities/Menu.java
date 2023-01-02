package com.scuec.restaurant.entities;

import com.baomidou.mybatisplus.annotation.TableField;
import com.scuec.restaurant.utils.PropertiesUtil;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

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

    @TableField(exist = false) //标识不是数据库表字段
    private String menuImgBasicPath;

    /**
     * 重写get方法，获取配置文件基础路径
     * 由于Mybatis未使用springboot自动装配，@Value设置默认值方式无效
     * @return
     */
    public String getMenuImgBasicPath(){
        return PropertiesUtil.getProperty("upload.file.basic-path");
    }

}
