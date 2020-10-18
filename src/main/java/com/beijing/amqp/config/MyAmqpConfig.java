package com.beijing.amqp.config;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 消息发送序列化json
 * @Author zc217
 * @Date 2020/10/18
 */
@Configuration
public class MyAmqpConfig {

    @Bean
    public MessageConverter myMessageConverter(){
        return new Jackson2JsonMessageConverter();
    }
}
