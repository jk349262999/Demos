package cn.jason.rabbitmq.springboot.pattern.simple;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @className: cn.jason.rabbitmq.springboot.pattern.simple
 * @author: Jason
 * @date: 2020/3/5 10:47
 * @description: TODO
 */
@Configuration
public class SimpleRabbitConfig {

    /**
     * 声明queue
     *
     * @return
     */
    @Bean
    public Queue simpleQueue() {
        return new Queue("simple_hello");
    }

}
