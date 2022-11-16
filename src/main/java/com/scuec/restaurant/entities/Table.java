package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Table {
    private String tId;

    private String tNo;

    private String tPeople;

    private String tOrderid;

    private int tableUse;

    private int tableDel;
}
