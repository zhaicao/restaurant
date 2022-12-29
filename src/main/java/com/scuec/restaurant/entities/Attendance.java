package com.scuec.restaurant.entities;


import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class Attendance {

    private String attendanceId;

    private int attendanceType;

    private Date attendanceDate;

    private String userId;


    private Date attendanceStart;

    private Date attendanceFinish;

    private int attendanceDel;

    private User user;
}
