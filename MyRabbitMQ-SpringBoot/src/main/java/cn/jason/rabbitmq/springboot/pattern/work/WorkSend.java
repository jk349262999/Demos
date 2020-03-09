package cn.jason.rabbitmq.springboot.pattern.work;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * WorkSend
 *
 * @author: Jason
 * @date: 2020/3/9
 */
@Component
public class WorkSend {
    @Autowired
    private RabbitTemplate template;


    public void sendHello(int i) {
        //24小时制
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
        String context = "hello " + i + " " + date;
        System.out.println("Work_Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.template.convertAndSend("work_queue", context);
    }

//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//
//    public void send() {
//        for (int i = 0; i < 100; i++) {
//            rabbitTemplate.convertAndSend("workQueue", "this is a message");
//        }
//    }
}
