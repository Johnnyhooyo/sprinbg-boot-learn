package com.hyq.rabbitmq.safemessage;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

/**
 * @author dibulidohu
 * @classname Consumer1
 * @date 2019/4/2316:20
 * @description
 */
@Slf4j
@Service
@RabbitListener(queues = SafeConfig.QUEUE_NAME, containerFactory = "rabbitListenerContainerFactory")
public class Consumer1 {

    @RabbitHandler
    public void doHandle(@Payload Beauty content, Message message, Channel channel) {
        log.info("content {},message {},channel {}", content, message, channel);
        log.info("----------------id:{}", message.getMessageProperties().getCorrelationId());
    }
}
