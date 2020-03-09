# 公平转换竞争：
> 取消注释

 > 改为手动确认：
`// 监听队列，false表示手动返回完成状态，true表示自动`
`channel.basicConsume(QUEUE_NAME, false, deliverCallback,consumerTag -> {});`
>
`// 同一时刻服务器只会发一条消息给消费者`
`channel.basicQos(1); `

`//开启这行 表示使用手动确认`
`channel.basicAck(delivery.getEnvelope().getDeliveryTag(), false);`

- 模式1：自动确认
> 只要消息从队列中获取，无论消费者获取到消息后是否成功消息，都认为是消息已经成功消费。
- 模式2：手动确认
> 消费者从队列中获取消息后，服务器会将该消息标记为不可用状态，等待消费者的反馈，如果消费者一直没有反馈，那么该消息将一直处于不可用状态。

- 手动模式

![手动模式](https://img-blog.csdn.net/2018080522513442?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pwY2FuZHpoag==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)

- 自动模式

![自动模式](https://img-blog.csdn.net/20180805225140850?watermark/2/text/aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L3pwY2FuZHpoag==/font/5a6L5L2T/fontsize/400/fill/I0JBQkFCMA==/dissolve/70)
