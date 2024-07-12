package com.scuec.restaurant.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.Message;
import com.scuec.restaurant.service.MessageService;
import com.scuec.restaurant.utils.CommUtil;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

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
        int result = messageService.addMessage(message.getMessageUserId(),
                message.getMessageOrderId(),
                message.getMessageType(),
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
            @ApiImplicitParam(name = "msgOrderId", value = "订单号", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "msgType", value = "消息类型，1：催单，2：普通消息", required = true, dataType = "int", paramType = "query"),
            @ApiImplicitParam(name = "startDate", value = "每页显示多少条记录", required = true, dataType = "String", paramType = "query"),
            @ApiImplicitParam(name = "endDate", value = "每页显示多少条记录", required = true, dataType = "String", paramType = "query"),
    })
    public IPage<Message> getMessageList(int currentPage,
                                         int pageSize,
                                         String msgOrderId,
                                         int msgType,
                                         int isComplete,
                                         String startDate,
                                         String endDate){

        return messageService.getMessageList(currentPage, pageSize, msgOrderId, msgType, isComplete, startDate, endDate);
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

    @PutMapping("/completeUrgeMsg")
    @ApiOperation(value = "通过信息ids批量处理催单消息", notes = "处理催单")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "messageIds", value = "信息ID的数组，格式: 0f359886967011ed856902004c4f4f50, 10", required = true, dataType = "String[]", paramType = "query")
    })
    public String completeUrgeMsg(String[] messageIds){
        int res = messageService.completeUrgeMsg(messageIds);
        if (res != 0 )
            return "success";
        else
            throw new GlobalException(ResponseCode.ERROR, "updateMessageState Message Error, messageId:" + messageIds);
    }
}
