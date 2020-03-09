package cn.jason.rabbitmq.springboot.pattern.ack;

import cn.jason.rabbitmq.springboot.pattern.simple.SimpleSend;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AckSendTest {
    @Autowired
    private AckSend ackSend;

    @Test
    public void send() throws Exception {
        ackSend.send();
        Thread.sleep(30*1000);
    }

}