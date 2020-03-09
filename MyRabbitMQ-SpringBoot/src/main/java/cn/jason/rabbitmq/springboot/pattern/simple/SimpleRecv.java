package cn.jason.rabbitmq.springboot.pattern.simple;

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
@RabbitListener(queues = "simple_hello")
public class SimpleRecv {

    @RabbitHandler
    public void process(String hello) {
        System.out.println("Simple_Receiver  : " + hello);
    }

}
