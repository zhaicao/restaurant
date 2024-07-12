package com.scuec.restaurant.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Menu;
import com.scuec.restaurant.service.CommonService;
import com.scuec.restaurant.service.MenuService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/menu")
@ApiOperation(value = "菜单管理", notes = "菜单管理相关业务")
@Slf4j
public class MenuController {
    @Autowired
    private MenuService menuService;

    @Autowired
    private CommonService commonService;

    @GetMapping("/getMenuId")
    @ApiOperation(value = "通过Id获取菜品信息", notes = "通过Id获取菜品信息")
    @ApiImplicitParam(name = "foodId", value = "菜品ID", required = true, dataType = "String", paramType = "query")
    public Menu getMenuById(String foodId){
        Menu menu = menuService.getMenuById(foodId);
        return menu;
    }

    /**
     * 获取菜单list
     * @param currentPage
     * @param pageSize
     * @param menuName 用户真实姓名，模糊查询
     * @return
     */
    @GetMapping("/getMenuList")
    @ApiOperation(value = "根据菜品名分页查询菜单list", notes = "菜品名模糊查询菜单list")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "menuName", value = "菜品名，模糊查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "menuType", value = "菜品类型", required = true, dataType = "String", paramType = "query"),

    })
    public IPage<Menu> getMenuList(int currentPage,
                                   int pageSize,
                                   String menuName,
                                   String menuType){

        return menuService.getMenuList(currentPage, pageSize, menuName,menuType);
    }

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
    public Menu addMenu(String menuType , String menuName , double menuPrice ,@RequestParam(value = "file") MultipartFile file){
        Menu menu = menuService.addMenu(menuType, menuName, menuPrice, file);
        if (menu != null)
            return menu;
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Menu Error");
    }


    @PutMapping("/updateMenu")
    @ApiOperation(value = "通过菜品Id更新菜品信息", notes = "类型，菜品名，价格和图片可修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "foodId", value = "菜品ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "menuType", value = "菜品类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "menuName", value = "菜品名", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "menuPrice", value = "菜品价格", required = true, dataType = "double", paramType = "query"),
//            @ApiImplicitParam(name = "menuImg", value = "图片", required = true, dataType = "String", paramType = "query"),
    })
    public Menu updateMenu(String foodId, String menuType , String menuName , double menuPrice , @RequestParam(value = "file") MultipartFile file ){
        Menu menu = menuService.updateMenu(foodId, menuType, menuName, menuPrice, file);
        if (menu != null)
            return menu;
        else
            throw new GlobalException(ResponseCode.ERROR, "Update Table Error, foodId:" + foodId);
    }
}
