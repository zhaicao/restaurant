package com.scuec.restaurant.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.scuec.restaurant.entities.Message;
import org.springframework.stereotype.Service;

@Service
public interface MessageService {
    int addMessage(String messageContent);

    IPage<Message> getMessageList(int currentPage, int pageSize);

    int updateMessage(String messageId, String messageContent);

    int deleteMessageById(String messageId);

    int updateMessageState(String messageId);
}
