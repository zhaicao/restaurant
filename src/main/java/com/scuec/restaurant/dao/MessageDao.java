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

    /**
     * 根据条件查询消息列表
     * @param page
     * @param msgOrderId
     * @param msgType
     * @param isComplete 仅当msgtype为催单时，该条件生效
     * @param startDate
     * @param endDate
     * @return
     */
    IPage<Message> getMessageList(@Param("page") Page<Message> page,
                                  String msgOrderId,
                                  int msgType,
                                  int isComplete,
                                  String startDate,
                                  String endDate);

    /**
     * 批量更新消息
     * @param messageIds messageId的集合，若多个，则格式"'1','2','3'"
     * @param messageContent
     * @param messageComplete
     * @param messageDel
     * @return
     */
    int updateMessage(String messageIds,
                   String messageContent,
                   int messageComplete,
                   int messageDel);

    /**
     * 获取消息总数
     * @param orderId 订单编号，非必填
     * @param msgType 消息类型，1：催单，2：普通消息，非必填
     * @return
     */
    int getMsgCount(String orderId,
                    int msgType,
                    String startDate,
                    String endDate);
}
