package com.scuec.lawyersys.controller;

import com.scuec.lawyersys.dao.PersonDao;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/person")
@Slf4j
public class PersonController {

    @Resource
    PersonDao personDao;
    /**
     *
     * @return
     */
    @GetMapping("/pers")
    public String getPersonList(){
        log.warn("热部署");
        log.info("测试完成");
        return "emp/AllList";
    }
}
