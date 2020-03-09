package cn.jason.rabbitmq.springboot.pattern.topic;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @className: TopicRabbitConfig
 * @author: Jason
 * @date: 2020/3/7 17:01
 * @description: TODO
 */
@Configuration
public class TopicRabbitConfig {
    final static String TOPIC_QUEUE1 = "topic_queue1";
    final static String TOPIC_QUEUE2 = "topic_queue2";
    final static String TOPIC_EXCHANGE = "topic_exchange";

    @Bean
    public Queue queueMessage() {
        return new Queue(TopicRabbitConfig.TOPIC_QUEUE1);
    }

    @Bean
    public Queue queueMessageAll() {
        return new Queue(TopicRabbitConfig.TOPIC_QUEUE2);
    }

    /**
     * 声明一个Topic类型的交换机
     * @return
     */
    @Bean
    TopicExchange exchange() {
        return new TopicExchange(TOPIC_EXCHANGE);
    }

    /**
     * 绑定Q到交换机,并且指定routingKey
     * @param queueMessage
     * @param exchange
     * @return
     */
    @Bean
    Binding bindingExchangeMessage(Queue queueMessage, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessage).to(exchange).with("topic.*");
    }

    @Bean
    Binding bindingExchangeMessages(Queue queueMessageAll, TopicExchange exchange) {
        return BindingBuilder.bind(queueMessageAll).to(exchange).with("*.*");
    }
}
