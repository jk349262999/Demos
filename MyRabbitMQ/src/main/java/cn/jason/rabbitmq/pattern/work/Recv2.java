package cn.jason.rabbitmq.pattern.work;

import cn.jason.rabbitmq.pattern.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.DeliverCallback;

import java.nio.charset.StandardCharsets;

/**
 * @className: Recv2
 * @author: Jason
 * @date: 2020/3/3 23:18
 * @description: TODO 消费者2
 */
public class Recv2 {
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {

        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 指该消费者在接收到队列里的消息但没有返回确认结果之前,它不会将新的消息分发给它
        channel.basicQos(1);

        // 定义队列的消费者
        DeliverCallback deliverCallback = (consumerTag, delivery) -> {
            String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
            System.out.println(" 《Work Recv2 》 Received '" + message + "'");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);
            }
        };
        // 监听队列，手动返回完成
        channel.basicConsume(QUEUE_NAME, false, deliverCallback,consumerTag -> {});
    }
}
