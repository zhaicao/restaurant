package com.scuec.restaurant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.vo.RevenueChartVO;
import com.scuec.restaurant.entities.vo.RevenueDetailVO;
import com.scuec.restaurant.entities.vo.RevenuePanelGroupVO;
import com.scuec.restaurant.service.StatisticsService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/statistics")
@ApiOperation(value = "统计管理", notes = "系统统计相关业务")
@Slf4j
public class StatisticsController {

    @Autowired
    private StatisticsService statisticsService;

    @GetMapping("/getRevenueOrderList")
    @ApiOperation(value = "获取营收情况列表", notes = "营收情况页面列表数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "统计查询开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "统计查询结束时间", required = true, dataType = "String", paramType = "query")
    })
    public IPage<RevenueDetailVO> getRevenueOrderList(int currentPage,
                                                 int pageSize,
                                                 String startDate,
                                                 String endDate) {
        return statisticsService.getRevenueOrderList(currentPage, pageSize, startDate, endDate);
    }

    @GetMapping("/getRevenueChart")
    @ApiOperation(value = "获取营收情况图表", notes = "营收情况页面图表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "统计查询开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "统计查询结束时间", required = true, dataType = "String", paramType = "query")
    })
    public RevenueChartVO getRevenueChart(String startDate, String endDate) {
        return statisticsService.getRevenueChart(startDate, endDate);
    }

    @GetMapping("/getRevenuePanelGroup")
    @ApiOperation(value = "获取主页看板统计数据", notes = "获取主页看板统计数据")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "startDate", value = "统计查询开始时间", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "统计查询结束时间", required = true, dataType = "String", paramType = "query")
    })
    public RevenuePanelGroupVO getRevenuePanelGroup(String startDate, String endDate) {
        return statisticsService.getRevenuePanelGroup(startDate, endDate);
    }
}
