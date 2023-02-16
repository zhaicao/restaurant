package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Table {
    private String tableId;

    private String tableName;

    private String tableDescription;

    private String tableOrderId;

    private int tableUse;

    private int tableDel;

    private Order order;
}
