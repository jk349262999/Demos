package cn.jason.rabbitmq.pattern.router;

import cn.jason.rabbitmq.pattern.ConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

/**
 * @ClassName: Send
 * @Author: Jason
 * @Date: 2020/3/3 23:54
 * @Description: TODO 消息的生产者（看作是后台系统） 向交换机中发送消息。
 */
public class Send {
    private final static String EXCHANGE_NAME = "test_exchange_direct";

    public static void main(String[] argv) throws Exception {
        // 获取到连接以及mq通道
        Connection connection = ConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");

        // 消息内容
        String message = "查看。。。。。";
        channel.basicPublish(EXCHANGE_NAME, "select", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        message = "删除。。。。。";
        channel.basicPublish(EXCHANGE_NAME, "update", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");

        channel.close();
        connection.close();
    }
}
