package cn.jason.rabbitmq.springboot.pattern.ack;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: AckRabbitConfig
 * @author: Jason
 * @date: 2020/3/8 18:35
 * @description: TODO
 */
@Configuration
public class AckRabbitConfig {
    static final String FACTORY_NAME = "AckRabbitFactory";
    static final String EXCHANGE_NAME = "AckRabbitExchange";
    static final String QUEUE_NAME = "AckRabbitQueue";
    static final String KEY_NAME = "AckRabbitKey";

    @Bean(FACTORY_NAME)
    public RabbitListenerContainerFactory myFactory(ConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory containerFactory=
                new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        /*设置消费者的消息确认模式
            NONE 没有ack的意思,对应rabbitMQ的自动确认模式
            MANUAL 手动模式,对应rabbitMQ的显式确认模式
            AUTO 自动模式,对应rabbitMQ的显式确认模式 和NONE的区别在于这个通过spring事物，没有异常自动提交*/
        containerFactory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return containerFactory;
    }
}
