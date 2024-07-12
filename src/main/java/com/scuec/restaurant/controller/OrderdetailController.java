package com.scuec.restaurant.controller;


import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Orderdetail;
import com.scuec.restaurant.service.OrderdetailService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/orderdetail")
@ApiOperation(value = "订单详情管理", notes = "订单详情管理相关业务")
@Slf4j
public class OrderdetailController {

    @Autowired
    private OrderdetailService orderdetailService;

    @PutMapping("/updateOrderamo")
    @ApiOperation(value = "通过订单id和菜品id更新菜品数量", notes = "通过订单id和菜品id更新菜品数量")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "foodId", value = "菜品ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "odAmount", value = "菜品数量", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "odprice", value = "价格", required = true, dataType = "double", paramType = "query")
    })
    public String updateOrderamo(String orderId,String foodId,int odAmount,double odprice){
        int res = orderdetailService.updateOrderamo(orderId,foodId,odAmount,odprice);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update Ordersta Error, orderId:" + orderId);
    }


}
