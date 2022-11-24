package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Message;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public interface MessageService {
    int addMessage(String messageUserid, String messageOrderid, String messageContent);

    IPage<Message> getMessageList(int currentPage, int pageSize);

    int updateMessage(String messageId, String messageContent);

    int deleteMessageById(String messageId);

    int updateMessageState(String messageId);
}
