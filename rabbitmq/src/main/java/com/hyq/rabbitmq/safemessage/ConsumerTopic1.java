package com.hyq.rabbitmq.safemessage;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author dibulidohu
 * @classname Consumer
 * @date 2019/4/2310:30
 * @description
 */
@Slf4j
@Service
@RabbitListener(queues = SafeConfig.QUEUE_NAME1, containerFactory = "rabbitListenerContainerFactory")
public class ConsumerTopic1 {

    @RabbitHandler
    public void doHandle(@Payload Beauty content, Message message, Channel channel) {
        try {
            log.info("1111111111111content {},message {},channel {}", content, message, channel);
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("----------------id:{}", message.getMessageProperties().getCorrelationId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
