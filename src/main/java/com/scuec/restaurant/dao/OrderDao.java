package com.scuec.restaurant.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.Orderdetail;
import com.scuec.restaurant.entities.vo.FoodVO;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

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


    IPage<Order> getOrderList(@Param("page") Page<Order> page,
                               String orderStatus,
                               String startDate,
                               String endDate);


    int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus);


    Order getunOrderBytableId(String tableId);

    int updateOrderByorderId(String orderId,double orderPrice);

    double getOrderPrice(String orderId);

    /**
     * 获取未上菜的菜品列表
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

    Order getOrderByTableId(String tableId);

    int updateOrderstaByTableid(String tableId);

    int addOrderVO(String orderId,String tableId);

    int updateOrderpriByTableid(String orderId, double price);

    /**
     * 通过日期获取当天的订单列表
     * @param page
     * @param orderDate 日期时间，格式“2022-01-20”
     * @return
     */
    IPage<Order> getOrderListByDate(@Param("page") Page<Order> page,
                                    String orderDate);

    String getOrderstaByTableid(String tableId);

    String getTableIdByorderid(String orderId);


    int updateOrderstakong(String orderId);

    int updateOrderstache(String orderId);

    /**
     * 根据时间获得一段时间内订单数及订单总金额
     * 两个都无默认查全部
     * @param startDate 开始时间, 非必填
     * @param endDate 结束时间, 非必填
     * @return
     */
    Map<String, Object> getOrderSumAndPriceByDate(String startDate,
                                                  String endDate);
}
