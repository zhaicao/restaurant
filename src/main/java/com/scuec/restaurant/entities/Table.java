package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Table {
    private String tableId;

    private String tableNo;

    private String tPeople;

    private String tOrderid;

    private int tableUse;

    private int tableDel;
}
