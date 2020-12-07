package cn.jason.rabbitmq.springboot.pattern.reactive;

import lombok.Data;
import lombok.Getter;

import java.io.Serializable;

/**
 * @author jinkai
 * @description
 * @since 2020/12/7
 */
@Data
public class SendMsgModel implements Serializable {
    private String msg;
    private String msg2;
}
