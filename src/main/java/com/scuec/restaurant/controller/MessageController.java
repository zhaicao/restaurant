package com.scuec.restaurant.controller;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Message;
import com.scuec.restaurant.service.MessageService;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/message")
@ApiOperation(value = "消息管理", notes = "消息管理相关业务")
@Slf4j
public class MessageController {
    @Autowired
    private MessageService messageService;

    @PostMapping("/addMessage")
    @ApiOperation(value = "新增消息", notes = "新增一个消息")
    public String addMessage(@RequestBody Message message){
        int result = messageService.addMessage(message.getMessageContent());
        if (result == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Message Error");
    }
}
