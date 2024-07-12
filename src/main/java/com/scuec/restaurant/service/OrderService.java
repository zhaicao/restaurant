package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.vo.FoodVO;

import java.util.List;

public interface OrderService {
    

    /**
     * 订单菜品批量上菜，支持多订单，多菜品
     * @param foodVOList FoodVO对象，其中orderId和foodId必填
     * @return
     */
    int serveFoodsByList(List<FoodVO> foodVOList);


    /**
     * 获取订单列表
     * @param currentPage
     * @param pageSize
     * @param orderStatus 订单状态
     * @param startDate 检索开始时间
     * @param endDate 检索结束时间
     * @return
     */
    IPage<Order> getOrderList(int currentPage, int pageSize, String orderStatus, String startDate, String endDate);


    int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus);


    Order getunOrderBytableId(String tableId);

    int addOrdermenu(String orderId,double orderPrice);

    /**
     * 获取已下单且未上菜的菜品列表
     * @param currentPage 当前页
     * @param pageSize 每页显示数
     * @param menuType 菜品类型
     * @param menuName 菜品名
     * @param startDate 下单开始时间
     * @param endDate 下单结束时间
     * @return FoodVO
     */
    IPage<FoodVO> getNewFoodList(int currentPage,
                                 int pageSize,
                                 String menuType,
                                 String menuName,
                                 String startDate,
                                 String endDate);

    Order getOrderByTableId(String tableId);

    int updateOrderstaByTableid(String tableId);

    int addOrderVO(String orderId,String tableId);

    int updateOrderpriByTableid(String orderId,double price);

    Order addOrderALL(String order1);

    String getOrderstaByTableid(String tableId);


    int updateOrderstakong(String orderId);

    int deleteOrderbyorderid(String orderId);
}
