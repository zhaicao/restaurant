package com.scuec.springboot.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Date;

@Data
@Component
public class LawyerInfo {
    private Integer liid;
    private Integer lfid;
    private Integer urid;
    private String name;
    private Boolean sex;
    private Date brithday;
    private String idcard;
    private String tel;
    private String password;
    private String email;
    private String licenseNum;
    private Date licenseYear;
    private Boolean isReg;
    private String picName;
    private Date createdate;
    private String remark;
    private Boolean lawyerState;
    private Integer position;
    private Integer showNum;
    private String introduce;
}
