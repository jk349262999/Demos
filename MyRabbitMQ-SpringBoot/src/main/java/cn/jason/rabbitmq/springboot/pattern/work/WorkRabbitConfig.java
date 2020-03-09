package cn.jason.rabbitmq.springboot.pattern.work;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.RabbitListenerContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @ClassName: WorkRabbitConfig
 * @Author: Jason
 * @Date: 2020/3/7 17:00
 * @Description: TODO
 */
@Configuration
public class WorkRabbitConfig {
    @Bean("workListenerFactory")
    public RabbitListenerContainerFactory workFactory(ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory containerFactory =
                new SimpleRabbitListenerContainerFactory();
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setPrefetchCount(1);
        /*设置消费者的消息确认模式
            NONE 没有ack的意思,对应rabbitMQ的自动确认模式
            MANUAL 手动模式,对应rabbitMQ的显式确认模式
            AUTO 自动模式,对应rabbitMQ的显式确认模式 和NONE的区别在于这个通过spring事物，没有异常自动提交*/
        containerFactory.setAcknowledgeMode(
                AcknowledgeMode.AUTO);
        return containerFactory;
    }
}
