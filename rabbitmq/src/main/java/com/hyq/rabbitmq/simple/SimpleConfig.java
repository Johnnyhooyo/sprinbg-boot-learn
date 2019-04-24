package com.hyq.rabbitmq.simple;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;


/**
 * @author dibulidohu
 * @classname SimpleConfig
 * @date 2019/4/1720:17
 * @description
 */
@Component
@Configuration
public class SimpleConfig {

    @Bean
    public Queue queue() {
        return new Queue("simpleQueue");
    }

    @Bean
    public Queue queue1() {
        return new Queue("testQueue");
    }
}
