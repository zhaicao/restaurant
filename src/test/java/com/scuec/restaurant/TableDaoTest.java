package com.scuec.restaurant;

import com.scuec.restaurant.dao.TableDao;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.*;

@SpringBootTest
@Slf4j
public class TableDaoTest {

    @Autowired
    private TableDao tableDao;
    @Test
    public void testGetCount(){
        int res  = tableDao.getUsedTableSum();
        log.warn(String.valueOf("count:" + res));
    }
    @Test
    public void testArrayBlockingQueue() throws InterruptedException {
        // 声明一个容量为10的缓存队列
        BlockingQueue<String> queue = new ArrayBlockingQueue<String>(10);
        queue.add("sss");
        queue.offer("aaa", 5, TimeUnit.SECONDS);
        queue.offer("bbb", 5, TimeUnit.SECONDS);
        System.out.println(queue.size());
        System.out.println(queue.remainingCapacity());
        System.out.println(queue.poll(5, TimeUnit.SECONDS));
/*        //测试添加null对象
        try{
        queue.add(null);
        }
        catch(Exception ee){
        ee.printStackTrace();
        }
        try{
        queue.offer(null);
        }
        catch(Exception ee){
        ee.printStackTrace();
        }
        try{
        queue.put(null);
        }
        catch(Exception ee){
        ee.printStackTrace();
        }
        Producer producer1 = new Producer(queue);
//        Producer producer2 = new Producer(queue);
//        Producer producer3 = new Producer(queue);
        Consumer consumer = new Consumer(queue);

        // 借助Executors
        ExecutorService service = Executors.newCachedThreadPool();

        // 启动线程
        service.execute(producer1);
//        service.execute(producer2);
//        service.execute(producer3);
        service.execute(consumer);

        // 执行10s
        Thread.sleep(30 * 1000);
        producer1.stop();
//        producer2.stop();
//        producer3.stop();
        Thread.sleep(2000);
        // 退出Executor
        service.shutdown();
    }*/

/*
    String currentWaitingPerson=""; //当前轮到的那一位
    boolean resourceBusy = false; //默认不需要排队
    if(resourceBusy && !person.equals(currentWaitingPerson)){
        ArrayBlockingQueue.put(person);
    }
    else{
        person 去使用资源;
    }
    //当前使用资源的人任务结束，释放掉资源，需要通知下一个排队人
    function notifyWaitingPerson(){

        if(currentTask is end){
            person = ArrayBlockingQueue.take()
            currentWaitingPerson = person
            if(null!=person){
                notify(person)
                schedule.execute(new Runnable({
                if(person 没有来使用资源){
                    //递归调用,通知下一个
                    notifyWaitingPerson()
                }
                }),指定一个超时时间)
            }
        }
    }*/
    }
}
