package com.scuec.restaurant.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.dao.MessageDao;
import com.scuec.restaurant.entities.Message;
import com.scuec.restaurant.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class MessageServiceimpl implements MessageService {
    @Autowired
    private MessageDao messageDao;


    @Override
    public int addMessage(String messageUserid, String messageOrderid, String messageContent) {
        return messageDao.addMessage(messageUserid,messageOrderid,messageContent);
    }

    @Override
    public IPage<Message> getMessageList(int currentPage, int pageSize) {
        return messageDao.getMessageList(new Page<>(currentPage, pageSize));
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
    public int updateMessageState(String messageId) {

        return messageDao.updateMessage(messageId,null,1,0);
    }
}
