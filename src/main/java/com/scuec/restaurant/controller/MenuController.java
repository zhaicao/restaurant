package com.scuec.restaurant.controller;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Menu;
import com.scuec.restaurant.service.MenuService;
import com.scuec.restaurant.service.impl.MenuServiceimpl;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/menur")
@ApiOperation(value = "菜单管理", notes = "菜单管理相关业务")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    @DeleteMapping("/deleteMenuById")
    @ApiOperation(value = "通过菜单Id删除（更新isdel）菜品", notes = "通过菜单Id删除（更新isdel）菜品")
    @ApiImplicitParam(name = "foodId", value = "菜品ID", required = true, dataType = "String", paramType = "query")
    public String deleteMenuById(String foodId){
        int res = menuService.deleteMenuById(foodId);
        if (res != 0 )
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Delete User Error, foodId:" + foodId);
    }

    @PostMapping("/addMenu")
    @ApiOperation(value = "新增菜品", notes = "新增一个菜品")
    public String addMenu(@RequestBody Menu menu){
        int result = menuService.addMenu(menu.getMenuType(),
                menu.getMenuName(),
                menu.getMenuPrice(),
                menu.getMenuImg());
        if (result == 1)
            return "successful";
        else if (result == -1)
            throw new GlobalException(ResponseCode.ERROR, "MenuName already exists, menuName:" + menu.getMenuName());
        else
            throw new GlobalException(ResponseCode.ERROR, "Add User Error");
    }
}
