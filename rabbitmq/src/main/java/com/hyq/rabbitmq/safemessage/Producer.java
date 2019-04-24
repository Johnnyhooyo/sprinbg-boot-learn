package com.hyq.rabbitmq.safemessage;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dibulidohu
 * @classname Producer
 * @date 2019/4/2310:01
 * @description
 */
@Slf4j
@RestController
public class Producer {
    @Autowired
    @Qualifier("rabbitTemplate1")
    RabbitTemplate rabbitTemplate;

    private Gson gson = new Gson();

    @PostMapping("safe")
    public void sendMessage(String exNAME, String routingKey) {
        Beauty beauty = new Beauty();
        beauty.setAge(18);
        beauty.setWeight(53.2);
        beauty.setName("meili");
        beauty.setIntroduce("beauty");
        rabbitTemplate.convertAndSend(exNAME, routingKey, beauty);
        log.info("there has a beauty : {}", gson.toJson(beauty));
    }
}
