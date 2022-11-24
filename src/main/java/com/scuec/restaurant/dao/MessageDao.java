package com.scuec.restaurant.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Message;
import com.scuec.restaurant.entities.Table;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface MessageDao {
    int addMessage(String messageUserid,
            String messageOrderid,
            String messageContent);

    IPage<Message> getMessageList(@Param("page") Page<Table> page);

    int updateMessage(String messageId,
                   String messageContent,
                   int messageComplete,
                   int messageDel);
}
