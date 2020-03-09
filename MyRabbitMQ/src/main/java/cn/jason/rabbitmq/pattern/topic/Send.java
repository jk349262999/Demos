package cn.jason.rabbitmq.pattern.topic;

import cn.jason.rabbitmq.pattern.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @className: Send
 * @author: Jason
 * @date: 2020/3/4 00:27
 * @description: TODO
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        // 消息内容
        String message = "routekey.1.xxx Hello World!!";
        channel.basicPublish(EXCHANGE_NAME, "routekey.1.xxx", null, message.getBytes());
        System.out.println(" 《topic Send 》 Sent '" + message + "'");

        message = "routekey.2.yyy Hello World!!";
        channel.basicPublish(EXCHANGE_NAME, "routekey.2.yyy", null, message.getBytes());
        System.out.println(" 《topic Send 》 Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
