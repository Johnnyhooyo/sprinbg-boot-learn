package com.hyq.rabbitmq.durable;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * @author dibulidohu
 * @classname DurableConsumer
 * @date 2019/4/1915:49
 * @description
 */
@Slf4j
@Service
@RabbitListener(queues = DurableConfig.QUEUE_NAME)
public class DurableConsumer {

    @RabbitHandler
    public void doHandle(String content, Message message, Channel channel, CorrelationData correlationData) {
        try {
            log.info("content {}", content);
            log.info("message {}", message);
            log.info("channel {}", channel);
            log.info("correlationData {}", correlationData.getId());
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            log.info("*************id:{}", message.getMessageProperties().getCorrelationId());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
