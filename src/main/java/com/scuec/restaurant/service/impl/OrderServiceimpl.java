package com.scuec.restaurant.service.impl;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.dao.OrderDao;
import com.scuec.restaurant.dao.OrderdetDao;
import com.scuec.restaurant.dao.TableDao;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.Orderdetail;
import com.scuec.restaurant.entities.vo.FoodVO;
import com.scuec.restaurant.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


@Service
public class OrderServiceimpl implements OrderService {


    @Autowired
    private TableDao tableDao;

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

    @Autowired
    private TableService tableService;

    @Autowired
    private CommonService commonService;


    @Override
    public int serveFoodsByList(List<FoodVO> foodVOList) {
        int res = 0;
        List<String> orderList = new ArrayList<String>();
        // 批量上菜且生成订单号List，减少订单循环
        // 说明：未使用mubatis-plus的批量更新，后续优化
        for (FoodVO food : foodVOList) {
            res = +orderdetDao.updateOrderFoodStatus(food.getOrderId(), food.getFoodId(), 1);
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
        return orderDao.addOrder(orderId, tableNo, orderPrice, orderStatus);
    }

    @Override
    public Order getunOrderBytableId(String tableId) {
        return orderDao.getunOrderBytableId(tableId);
    }

    @Override
    public int addOrdermenu(String orderId, double orderPrice) {
        double price = orderDao.getOrderPrice(orderId);
        orderPrice = price + orderPrice;
        return orderDao.updateOrderByorderId(orderId, orderPrice);
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

    @Override
    public Order getOrderByTableId(String tableId) {

        return orderDao.getOrderByTableId(tableId);
    }

    @Override
    public int updateOrderstaByTableid(String tableId) {

        return orderDao.updateOrderstaByTableid(tableId);
    }

    @Override
    public int addOrderVO(String orderId, String tableId) {
        return orderDao.addOrderVO(orderId, tableId);
    }

    @Override
    public int updateOrderpriByTableid(String orderId, double price) {
        return orderDao.updateOrderpriByTableid(orderId, price);
    }

    @Override
    public Order addOrderALL(String order1) {
        JSONObject jsonObj = JSON.parseObject(order1);
        String tableId = jsonObj.getString("tableNo");
        Integer m = tableService.getTablestaById(tableId);
        if (m == 0) {
            String orderId = commonService.getId();
            tableService.updateTableUse(tableId, orderId);
            JSONArray foodList = jsonObj.getJSONArray("foodList");
            System.out.println("foodList：" + foodList);
            int result = orderService.addOrderVO(orderId, tableId);
            List<Orderdetail> list = JSONObject.parseArray(foodList.toJSONString(), Orderdetail.class);
            double price = 0;
            System.out.println("list：" + list);
//            for (int i = 0; i < list.size(); i++) {
//                Orderdetail a=list.get(i);
//                orderdetailService.addOrderdet(
//                        orderId,
//                        a.getFoodId(),
//                        a.getOdAmount(),
//                        a.getOdPrice(),
//                        a.getOdStatus());
//                price = price + a.getOdAmount()*a.getOdPrice();
//            }
            List<Orderdetail> orderdetailList = new ArrayList<>();
            for (Orderdetail orderdetail : list) {
                System.out.println("orderdetail555：" + orderdetail);
                Orderdetail orderdetail1 = new Orderdetail();
                orderdetail1.setOrderId(orderId);
                orderdetail1.setFoodId(orderdetail.getMenu().getFoodId());
                orderdetail1.setOdAmount(orderdetail.getOdAmount());
                orderdetail1.setOdPrice(orderdetail.getOdAmount() * orderdetail.getMenu().getMenuPrice());
                orderdetail1.setOdStatus(orderdetail.getOdStatus());
                orderdetail1.setOdDel(0);
                orderdetailList.add(orderdetail1);
                price = price + orderdetail.getOdAmount() * orderdetail.getMenu().getMenuPrice();

            }
            orderdetDao.addOrderdetlist(orderdetailList);
            int res = orderService.updateOrderpriByTableid(orderId, price);
            Order order = orderService.getOrderByTableId(tableId);
            return order;

        }
        else if(m==1){
            Order uselist = orderService.getOrderByTableId(tableId);
            String orderId = uselist.getOrderId();
            List<Orderdetail> usefoodList = orderdetDao.getFoodListByOrderId(orderId);
            System.out.println("usefoodList：" + usefoodList);
            JSONArray foodList = jsonObj.getJSONArray("foodList");
            System.out.println("foodList：" + foodList);
            List<Orderdetail> list = JSONObject.parseArray(foodList.toJSONString(), Orderdetail.class);
            double price =0;
            System.out.println("list：" + list);
            for (int i = 0; i < list.size(); i++) {
                Orderdetail a=list.get(i);
                String foodId=a.getMenu().getFoodId();
                int food11 = 0;
                for (int n = 0; n < usefoodList.size(); n++){
                    System.out.println("foodId：" + foodId);
                    Orderdetail b=usefoodList.get(n);
                    String usefoodid = b.getFoodId();
                    System.out.println("usefoodid：" + usefoodid);
                    if(Objects.equals(foodId, usefoodid)){
                        int odAmount =a.getOdAmount()+b.getOdAmount();
                        double odprice = b.getOdPrice()+a.getOdAmount()*a.getMenu().getMenuPrice();
                        food11 = 1;
                        System.out.println("有相同的：" + food11);
                        orderdetDao.updateOrderamo(orderId, foodId, odAmount,odprice);//更新订单详情数量和价格
                    }
                }
                if(food11==0){
                    orderdetailService.addOrderdet(
                            orderId,
                            a.getMenu().getFoodId(),
                            a.getOdAmount(),
                            a.getOdAmount()*a.getMenu().getMenuPrice(),
                            a.getOdStatus());
                }
                System.out.println("food11：" + food11);
            }
            List<Orderdetail> usefoodList2 = orderdetDao.getFoodListByOrderId(orderId);
            System.out.println("usefoodList2" + usefoodList2);

            for (int c = 0; c < usefoodList2.size(); c++){
                Orderdetail q=usefoodList2.get(c);
                price = price +q.getOdPrice();
                System.out.println("price1" + q.getOdPrice());
            }
            System.out.println("price" + price);
            int res =orderService.updateOrderpriByTableid(orderId,price);
            Order order = orderService.getOrderByTableId(tableId);
            return order;
        }
        else {

            throw new GlobalException(ResponseCode.ERROR,"提交订单失败");

        }
    }

//        else if (m == 1) {
//            Order uselist = orderService.getOrderByTableId(tableId);
//            String orderId = uselist.getOrderId();
//            List<Orderdetail> usefoodList = orderdetDao.getFoodListByOrderId(orderId);
//            System.out.println("usefoodList：" + usefoodList);
//            JSONArray foodList = jsonObj.getJSONArray("foodList");
//            System.out.println("foodList：" + foodList);
//            List<Orderdetail> list = JSONObject.parseArray(foodList.toJSONString(), Orderdetail.class);
//            double price = 0;
//            System.out.println("list：" + list);
//            for (Orderdetail orderdetail : list) {
//                String foodId = orderdetail.getMenu().getFoodId();
//                int food11 = 0;
//                for (Orderdetail usefoodList1 : usefoodList) {
//                    {
//                        System.out.println("foodId：" + foodId);
//                        String usefoodid = usefoodList1.getFoodId();
//                        System.out.println("usefoodid：" + usefoodid);
//                        if (Objects.equals(foodId, usefoodid)) {
//                            int odAmount = orderdetail.getOdAmount() + usefoodList1.getOdAmount();
//                            double odprice = usefoodList1.getOdPrice() + orderdetail.getOdAmount() * orderdetail.getMenu().getMenuPrice();
//                            food11 = 1;
//                            System.out.println("有相同的：" + food11);
//                            orderdetDao.updateOrderamo(orderId, foodId, odAmount, odprice);//更新订单详情数量和价格
//                        }
//                    }
//                    if (food11 == 0) {
//                        orderdetailService.addOrderdet(
//                                orderId,
//                                orderdetail.getMenu().getFoodId(),
//                                orderdetail.getOdAmount(),
//                                orderdetail.getOdAmount() * orderdetail.getMenu().getMenuPrice(),
//                                orderdetail.getOdStatus());
//                    }
//                    System.out.println("food11：" + food11);
//                }
//                List<Orderdetail> usefoodList2 = orderdetDao.getFoodListByOrderId(orderId);
//                System.out.println("usefoodList2" + usefoodList2);
//
//                for (int c = 0; c < usefoodList2.size(); c++) {
//                    Orderdetail q = usefoodList2.get(c);
//                    price = price + q.getOdPrice();
//                    System.out.println("price1" + q.getOdPrice());
//                }
//                System.out.println("price" + price);
//                int res = orderService.updateOrderpriByTableid(orderId, price);
//                Order order = orderService.getOrderByTableId(tableId);
//                return order;
//            }
//        } else {
//
//            throw new GlobalException(ResponseCode.ERROR, "提交订单失败");
//        }
//    }




    @Override
    public String getOrderstaByTableid(String tableId) {
        return orderDao.getOrderstaByTableid(tableId);
    }

    @Override
    public int updateOrderstakong(String orderId) {
        String tableId=orderDao.getTableIdByorderid(orderId);
        int res =orderDao.updateOrderstakong(orderId);
        if(res==1){
            int a =tableDao.updateTableUse1(tableId);
            if(a!=1){
                throw new GlobalException(ResponseCode.ERROR, "更新餐桌失败");
            }
            int b = orderdetDao.updateOrderdetSta(orderId);
        }
        return res;
    }

    @Override
    public int deleteOrderbyorderid(String orderId) {
        String tableId=orderDao.getTableIdByorderid(orderId);
        int res =orderDao.updateOrderstache(orderId);
        if(res==1){
            tableDao.updateTableUse1(tableId);
        }
        return res;
    }

}
