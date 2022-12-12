package com.scuec.restaurant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Message;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.Table;
import com.scuec.restaurant.service.AttendanceService;
import com.scuec.restaurant.service.CommonService;
import com.scuec.restaurant.service.OrderService;
import com.scuec.restaurant.service.OrderdetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

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

    @PostMapping("/addOrder")
    @ApiOperation(value = "新增订单", notes = "新增订单")
    public String addMenu(@RequestBody Order order){
        String orderId = commonService.getId();
        int result = orderService.addOrder(orderId,
                order.getTableNo(),
                order.getOrderPrice(),
                order.getOrderStatus());

//        String odId = commonService.getId();
        order.getOrderdetail().forEach(u->{orderdetailService.addOrderdet(
                orderId,
                u.getFoodId(),
                u.getOdAmount(),
                u.getOdPrice(),
                u.getOdStatus());});
        if (result == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Message Error");
    }


    @GetMapping("/getOrderList")
    @ApiOperation(value = "分页显示order列表", notes = "分页显示order列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "orderStatus", value = "订单状态", required = true, dataType = "String", paramType = "query"),
    })
    public IPage<Order> getOrderList(int currentPage,
                                     int pageSize,
                                     String orderStatus){

        return orderService.getOrderList(currentPage, pageSize,orderStatus);
    }


    @DeleteMapping("/deleteOrder")
    @ApiOperation(value = "根据订单Id删除（逻辑删除）订单详情", notes = "根据订单Id删除（逻辑删除）订单详情")
    @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "query")
    public String deleteOrderdelById(String orderId){
        orderService.deleteOrderById(orderId);
        int res = orderdetailService.deleteOrderdetId(orderId);
        if (res != 0 )
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Delete Order Error, orderId:" + orderId);
    }


    @PostMapping("/updateOrdersta")
    @ApiOperation(value = "通过订单id更新订单状态上菜", notes = "通过订单id更新订单状态上菜")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "query")
    })
    public String updateOrdersta(String orderId){
        int res = orderService.updateOrdersta(orderId);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update Ordersta Error, orderId:" + orderId);
    }


    @PostMapping("/updateOrdersta1")
    @ApiOperation(value = "通过订单id更新订单状态已付款", notes = "通过订单id更新订单状态已付款")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "query")
    })
    public String updateOrdersta1(String orderId){
        int res = orderService.updateOrdersta1(orderId);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update Ordersta Error, orderId:" + orderId);
    }

    @GetMapping("/getOrderById")
    @ApiOperation(value = "通过Id获取订单信息", notes = "通过Id获取订单信息")
    @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "query")
    public Order getOrderById(String orderId){
        Order order = orderService.getOrderById(orderId);
        return order;
    }

}
