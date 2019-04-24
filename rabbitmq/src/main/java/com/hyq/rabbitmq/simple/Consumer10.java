package com.hyq.rabbitmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author dibulidohu
 * @classname Consumer
 * @date 2019/4/1720:20
 * @description
 */
@Slf4j
@Component
@RabbitListener(queues = "simpleQueue")
public class Consumer10 {

    @RabbitHandler
    public void process(String message) {
        log.info("simpleReceive：{}", message);
    }
}
