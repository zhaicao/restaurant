package com.scuec.restaurant.dao;

import com.scuec.restaurant.constant.exception.GlobalException;
import com.scuec.restaurant.constant.response.ResponseCode;
import com.scuec.restaurant.entities.QueueElement;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * ArrayBlockingQueue实现类似Dao的功能
 */
@Repository
public class BlockingQueueDao {

    @Value("${queue.timeout-threshold: 3}")
    private int timeoutThreshold;

    public static ArrayBlockingQueue<Object> queue;

    // 初始化时设置Queue Capacity
    // Spring会自动调用，设定默认值
    @Value("${queue.capacity: 100}")
    public void setQueue(int capacity) {
        this.queue = new ArrayBlockingQueue<Object>(capacity);
    }

    /**
     * 入队
     * @param queueElement 入队元素
     * @return
     */
    public Boolean putElement(QueueElement queueElement) {
        System.out.println(this.timeoutThreshold);
        try {
            return this.queue.offer(queueElement, this.timeoutThreshold, TimeUnit.SECONDS);
        } catch (InterruptedException e){
            throw new GlobalException(ResponseCode.ERROR, "Queue occurs the InterruptedException while Offering");
        }
    }

    /**
     * 出队
     * @return 出队元素
     */
    public QueueElement takeElement() {
        try {
            return (QueueElement) this.queue.poll(this.timeoutThreshold, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            throw new GlobalException(ResponseCode.ERROR, "Queue occurs the InterruptedException while Taking");
        }
    }

    /**
     * 获取队头元素
     * @return
     */
    public QueueElement getFirstElement() {
        return (QueueElement) this.queue.peek();
    }

    /**
     * 获取队列长度，即有多少元素
     * @return
     */
    public int getQueueSize() {
        return this.queue.size();
    }

    /**
     * 返回队列是否为空
     * @return
     */
    public boolean isEmpty() {
        return this.queue.isEmpty();
    }

    /**
     * 返回整个队列
     * @return
     */
    public ArrayBlockingQueue<Object> getAllQueue() {
        return this.queue;
    }

    /**
     * 队列中删除元素
     * @param queueElement 待删除的元素，全部属性要匹配
     * @return
     */
    public boolean removeElement(QueueElement queueElement) {
        return this.queue.remove(queueElement);
    }
}
