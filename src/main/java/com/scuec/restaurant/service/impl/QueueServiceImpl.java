package com.scuec.restaurant.service.impl;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.dao.BlockingQueueDao;
import com.scuec.restaurant.entities.QueueElement;
import com.scuec.restaurant.service.QueueService;
import com.scuec.restaurant.utils.DateUtil;
import com.scuec.restaurant.utils.SMSUtil;
import kotlin.jvm.Synchronized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ArrayBlockingQueue;

@Service
public class QueueServiceImpl implements QueueService {

    @Autowired
    private BlockingQueueDao blockingQueueDao;

    @Override
    @Synchronized
    public QueueElement putElement(QueueElement queueElement) {
        int elementNo = 1;
        int currentQueueSize = blockingQueueDao.getQueueSize();
        // 若队列不为空，即存在排队人员
        if (!blockingQueueDao.isEmpty()) {
            QueueElement firstElement = blockingQueueDao.getFirstElement();
            try {
                elementNo = Integer.parseInt(firstElement.getElementNum()) + currentQueueSize;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                throw new GlobalException(ResponseCode.ERROR, "Queue formation occur error");
            }
        }
        // 设置queueElement属性
        queueElement.setElementNum(String.format("%04d", elementNo));
        queueElement.setElementDate(DateUtil.getCurrentDate());
        queueElement.setElementFrontNum(currentQueueSize);
        // 新元素入队
        if (blockingQueueDao.putElement(queueElement)) {
            SMSUtil.sendMsg(queueElement.getElementUserPhone(),
                    "1743459",
                    new String[]{queueElement.getElementNum(), String.valueOf(queueElement.getElementFrontNum())});
            return queueElement;
        } else
            throw new GlobalException(ResponseCode.ERROR, "Queue has been full");
    }

    @Override
    public QueueElement takeElement() {
        QueueElement queueElement = blockingQueueDao.takeElement();
        if (queueElement != null) {
            // 到号提醒，获取队首元素并发送提醒
            QueueElement firstElement = blockingQueueDao.getFirstElement();
            SMSUtil.sendMsg(firstElement.getElementUserPhone(),
                    "1743468",
                    new String[]{firstElement.getElementNum()});
            return queueElement;
        }else
            return null;
    }

    @Override
     public ArrayBlockingQueue<Object> getAllQueue() {
        return blockingQueueDao.getAllQueue();
    }

    @Override
    public QueueElement removeElement(QueueElement queueElement) {
        if (blockingQueueDao.removeElement(queueElement)) {
            // 取消提醒
            SMSUtil.sendMsg(queueElement.getElementUserPhone(),
                    "1743492",
                    new String[]{queueElement.getElementNum()});
            return queueElement;
        }else
            throw new GlobalException(ResponseCode.ERROR, "Fail to delete the element");
    }

    @Override
    @Synchronized
    public QueueElement reputElement(QueueElement queueElement) {
        if (blockingQueueDao.removeElement(queueElement)) {
            queueElement.setElementFrontNum(blockingQueueDao.getQueueSize());
            if(blockingQueueDao.putElement(queueElement)) {
                // 重排或过号提醒
                SMSUtil.sendMsg(queueElement.getElementUserPhone(),
                        "1743475",
                        new String[]{queueElement.getElementNum(), String.valueOf(queueElement.getElementFrontNum())});
                return queueElement;
            } else
                throw new GlobalException(ResponseCode.ERROR, "Fail to put the element in re-arrange");
        } else
            throw new GlobalException(ResponseCode.ERROR, "Fail to remove the element in re-arrange");
    }
}
