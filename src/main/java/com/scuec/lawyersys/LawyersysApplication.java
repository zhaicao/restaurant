package com.scuec.lawyersys;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"com.scuec.lawyersys", "com.scuec.lawyersys.constant.exception"})
@MapperScan(value = "com.scuec.lawyersys.dao")
public class LawyersysApplication {

    public static void main(String[] args) {
        SpringApplication.run(LawyersysApplication.class, args);
    }

}
