package com.scuec.restaurant.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.*;

import java.util.Locale;

@RestController
@RequestMapping("/test")
@Slf4j
public class TestController {

    @Autowired
    private MessageSource messageSource;
    /**
     * 后端国际化
     * @param language
     * @return
     */
    @GetMapping("/i18n")
    public String testI18n(@RequestHeader("accept-language") String language,
                           @RequestParam("username") String username,
                           @RequestParam("password") String password){
        String firstLang = language.split(",")[0];
        if (firstLang.equals("en"))
            return messageSource.getMessage("login.fail", new String[0], Locale.US);
        else
            return messageSource.getMessage("login.fail", new String[0], Locale.CHINA);
    }
}
