package cn.jason.rabbitmq.springboot.pattern.reactive.custom;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.amqp.rabbit.listener.MessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.Duration;

@RestController
@Slf4j
@RequiredArgsConstructor
public class AmqpReactiveController {

    private final MessageListenerContainerFactory messageListenerContainerFactory;

    @GetMapping(value = "listener", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> receiveMessagesFromQueue(@RequestParam String queueName) {
        MessageListenerContainer container = messageListenerContainerFactory.createMessageListenerContainer(queueName);
        Flux<String> f = Flux.create(emitter -> {
            log.info("开始监听, queue={}", queueName);
            container.setupMessageListener((ChannelAwareMessageListener) (message, channle) -> {
                if (emitter.isCancelled()) {
                    log.info("停止消费, queue={}", queueName);
                    container.stop();
                    return;
                }
                String payload = new String(message.getBody());
                emitter.next(payload);
                Thread.sleep(3000);
                log.info("收到来自{}的消息：{}", queueName, payload);
                channle.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            });
            emitter.onRequest(v -> container.start());
            emitter.onDispose(() -> {
                log.info("停止消费: queue={}", queueName);
                container.stop();
            });
        });
        return Flux.interval(Duration.ofSeconds(5))
                .map(v -> "No news is good news")
                .mergeWith(f);
    }

    @GetMapping(value = "listener2", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<String> receiveMessagesFromQueue2(@RequestParam String queueName) {
        MessageListenerContainer container = messageListenerContainerFactory.createMessageListenerContainer(queueName);
        Flux<String> f = Flux.create(emitter -> {
            log.info("开始监听2, queue={}", queueName);
            container.setupMessageListener((ChannelAwareMessageListener) (message, channle) -> {
                String payload = new String(message.getBody());
                emitter.next(payload);
                Thread.sleep(3000);
                log.info("收到来自{}的消息2：{}", queueName, payload);
                channle.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            });
            emitter.onRequest(v -> container.start());
            emitter.onDispose(() -> {
                log.info("停止消费2: queue={}", queueName);
                container.stop();
            });
        });
        return Flux.interval(Duration.ofSeconds(5))
                .map(v -> "No news is good news")
                .mergeWith(f);
    }

}
