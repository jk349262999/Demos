package cn.jason.rabbitmq.springboot.pattern.ack;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @ClassName: AckSend
 * @Author: Jason
 * @Date: 2020/3/8 19:08
 * @Description: TODO
 */
@Component
public class AckSend {
    @Autowired
    private RabbitTemplate rabbitTemplate;
    public void send() {
        this.rabbitTemplate.convertAndSend(AckRabbitConfig.EXCHANGE_NAME, AckRabbitConfig.KEY_NAME, " hi, i am message [ack.message]");
    }
}
