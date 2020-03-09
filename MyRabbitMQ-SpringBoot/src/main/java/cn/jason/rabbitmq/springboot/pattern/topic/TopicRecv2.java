package cn.jason.rabbitmq.springboot.pattern.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @className: TopicRecv2
 * @author: Jason
 * @date: 2020/3/8 18:13
 * @description: TODO
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.TOPIC_QUEUE2)
public class TopicRecv2 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("TopicRecv2  : " + hello);
    }
}
