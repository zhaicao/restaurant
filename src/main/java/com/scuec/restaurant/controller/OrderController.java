package com.scuec.restaurant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.vo.FoodVO;
import com.scuec.restaurant.service.CommonService;
import com.scuec.restaurant.service.OrderService;
import com.scuec.restaurant.service.OrderdetailService;
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
@RequestMapping(value = "/order")
@ApiOperation(value = "订单", notes = "订单管理相关业务")
@Slf4j
public class OrderController {

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderdetailService orderdetailService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private TableService tableService;



    @PostMapping("/addOrder")
    @ApiOperation(value = "新增订单", notes = "新增订单")
    public Order addOrder(@RequestBody String order1){

        return orderService.addOrderALL(order1);
    }




    @GetMapping("/getOrderList")
    @ApiOperation(value = "分页显示order列表", notes = "分页显示order列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "结束时间", required = true, dataType = "String", paramType = "query"),
    })
    public IPage<Order> getOrderList(int currentPage,
                                     int pageSize,
                                     String orderStatus,
                                     String startDate,
                                     String endDate){
        return orderService.getOrderList(currentPage, pageSize,orderStatus, startDate, endDate);
    }

    @DeleteMapping("/deleteOrderbyorderid")
    @ApiOperation(value = "根据订单号进行撤单", notes = "根据订单号进行撤单")
    @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "query")
    public String deleteOrderbyorderid(String orderId){
        int res = orderService.deleteOrderbyorderid(orderId);
        if (res == 1 )
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "不能撤销已上菜的订单，orderId" + orderId);
    }


    @PutMapping("/serveFoodsByList")
    @ApiOperation(value = "通过订单id和菜品id对菜品进行批量上菜", notes = "通过订单id和菜品id对菜品进行批量上菜")
    public String serveFoodsByList(@RequestBody List<FoodVO> foodList){
        int res = orderService.serveFoodsByList(foodList);
        if (res == 1)
            return "success";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update Ordersta Error, orderId:" + foodList);
    }


    @PutMapping("/updateOrderstakong")
    @ApiOperation(value = "通过订单号更新订单状态已付款，并去掉桌位号，修改桌号为空闲，去掉桌表的订单号", notes = "通过订单号更新订单状态已付款，并去掉桌位号，修改桌号为空闲，去掉桌表的订单号")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "query")
    })
    public String updateOrderstakong(String orderId){
        int res = orderService.updateOrderstakong(orderId);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "未上菜的订单不能结账, orderId:" + orderId);
    }



    @GetMapping("/getunOrderBytableId")
    @ApiOperation(value = "通过桌号Id获取未上菜订单id", notes = "通过桌号Id获取未上菜订单id")
    @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query")
    public String getunOrderBytableId(String tableId){
        Order order = orderService.getunOrderBytableId(tableId);
        String orderid = order.getOrderId();
        return orderid;
    }

    @GetMapping("/getNewFoodList")
    @ApiOperation(value = "获取未上菜的菜品列表", notes = "获取未上菜的菜品列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "menuType", value = "菜品类型", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "menuName", value = "菜品名称", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "下单开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "下单结束时间", required = true, dataType = "String", paramType = "query"),
    })
    public IPage<FoodVO> getNewFoodList(int currentPage,
                                        int pageSize,
                                        String menuType,
                                        String menuName,
                                        String startDate,
                                        String endDate){
        return orderService.getNewFoodList(currentPage, pageSize, menuType, menuName, startDate, endDate);
    }


    @GetMapping("/getOrderByTableId")
    @ApiOperation(value = "根据桌号查询order详情", notes = "根据桌号查询order详情")
    @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query")
    public Order getOrderByTableId(String tableId){

        Order order = orderService.getOrderByTableId(tableId);
        if (order != null ) {
            return order;

        } else {
            throw new GlobalException(ResponseCode.ERROR, "未找到订单");

        }
    }


    @GetMapping("/getOrderstaByTableid")
    @ApiOperation(value = "根据桌号查询order状态", notes = "根据桌号查询order状态")
    @ApiImplicitParam(name = "tableId", value = "桌号ID", required = true, dataType = "String", paramType = "query")
    public String getOrderstaByTableid(String tableId){

        return orderService.getOrderstaByTableid(tableId);

    }







}
