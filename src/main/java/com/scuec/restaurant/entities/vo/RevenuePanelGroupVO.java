package com.scuec.restaurant.entities.vo;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class RevenuePanelGroupVO {
    private int consumers;
    private int orders;
    private int urgencies;
    private Double turnover;

    public RevenuePanelGroupVO() {}

    public RevenuePanelGroupVO(int usedTableSum,
                               int orderSum,
                               int msgCount,
                               double orderTotalPrice) {
        this.consumers = usedTableSum;
        this.orders = orderSum;
        this.urgencies = msgCount;
        this.turnover = orderTotalPrice;
    }
}
