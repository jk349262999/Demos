package cn.jason.rabbitmq.springboot.pattern.topic;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: TopicSend
 * @author: Jason
 * @date: 2020/3/8 18:16
 * @description: TODO
 */
@Component
public class TopicSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send() {
        this.rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE, "topic.message", "Sender : hi, i am message 1[topic.message]");
        this.rabbitTemplate.convertAndSend(TopicRabbitConfig.TOPIC_EXCHANGE, "top.xx", "Sender : hi, i am message 2[top.xx]");
    }
}
