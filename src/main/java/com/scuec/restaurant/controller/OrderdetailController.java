package com.scuec.restaurant.controller;


import com.scuec.restaurant.service.OrderdetailService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/orderdetail")
@ApiOperation(value = "订单详情管理", notes = "订单详情管理相关业务")
@Slf4j
public class OrderdetailController {

    @Autowired
    private OrderdetailService orderdetailService;
}
