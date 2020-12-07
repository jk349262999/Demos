package cn.jason.rabbitmq.springboot.pattern.reactive.custom;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * @author jinkai
 */
@Component
@RequiredArgsConstructor
public class MessageListenerContainerFactory {
    private final ConnectionFactory connectionFactory;
    private final AmqpAdmin amqpAdmin;

    public MessageListenerContainer createMessageListenerContainer(String queueName) {
        amqpAdmin.declareQueue(QueueBuilder.durable(queueName).build());
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.addQueueNames(queueName);
        container.setPrefetchCount(1);
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return container;
    }
}