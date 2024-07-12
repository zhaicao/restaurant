package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MessageDao;
import com.scuec.restaurant.entities.Message;
import com.scuec.restaurant.service.MessageService;
import com.scuec.restaurant.utils.CommUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceimpl implements MessageService {
    @Autowired
    private MessageDao messageDao;


    @Override
    public int addMessage(String messageUserid, String messageOrderid, int messageType, String messageContent) {
        return messageDao.addMessage(messageUserid,messageOrderid, messageType, messageContent);
    }

    @Override
    public IPage<Message> getMessageList(int currentPage, int pageSize, String msgOrderId, int msgType, int isComplete, String startDate, String endDate) {
        return messageDao.getMessageList(new Page<>(currentPage, pageSize), msgOrderId, msgType, isComplete, startDate, endDate);
    }

    @Override
    public int updateMessage(String messageId, String messageContent) {
        return messageDao.updateMessage(messageId,messageContent,0,0);
    }

    @Override
    public int deleteMessageById(String messageId) {
        return messageDao.updateMessage(messageId,null,0,1);
    }

    @Override
    public int completeUrgeMsg(String[] messageIds) {
        String ids = CommUtil.arr2Str(messageIds); // Arr to Str
        return messageDao.updateMessage(ids,null,1,0);
    }

    @Override
    public int getMessageSum(String orderId, int msgType) {
        return messageDao.getMsgCount(orderId, msgType, null, null);
    }
}
