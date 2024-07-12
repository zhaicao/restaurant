package com.scuec.restaurant.entities;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class Sms {
    String phoneNumber;
    String templateId;
    String[] templateParams;
}
