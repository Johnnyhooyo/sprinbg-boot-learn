package com.hyq.rabbitmq.simple;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.stream.IntStream;

/**
 * @author dibulidohu
 * @classname Producer
 * @date 2019/4/1720:16
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/rabbit")
public class Producer10 {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @RequestMapping("/simple")
    public void sendMessage() {
        log.info("start");
        rabbitTemplate.convertAndSend("hello rabbit");
    }

    @RequestMapping("/simple0")
    public void sendMessage0() {
        IntStream.range(1,100).forEach(i -> {
            log.info("simple send------" + i);
            rabbitTemplate.convertAndSend("simpleQueue", "hello simple" + i);
        });
    }

    @RequestMapping("/simple1")
    public void sendMessage1() {
        rabbitTemplate.convertAndSend("simpleQueue", "hello simple");
    }


    @RequestMapping("/simple2")
    public void sendMessage2() {
        rabbitTemplate.convertAndSend("testQueue", "hello testQueue");
    }
}
