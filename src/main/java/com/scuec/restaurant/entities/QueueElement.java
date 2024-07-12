package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Data
@Component
//@Scope("prototype")
public class QueueElement {
    private String elementNum;
    private String elementUserPhone;
    private int elementFrontNum;
    private String elementDate;
}
