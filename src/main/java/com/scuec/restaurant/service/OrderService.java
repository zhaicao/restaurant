package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.vo.FoodVO;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface OrderService {

    int deleteOrderById(String tableId);

    int updateOrdersta(String orderId,String foodId);

    int updateOrdersta1(String orderId);

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


    Order getOrderBytableId(String tableId);

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
}
