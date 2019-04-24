package com.hyq.rabbitmqconsumer.simple;

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
//@Slf4j
//@Component
//@RabbitListener(queues = "simpleQueue")
//public class Consumer {
//
//    @RabbitHandler
//    public void process(String message) {
//        System.out.println("00000000000000000");
//        log.info("simpleReceive：{}", message);
//    }
//}

@Slf4j
@Component
public class Consumer {
    @RabbitListener(queues = "simpleQueue")
    public void process(String message) {
        System.out.println("00000000000000000");
        log.info("simpleReceive：{}", message);
    }
}
