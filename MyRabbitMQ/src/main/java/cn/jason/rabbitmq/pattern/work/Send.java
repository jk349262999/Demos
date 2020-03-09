package cn.jason.rabbitmq.pattern.work;

import cn.jason.rabbitmq.pattern.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @className: Send
 * @author: Jason
 * @date: 2020/3/3 23:29
 * @description: TODO 生产者 向队列中发送100条消息
 */
public class Send {
    private final static String QUEUE_NAME = "test_queue_work";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        int total = 100;
        for (int i = 0; i < total; i++) {
            // 消息内容
            String message = "" + i;
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" 《Work Send 》 Sent '" + message + "'");
            Thread.sleep(10);
        }

        channel.close();
        connection.close();
    }
}
