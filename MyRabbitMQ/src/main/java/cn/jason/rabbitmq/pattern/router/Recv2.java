package cn.jason.rabbitmq.pattern.router;

import cn.jason.rabbitmq.pattern.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @className: Recv2
 * @author: Jason
 * @date: 2020/3/3 23:58
 * @description: TODO   消费者2
 */
public class Recv2 {
    private final static String QUEUE_NAME = "test_queue_direct_2";

    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        // 绑定队列到交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "select");
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "update");

        // 同一时刻服务器只会发一条消息给消费者
        channel.basicQos(1);

        // 定义队列的消费者
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" 《router Recv2 》 Received '" + message + "'");
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, deliverCallback, consumerTag -> {
        });
    }
}
