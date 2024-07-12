package com.scuec.restaurant.controller;

import com.scuec.restaurant.entities.QueueElement;
import com.scuec.restaurant.service.QueueService;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.ArrayBlockingQueue;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(value = "/queue")
@ApiOperation(value = "排队系统", notes = "排队取号相关业务")
@Slf4j
public class QueueController {
    @Autowired
    private QueueService queueService;

    @GetMapping("/takeNumber")
    @ApiOperation(value = "入座", notes = "队列出队")
    public QueueElement takeQueueElement(){
        return queueService.takeElement();
    }
    @PostMapping("/getNumber")
    @ApiOperation(value = "取号", notes = "队列入队，成功返回QueueElement")
    @ApiImplicitParam(name = "queueElement",
            value = "QueueElement对象，其中elementUserPhone必填",
            required = true,
            dataType="QueueElement",
            paramType = "body")
    public QueueElement getQueueElement(@RequestBody QueueElement queueElement){
        System.out.println(queueElement);
        return queueService.putElement(queueElement);
    }

    @PostMapping("/regetNumber")
    @ApiOperation(value = "重新取号（排队）", notes = "队列入队成功返回QueueElement")
    @ApiImplicitParam(name = "queueElement",
            value = "原QueueElement对象，其中所有属性均必填",
            required = true,
            dataType="QueueElement",
            paramType = "body")
    public QueueElement regetQueueElement(@RequestBody QueueElement queueElement){
        return queueService.reputElement(queueElement);
    }

    @GetMapping("/getAllNumber")
    @ApiOperation(value = "获取队列中全部元素", notes = "获取队列中全部元素")
    public ArrayBlockingQueue<Object> getQueue() {
       return queueService.getAllQueue();
    }

    @DeleteMapping("/delNumber")
    @ApiOperation(value = "删除队列中的指定元素", notes = "获取队列中全部元素")
    @ApiImplicitParam(name = "queueElement",
            value = "QueueElement对象，其中所有属性均必填",
            required = true,
            dataType="QueueElement",
            paramType = "body")
    public QueueElement delQueue(@RequestBody QueueElement queueElement) {
        return queueService.removeElement(queueElement);
    }
}
