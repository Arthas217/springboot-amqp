package com.beijing.amqp;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
  // 基于注解的rabbitmq模式
@SpringBootApplication
public class SpringbootAmqpApplication {

    // 自动配置类  RabbitAutoConfiguration
    // 1.rabbitConnectionFactory连接工厂及RabbitProperties配置
    // 2.RabbitTemplate： 对rabbitmq发送和接收消息
    // 3.amqpAdmin： rabbitmq系统管理功能组件
    //帮我们创建、删除 exchange  queue bind
    // @EnableRabbit + @RabbitListener  监听消息队列里的内容
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqpApplication.class, args);
    }

}
