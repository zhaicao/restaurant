package com.scuec.restaurant.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.scuec.restaurant.entities.Message;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageDao {
    /**
     * 增加消息
     * @param messageUserid
     * @param messageOrderid
     * @param messageType 消息类型，1：催单，2：普通消息
     * @param messageContent
     * @return
     */
    int addMessage(String messageUserid,
            String messageOrderid,
            int messageType,
            String messageContent);

    IPage<Message> getMessageList(@Param("page") Page<Message> page,
                                  String msgOrderId,
                                  int msgType,
                                  String startDate,
                                  String endDate);

    int updateMessage(String messageId,
                   String messageContent,
                   int messageComplete,
                   int messageDel);

    /**
     * 获取消息总数
     * @param msgType 消息类型，1：催单，2：普通消息
     * @return
     */
    int getMsgCount(String orderId, int msgType);
}
