package com.scuec.restaurant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Table;
import com.scuec.restaurant.entities.vo.TableVO;
import com.scuec.restaurant.service.TableService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/table")
@ApiOperation(value = "餐桌管理", notes = "餐桌管理相关业务")
@Slf4j
public class TableController {
    @Autowired
    private TableService tableService;

    @GetMapping("/getTableById")
    @ApiOperation(value = "通过Id获取餐桌信息", notes = "通过Id获取餐桌信息")
    @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query")
    public Table getTableById(String tableId){
        Table table = tableService.getTableById(tableId);
        return table;
    }


    @DeleteMapping("/deleteTableById")
    @ApiOperation(value = "通过桌号Id删除（逻辑删除）餐桌", notes = "通过桌号Id删除（逻辑删除）餐桌")
    @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query")
    public String deleteTableById(String tableId){
        int res = tableService.deleteTableById(tableId);
        if (res != 0 )
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "This Table is in use, tableId:" + tableId);
    }

    @PutMapping("/updateTable")
    @ApiOperation(value = "通过餐号Id更新餐桌信息", notes = "桌号，描述和orderId可修改，tableId必填")
    public Table updateTable(@RequestBody Table table){
        Table res = tableService.updateTable(table);
        if (res != null )
            return res;
        else
            throw new GlobalException(ResponseCode.ERROR, "Update Table Error, tableId:" + table.getTableId());
    }

    @PutMapping("/updateTableuse")
    @ApiOperation(value = "通过餐号Id更新餐桌状态已使用", notes = "通过餐号Id更新餐桌状态")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tOrderid", value = "orderid", required = true, dataType = "String", paramType = "query")
    })
    public String updateTableuse(String tableId,String tOrderid){
        int res = tableService.updateTableUse(tableId,tOrderid);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "餐桌为已使用状态，tableId：" + tableId);
    }

    @PutMapping("/updateTableuse1")
    @ApiOperation(value = "通过餐号Id更新餐桌状态为空闲", notes = "通过餐号Id更新餐桌状态为空闲")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query")
    })
    public String updateTableuse1(String tableId){
        int res = tableService.updateTableUse1(tableId);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "餐桌为空闲状态，tableId：" + tableId);
    }

    /**
     * 获取餐桌list
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getTableList")
    @ApiOperation(value = "分页显示table列表", notes = "分页显示table列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "tableName", value = "桌号，模糊查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tableId", value = "餐桌ID，精确查询", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "tableUse", value = "餐桌状态", required = true, dataType = "int", paramType = "query")
    })
    public IPage<Table> getTableList(int currentPage,
                                     int pageSize,
                                     String tableId,
                                     String tableName,
                                     int tableUse){

        return tableService.getTableList(currentPage, pageSize, tableId, tableName, tableUse);
    }

    @PostMapping("/addTable")
    @ApiOperation(value = "添加餐桌信息", notes = "桌号，人数")
    public Table addTable(@RequestBody Table table){
        Table response = tableService.addTable(table);
        if (response != null)
            return response;
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Table Error");
    }


    /**
     * 获取餐桌list
     * @return
     */
    @GetMapping("/getTableListAll")
    @ApiOperation(value = "显示table列表", notes = "页显示table列表")
    public List<TableVO> getTableListAll(){

        return tableService.getTableListAll();
    }



    @GetMapping("/getTablestaById")
    @ApiOperation(value = "通过tableId判断餐桌状态", notes = "通过tableId判断餐桌状态")
    @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query")
    public Integer getTablestaById(String tableId){
         return tableService.getTablestaById(tableId);

    }
}



