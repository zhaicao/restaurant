package com.scuec.restaurant.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.vo.RevenueDetailVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StatisticsDao {
    /**
     * 获取订单统计金额及订单明细
     * @param page page对象
     * @param startDate
     * @param endDate
     * @return
     */
    IPage<RevenueDetailVO> getRevenueList(@Param("page") Page<RevenueDetailVO> page,
                                          String startDate,
                                          String endDate);
}
