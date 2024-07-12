package com.scuec.restaurant.entities.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
public class RevenueChartVO {
    private List<String> legend;
    private List<String> category;
    private List<Double> revenue;
    private List<Integer> orderQuantity;


    public RevenueChartVO(List<String> legend,
                          List<String> category,
                          List<Double> revenue,
                          List<Integer> orderQuantity) {
        this.legend = legend;
        this.category = category;
        this.revenue = revenue;
        this.orderQuantity = orderQuantity;
    }
}
