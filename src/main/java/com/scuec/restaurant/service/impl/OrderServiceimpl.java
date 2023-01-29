package com.scuec.restaurant.service.impl;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.dao.OrderdetDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.Orderdetail;
import com.scuec.restaurant.entities.vo.FoodVO;
import com.scuec.restaurant.service.MessageService;
import com.scuec.restaurant.service.OrderService;
import com.scuec.restaurant.service.OrderdetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


@Service
public class OrderServiceimpl implements OrderService {

    @Autowired
    private OrderDao orderDao;

    @Autowired
    private OrderService orderService;

    @Autowired
    private OrderdetailService orderdetailService;

    @Autowired
    private OrderdetDao orderdetDao;

    @Autowired
    private MessageService messageService;


    @Override
    public int deleteOrderById(String tableId) {
        Order order = orderDao.getOrderBytableId(tableId);
        if (order != null){
            String orderId = order.getOrderId();
            orderDao.deleteOrder(orderId);
            orderdetailService.deleteOrderdetId(orderId);
            return 0;
        }else
            return -1;
    }

//    @Override
//    public int deleteOrderById(String orderId,String tableId) {
//        Order order = orderDao.getOrderBytableId(tableId);
//        if (order == null){
//            return orderDao.deleteOrder(orderId);
//        }else
//            return -1;
//    }

    @Override
    public int serveFoodsByList(List<FoodVO> foodVOList) {
        int res = 0;
        List<String> orderList = new ArrayList<String>();
        // 批量上菜且生成订单号List，减少订单循环
        // 说明：未使用mubatis-plus的批量更新，后续优化
        for (FoodVO food: foodVOList) {
            res =+ orderdetDao.updateOrderFoodStatus(food.getOrderId(), food.getFoodId(), 1);
            // OrderId去重
            if (!orderList.contains(food.getFoodId()))
                orderList.add(food.getOrderId());
        }
        if (res == 0)
            throw new GlobalException(ResponseCode.ERROR, "Serve Foods Error");
        // 遍历所有订单
        for (String orderId : orderList) {
            // 获得并检查该订单下所有菜品状态
            List<Orderdetail> foodList = orderdetDao.getFoodListByOrderId(orderId);
            int serveFoodsCount = 0;
            for (Orderdetail food : foodList) {
                if (food.getOdStatus() == 1)
                    serveFoodsCount++;
            }
            // 更新订单状态为”上菜中“或”已上菜“
            if (serveFoodsCount == foodList.size())
                orderDao.updateOrderStatus(orderId, "已上菜");
            else
                orderDao.updateOrderStatus(orderId, "上菜中");
        }
        return res;
    }

    @Override
    public int updateOrdersta1(String orderId) {
        return orderDao.updateOrdersta1(orderId);
    }


    @Override
    public IPage<Order> getOrderList(int currentPage, int pageSize, String orderStatus, String startDate, String endDate) {
        IPage<Order> orderList = orderDao.getOrderList(new Page<>(currentPage, pageSize), orderStatus, startDate, endDate);
        List<Order> orders = orderList.getRecords();
        // 以下遍历后再设置每个FoodVO中的urgeSum和MsgSum，待后续优化
        int i = 0;
        for (Order order : orders) {
            order.setUrgeSum(messageService.getMessageSum(order.getOrderId(), 1));
            order.setMsgSum(messageService.getMessageSum(order.getOrderId(), 2));
            orders.set(i, order);
            i++;
        }
        return orderList;
    }


    @Override
    public int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus) {
        return orderDao.addOrder(orderId,tableNo,orderPrice,orderStatus);
    }

    @Override
    public Order getOrderBytableId(String tableId) {
        return orderDao.getOrderBytableId(tableId);
    }

    @Override
    public int addOrdermenu(String orderId,double orderPrice) {
        double price = orderDao.getOrderPrice(orderId);
        orderPrice = price + orderPrice;
        return orderDao.updateOrderByorderId(orderId,orderPrice);
    }

    @Override
    public IPage<FoodVO> getNewFoodList(int currentPage, int pageSize, String menuType, String menuName, String startDate, String endDate) {
        IPage<FoodVO> foodList = orderDao.getFoodList(new Page<>(currentPage, pageSize), 0, menuType, menuName, startDate, endDate);
        List<FoodVO> foods = foodList.getRecords();
        // 以下遍历后再设置每个FoodVO中的urgeSum和MsgSum，待后续优化
        int i = 0;
        for (FoodVO food : foods) {
            food.setUrgeSum(messageService.getMessageSum(food.getOrderId(), 1));
            food.setMsgSum(messageService.getMessageSum(food.getOrderId(), 2));
            foods.set(i, food);
            i++;
        }
        return foodList;
    }
}
