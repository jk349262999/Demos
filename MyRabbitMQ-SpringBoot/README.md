### 配置

- 队列queue:可以使用以下方式来声明
    -
    自定义bean: [SimpleRabbitConfig.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/simple/SimpleRabbitConfig.java)
    - 使用注解`queuesToDeclare`: [WorkRecv.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/work/WorkRecv.java)
- 交换器exchange:
    - 自定义bean: [AckRabbitConfig.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/ack/AckRabbitConfig.java)
- 工厂factory: 可以使用自定义bean的方式来注册factory
    - 自定义bean: [AckRabbitConfig.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/ack/AckRabbitConfig.java)

---

### 信息发送

使用RabbitTemplate.convertAndSend发送消息

- // 发送消息到默认的交换器，默认的路由键[SimpleSend.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/simple/SimpleSend.java)
  `rabbitTemplate.convertAndSend("simple_hello", context);`
- // 发送消息到指定的交换器，指定的路由键，消息内容[TopicSend.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/topic/TopicSend.java)
  `rabbitTemplate.convertAndSend("exchange", "top.xx", "Sender : hi, i am message 2[top.xx]");`

---

### 信息接收

使用RabbitTemplate.convertAndSend发送消息

- 简单双注解:
  使用@RabbitListener配合@RabbitHandler[SimpleSend.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/simple/SimpleSend.java)
- 简单单注解：单独使用RabbitListener[WorkRecv.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/work/WorkRecv.java)

---

### 手动应答及自定义工厂绑定

[AckRecv.java](src/main/java/cn/jason/rabbitmq/springboot/pattern/ack/AckRecv.java)

