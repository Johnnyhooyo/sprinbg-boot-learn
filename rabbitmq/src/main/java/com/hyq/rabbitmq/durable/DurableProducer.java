package com.hyq.rabbitmq.durable;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

/**
 * @author dibulidohu
 * @classname DurableProducer
 * @date 2019/4/1915:36
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/rabbit")
public class DurableProducer {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Autowired
    Exchange directExchange;

    @Autowired
    Queue myDirectQueue;

    @RequestMapping("/durable")
    public void sendMessage() {
        log.info("start durable message");
        String mid = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(mid);
        MessageProperties properties = new MessageProperties();
        properties.setDeliveryMode(MessageDeliveryMode.PERSISTENT);
        properties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        properties.setContentEncoding("UTF-8");
        Message message = new Message("hello rabbit i want durable".getBytes(), properties);
        rabbitTemplate.convertAndSend(DurableConfig.EXCHANGE_NAME, DurableConfig.BINDING_KEY, message, correlationData);
        log.info("message send:{}", correlationData);
    }
}
