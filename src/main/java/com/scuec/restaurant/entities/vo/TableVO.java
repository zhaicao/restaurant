package com.scuec.restaurant.entities.vo;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class TableVO {

    private String tableId;

    private String tableName;

    private String tableDescription;

    private String tableOrderId;

    private int tableUse;

    private String orderDate;


}
