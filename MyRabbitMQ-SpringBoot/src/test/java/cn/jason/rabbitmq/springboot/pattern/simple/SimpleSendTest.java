package cn.jason.rabbitmq.springboot.pattern.simple;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SimpleSendTest {

    @Autowired
    private SimpleSend simpleSend;

    @Test
    public void hello() throws Exception {
        simpleSend.sendHello();
        Thread.sleep(30 * 1000);
    }
}