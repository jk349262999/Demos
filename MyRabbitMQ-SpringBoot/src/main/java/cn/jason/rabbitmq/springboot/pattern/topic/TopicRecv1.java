package cn.jason.rabbitmq.springboot.pattern.topic;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @ClassName: TopicRecv1
 * @Author: Jason
 * @Date: 2020/3/8 18:13
 * @Description: TODO
 */
@Component
@RabbitListener(queues = TopicRabbitConfig.TOPIC_QUEUE1)
public class TopicRecv1 {
    @RabbitHandler
    public void process(String hello) {
        System.out.println("TopicRecv1  : " + hello);
    }
}
