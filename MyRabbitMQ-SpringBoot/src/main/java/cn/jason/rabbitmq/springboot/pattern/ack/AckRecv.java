package cn.jason.rabbitmq.springboot.pattern.ack;

import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @ClassName: AckRecv
 * @Author: Jason
 * @Date: 2020/3/8 18:36
 * @Description: TODO
 */
@Component
@RabbitListener(
        containerFactory = AckRabbitConfig.FACTORY_NAME,
        bindings = @QueueBinding(
                value = @Queue(AckRabbitConfig.QUEUE_NAME),
                exchange = @Exchange(value = AckRabbitConfig.EXCHANGE_NAME, type = ExchangeTypes.DIRECT),
                key = AckRabbitConfig.KEY_NAME))
public class AckRecv {
    @RabbitHandler
    public void onMessage(@Payload String msg,
                          @Headers Map<String, Object> headers,
                          Channel channel) throws Exception {
        System.out.println(msg);
        try {
//            if (1 == 1)
//                throw new Exception();
            //消息确认,(deliveryTag,multiple是否确认所有消息)
            channel.basicAck((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false);
        } catch (Exception e) {
            //消息拒绝(deliveryTag,multiple,requeue拒绝后是否重新回到队列)
            channel.basicNack((Long) headers.get(AmqpHeaders.DELIVERY_TAG), false, false);
            // 拒绝一条
            // channel.basicReject();
        }
    }
}
