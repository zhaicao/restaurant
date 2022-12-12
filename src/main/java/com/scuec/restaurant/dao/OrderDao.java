package com.scuec.restaurant.dao;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.Table;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderDao {

    @Delete("delete from `m_order` where orderid = #{orderId}")
    int deleteOrder(String orderId);

    int updateOrdersta(String orderId);

    int updateOrdersta1(String orderId);

    IPage<Order> getOrderList(@Param("page") Page<Order> page,
                              String orderStatus);


    int addOrder(String orderId, String tableNo, double orderPrice, String orderStatus);

    Order getOrderById(String orderId);
}
