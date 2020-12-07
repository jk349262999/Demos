package cn.jason.rabbitmq.springboot.pattern.reactive;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

/**
 * @author jinkai
 * @description
 * @since 2020/12/7
 */
@Controller
@RequiredArgsConstructor
public class MessageSendController {
    private final AmqpTemplate amqpTemplate;

    @PostMapping(value = "queue/{name}",produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Mono<ResponseEntity<?>> sendMessageToQueue(@PathVariable String name, @RequestBody String payload) {
        return Mono.fromCallable(() -> {
            SendMsgModel sendMsgModel = new SendMsgModel();
            sendMsgModel.setMsg(name);
            sendMsgModel.setMsg2(payload);
            amqpTemplate.convertAndSend(name,sendMsgModel);
            return ResponseEntity.accepted().build();
        });
    }
}
