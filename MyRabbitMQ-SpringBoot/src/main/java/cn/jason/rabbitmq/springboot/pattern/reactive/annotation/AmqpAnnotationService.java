package cn.jason.rabbitmq.springboot.pattern.reactive.annotation;

import cn.jason.rabbitmq.springboot.pattern.reactive.SendMsgModel;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Headers;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.IOException;
import java.util.Map;

/**
 * @author jinkai
 * @description
 * @since 2020/12/7
 */
@Service
@Slf4j
public class AmqpAnnotationService {

    @RabbitListener(containerFactory = "reactiveListenerFactory",queuesToDeclare = @Queue("reactive_work2"))
    public void receiveMessages1(@Payload SendMsgModel msg, Channel channel, @Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("receiveMessages1.。。");
        Mono.just(msg).map(s -> {
            log.info("AmqpAnnotationService.receiveMessages1已消费:"+msg);
            try {
                channel.basicAck(tag,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "1";
        }).subscribe();
    }

    @RabbitListener(containerFactory = "reactiveListenerFactory",queuesToDeclare = @Queue("reactive_work2"))
    public void receiveMessages2(@Payload SendMsgModel msg,Channel channel,@Header(AmqpHeaders.DELIVERY_TAG) long tag) {
        log.info("receiveMessages2.。。");
        Mono.just(msg).map(s -> {
            log.info("AmqpAnnotationService.receiveMessages2 已消费:"+msg);
            try {
                channel.basicAck(tag,false);
            } catch (IOException e) {
                e.printStackTrace();
            }
            return "2";
        }).subscribe();
    }
}
