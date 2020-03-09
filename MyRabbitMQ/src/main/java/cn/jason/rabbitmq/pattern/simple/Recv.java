package cn.jason.rabbitmq.pattern.simple;

import cn.jason.rabbitmq.pattern.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @className: Recv
 * @author: Jason
 * @date: 2020/3/3 22:51
 * @description: TODO   消费者从队列中获取消息
 */
public class Recv {
    private final static String QUEUE_NAME = "q_test_01";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 定义队列的消费者
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" 《simple 》 Received '" + message + "'");
        };
        // 监听队列
        channel.basicConsume(QUEUE_NAME, true, deliverCallback,consumerTag -> { });
    }
}
