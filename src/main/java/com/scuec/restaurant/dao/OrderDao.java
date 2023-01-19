package com.scuec.restaurant.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.vo.FoodVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface OrderDao {

    @Delete("delete from `m_order` where orderid = #{orderId}")
    int deleteOrder(String orderId);

    /**
     * 更新订单状态
     * @param orderId 订单号
     * @param orderStatus 订单状态
     * @return
     */
    int updateOrderStatus(String orderId, String orderStatus);

    int updateOrdersta1(String orderId);

    IPage<Order> getOrderList(@Param("page") Page<Order> page,
                               String orderStatus,
                               String startDate,
                               String endDate);


    int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus);


    Order getOrderBytableId(String tableId);

    int updateOrderByorderId(String orderId,double orderPrice);

    double getOrderPrice(String orderId);

    /**
     * 获取已上菜的菜品列表
     * @param page page对象
     * @param foodStatus 菜品状态（option），0:未上菜 1:已上菜 -1:全部
     * @param menuType 菜品类型（option）
     * @param menuName 菜品名称（option）
     * @param startDate 菜品下单开始时间（option）
     * @param endDate 菜品下单结束时间（option）
     * @return
     */
    IPage<FoodVO> getFoodList(@Param("page") Page<Order> page,
                              int foodStatus,
                              String menuType,
                              String menuName,
                              String startDate,
                              String endDate);
}
