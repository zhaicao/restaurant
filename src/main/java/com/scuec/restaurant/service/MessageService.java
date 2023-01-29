package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Message;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface MessageService {
    int addMessage(String messageUserid, String messageOrderid, int messageType, String messageContent);

    IPage<Message> getMessageList(int currentPage, int pageSize, String msgOrderId, int msgType, int isComplete, String startDate, String endDate);

    int updateMessage(String messageId, String messageContent);

    int deleteMessageById(String messageId);

    /**
     * 处理催单消息
     * @param messageIds 消息Id数组
     * @return
     */
    int completeUrgeMsg(String[] messageIds);

    /**
     * 获取消息总数
     * @param msgType 消息类型
     * @return
     */
    int getMessageSum(String orderId, int msgType);
}
