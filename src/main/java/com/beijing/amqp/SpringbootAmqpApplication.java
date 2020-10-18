package com.beijing.amqp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringbootAmqpApplication {

    // 自动配置类  RabbitAutoConfiguration
    // 1.rabbitConnectionFactory连接工厂及RabbitProperties配置
    // 2.RabbitTemplate： 对rabbitmq发送和接收消息
    // 3.amqpAdmin： rabbitmq系统管理功能组件（
    public static void main(String[] args) {
        SpringApplication.run(SpringbootAmqpApplication.class, args);
    }

}
