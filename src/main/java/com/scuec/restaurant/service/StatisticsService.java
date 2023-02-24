package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Order;
import com.scuec.restaurant.entities.vo.RevenueChartVO;
import com.scuec.restaurant.entities.vo.RevenueDetailVO;
import com.scuec.restaurant.entities.vo.RevenuePanelGroupVO;

public interface StatisticsService {
    /**
     * 获取订单列表
     * @param currentPage
     * @param pageSize
     * @param startDate 检索开始时间
     * @param endDate 检索结束时间
     * @return
     */
    IPage<RevenueDetailVO> getRevenueOrderList(int currentPage, int pageSize, String startDate, String endDate);

    /**
     * 获取统计图表数据
     * 若startDate和endDate均空，则默认展示迄今30天内的数据
     * 若startDate有，endDate无；
     *         startDate迄今超过30天，则默认展示迄今30天内的数据
     *         若没有超过30天，则展示从startDate到当天的数据
     * 若startDate无，endDate有；
     *         默认展示从endDate往前推30天内的数据
     * 若startDate和endDate均有：
     *         若startDate与endDate之间超过30天，则默认展示endDate往前推30天内的数据
     *         若两者未超过30天，则默认展示两者之间全部数据
     * @param startDate 开始时间，可选
     * @param endDate 结束时间，可选
     * @return
     */
    RevenueChartVO getRevenueChart(String startDate, String endDate);

    /**
     * 获取看板上panelGroup的数据
     * 若startDate和endDate均空，则默认展示迄今30天内的数据
     * 若startDate有，endDate无；
     *         startDate迄今超过30天，则默认展示迄今30天内的数据
     *         若没有超过30天，则展示从startDate到当天的数据
     * 若startDate无，endDate有；
     *         默认展示从endDate往前推30天内的数据
     * 若startDate和endDate均有：
     *         若startDate与endDate之间超过30天，则默认展示endDate往前推30天内的数据
     *         若两者未超过30天，则默认展示两者之间全部数据
     * @param startDate 开始时间
     * @param endDate 结束时间
     * @return
     */
    RevenuePanelGroupVO getRevenuePanelGroup(String startDate, String endDate);
}
