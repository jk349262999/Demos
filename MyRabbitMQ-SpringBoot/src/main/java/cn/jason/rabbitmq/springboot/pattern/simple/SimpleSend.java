package cn.jason.rabbitmq.springboot.pattern.simple;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName: Send
 * @Author: Jason
 * @Date: 2020/3/5 10:55
 * @Description: TODO   发送者
 */
@Component
public class SimpleSend {

    @Autowired
    private RabbitTemplate template;

    public void sendHello(){
        String date = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());//24小时制
        String context = "hello " + date;
        System.out.println("Simple_Sender : " + context);
        //简单对列的情况下routingKey即为Q名
        this.template.convertAndSend("simple_hello", context);
    }
}
