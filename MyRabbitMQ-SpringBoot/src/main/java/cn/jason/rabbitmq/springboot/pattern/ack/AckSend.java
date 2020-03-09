package cn.jason.rabbitmq.springboot.pattern.ack;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @className: AckSend
 * @author: Jason
 * @date: 2020/3/8 19:08
 * @description: TODO
 */
@Component
public class AckSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send() {
        this.rabbitTemplate.convertAndSend(AckRabbitConfig.EXCHANGE_NAME, AckRabbitConfig.KEY_NAME, " hi, i am message [ack.message]");
    }
}
