package com.scuec.restaurant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Message;
import com.scuec.restaurant.service.MessageService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/message")
@Slf4j
public class MessageController {
    @Autowired
    private MessageService messageService;


    @PostMapping("/addMessage")
    @ApiOperation(value = "新增信息", notes = "新增一条信息")
    public String addMessage(@RequestBody Message message){
        int result = messageService.addMessage(message.getMessageUserid(),
                message.getMessageOrderid(),
                message.getMessageContent());
        if (result == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Add Message Error");
    }

    /**
     * 获取信息list
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/getMessageList")
    @ApiOperation(value = "分页显示message列表", notes = "分页显示message列表")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "currentPage", value = "当前页", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "pageSize", value = "每页显示多少条记录", required = true, dataType = "int", paramType = "query"),
    })
    public IPage<Message> getMessageList(int currentPage,
                                     int pageSize){

        return messageService.getMessageList(currentPage, pageSize);
    }

    @PutMapping("/updateMessage")
    @ApiOperation(value = "通过信息id更新信息", notes = "信息内容可修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "信息ID", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "messageContent", value = "信息内容", required = true, dataType = "String", paramType = "query")
    })
    public String updateMessage(String messageId, String messageContent){
        int res = messageService.updateMessage(messageId,messageContent);
        if (res == 1)
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Update Message Error, messageId:" + messageId);
    }

    @DeleteMapping("/deleteMessageById")
    @ApiOperation(value = "根据信息Id删除（逻辑删除）信息", notes = "通过信息id删除信息")
    @ApiImplicitParam(name = "messageId", value = "信息ID", required = true, dataType = "String", paramType = "query")
    public String deleteMessageById(String messageId){
        int res = messageService.deleteMessageById(messageId);
        if (res != 0 )
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "Delete Message Error, messageId:" + messageId);
    }

    @PutMapping("/updateMessageState")
    @ApiOperation(value = "通过信息id更新信息状态", notes = "信息状态修改")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageId", value = "信息ID", required = true, dataType = "String", paramType = "query")
    })
    public String updateMessageState(String messageId){
        int res = messageService.updateMessageState(messageId);
        if (res != 0 )
            return "successful";
        else
            throw new GlobalException(ResponseCode.ERROR, "updateMessageState Message Error, messageId:" + messageId);
    }
}
