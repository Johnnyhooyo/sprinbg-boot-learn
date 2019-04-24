package com.hyq.rabbitmq;

import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * @author dibulidohu
 * @classname RabbitMQConfig
 * @date 2019/4/1711:24
 * @description
 */
@Configuration
public class RabbitMQConfig {

//    /***
//     * 连接工厂 --link below template
//     */
//    @Bean
//    public ConnectionFactory connectionFactory() {
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setHost("your host addr");
//        cachingConnectionFactory.setPort(999);
//        cachingConnectionFactory.setUsername("your name");
//        cachingConnectionFactory.setPassword("pass");
//        return cachingConnectionFactory;
//    }
//
//    /***
//     * Template  --diy template
//     */
//    @Bean
//    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
//        RabbitTemplate rabbitTemplate = new RabbitTemplate();
//        rabbitTemplate.setConnectionFactory(connectionFactory);
//        return rabbitTemplate;
//    }
}
