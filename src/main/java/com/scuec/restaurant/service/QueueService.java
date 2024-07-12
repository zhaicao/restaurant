package com.scuec.restaurant.service;

import com.scuec.restaurant.entities.QueueElement;

import java.util.concurrent.ArrayBlockingQueue;

public interface QueueService {
     /**
      * 取号（入队/生产）
      * @param queueElement
      * @return
      */
     QueueElement putElement(QueueElement queueElement);

     /**
      * 入座（出队/消费）
      * @return
      */
     QueueElement takeElement();

     /**
      * 获取全部队列元素
      * @return
      */
     ArrayBlockingQueue<Object> getAllQueue();

     /**
      * 删除队列中元素
      * @param queueElement
      * @return
      */
     QueueElement removeElement(QueueElement queueElement);

     /**
      * 重新排队，将元素放在队尾
      * @param queueElement
      * @return
      */
     QueueElement reputElement(QueueElement queueElement);
}
