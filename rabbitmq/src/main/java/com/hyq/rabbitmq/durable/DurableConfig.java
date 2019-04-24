package com.hyq.rabbitmq.durable;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.Connection;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dibulidohu
 * @classname durableConfig
 * @date 2019/4/1915:02
 * @description
 */
@Configuration
public class DurableConfig {

    public static final String EXCHANGE_NAME = "my_directExchange";

    public static final String QUEUE_NAME = "myDirectQueue";

    public static final String BINDING_KEY = "my_binding";

    /***
     * 1.交换机名字
     * 2.是否持久化
     * 3.是否自动删除，在空闲时
     */
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(EXCHANGE_NAME,true,false);
    }

    /***
     * 1.queue名字
     * 2.是否持久化
     * 3.标识该消息队列是否只在当前connection生效,默认是false  是否独有
     * 4.是否自动删除，在空闲时
     */
    @Bean
    public Queue myDirectQueue() {
        // 配置 自动删除   如果使用凡在queue的最后一个入参
        Map<String, Object> arguments = new HashMap<>();
        //60秒自动删除
        arguments.put("x-message-ttl", 60000);
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    public Binding myBinding() {
        return BindingBuilder.bind(myDirectQueue()).to(directExchange()).with(BINDING_KEY);
    }
}
