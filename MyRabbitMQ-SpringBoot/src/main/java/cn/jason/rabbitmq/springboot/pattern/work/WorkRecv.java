package cn.jason.rabbitmq.springboot.pattern.work;

import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: Recv
 * @author: Jason
 * @date: 2020/3/5 10:59
 * @description: TODO   接收者
 */
@Component
public class WorkRecv {

    /**
     * queuesToDeclare： 声明并绑定queue
     * quque：绑定
     */
    @RabbitListener(containerFactory = "workListenerFactory", queuesToDeclare = @Queue("work_queue"))
    public void onMessage1(String msg) throws InterruptedException {
        Thread.sleep(100);
        System.out.println("Work_Receiver1  : " + msg);
    }

    @RabbitListener(containerFactory = "workListenerFactory", queuesToDeclare = @Queue("work_queue"))
    public void onMessage2(String msg) throws InterruptedException {
        Thread.sleep(200);
        System.out.println("Work_Receiver2  : " + msg);
    }
}
