package com.beijing.amqp.service;

import com.beijing.amqp.bean.Book;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * 监听mq消息队列
 *
 * @Author zc217
 * @Date 2020/10/18
 */
@Service
public class BookService {

    @RabbitListener(queues = {"atguigu.news"})
    public void listenInfo(Book book) {
        System.out.println("收到消息：" + book.getAuthor());
    }

    @RabbitListener(queues = "atguigu")
    public void listenHeadInfo(Message message){
        MessageProperties properties = message.getMessageProperties();
        System.out.println(properties);
    }
}
