package com.beijing.amqp;

import com.beijing.amqp.bean.Book;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeoutException;

@SpringBootTest
class SpringbootAmqpApplicationTests {

    String SEND = "exchange.direct";
    String SEND_MULTI = "exchange.fanout";

    // MessageConverter--> createMessage序列化  -- > fromMessage反序列化
    @Autowired
    RabbitTemplate rabbitTemplate;


    /**
     * 发送消息API
     */
    @Test
    public void testSendApI() {
        //点对点 direct 自定义org.springframework.amqp.core.Message的消息头和消息体
//        rabbitTemplate.send(exchage,routingkey,message);
        // 传入发送对象，自动序列化发送
//        rabbitTemplate.convertAndSend(exchage,routingkey,object);
        Map<String, Object> map = new HashMap<>();
        map.put("msg", "one message");
        map.put("data", Arrays.asList("helloworld", "123", "true"));
        // 往交换器exchange.direct的路由key =atguigu.news的点对点发送数据map
        // content_type:application/x-java-serialized-object  对象被默认序列化以后发送出去
//        rabbitTemplate.convertAndSend("exchange.direct", "atguigu.news", map);
        //自定义对象
        Book book = new Book("西游记", "zlk");
        rabbitTemplate.convertAndSend(SEND, "atguigu.news", book);

    }

    /**
     * 接收消息API  执行完后消息在queue中消费掉没有了
     */
    @Test
    public void testReceviceApi() {
//        Object object = rabbitTemplate.receiveAndConvert("atguigu.news");
//        if (object != null) {
//            System.out.println(object.getClass());
//            System.out.println(object);
//        }
    }

    /**
     * 广播
     */
    @Test
    public void sendMessage() {
        rabbitTemplate.convertAndSend(SEND_MULTI, "atguigu.news", new Book("红楼梦", "曹雪芹"));
    }


    /**
     * 另一种方式，发送单播  我这是使用默认的guest用户
     * https://www.cnblogs.com/rollenholt/p/4098089.html
     */
    @Test
    public void testCustomerSend() throws IOException, TimeoutException {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("192.168.0.106");
        factory.setUsername("guest");
        factory.setPassword("guest");
        factory.setPort(5672);
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare("atguigu", true, false, false, null);
        String message = "Hello World!";
        channel.basicPublish(SEND, "atguigu", null, message.getBytes());
        System.out.println(" [x] Sent '" + message + "'");
        channel.close();
        connection.close();
    }


    @Autowired
    AmqpAdmin amqpAdmin;

    /**
     * 通过amqpAdmin创建exchange、queue和他们之间的绑定规则
     */
    @Test
    public void testCreateExchange() {
//        Exchange exchange = new DirectExchange("amqpadmin.exchange.direct");
//        amqpAdmin.declareExchange(exchange);
//        System.out.println("amqp.exchange.direct create finish");

//        amqpAdmin.declareQueue(new Queue("amqpadmin.queue1",true));
//        System.out.println("amqpadmin.queue1 FINISH");

        amqpAdmin.declareBinding(new Binding("amqpadmin.queue1"
                , Binding.DestinationType.QUEUE
                , "amqpadmin.exchange.direct"
                , "amqp.routkey"
                , null));

    }
}
