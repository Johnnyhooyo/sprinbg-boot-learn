package com.hyq.rabbitmq.safemessage;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dibulidohu
 * @classname SafeConfig
 * @date 2019/4/239:48
 * @description
 */
@Configuration
public class SafeConfig {

    public static final String HOST = "148.70.215.26";
    public static final int PORT = 5672;
    public static final String USERNAME = "guest";
    public static final String PASSWORD = "guest";
    public static final String EXCHANGE_NAME = "safe_directExchange";
    public static final String TOPIC_EXCHANGE_NAME = "safe_directTopicExchange";
    public static final String FANOUT_EXCHANGE_NAME = "safe_directFanoutExchange";
    public static final String QUEUE_NAME = "safeDirectQueue";
    public static final String QUEUE_NAME1 = "safeTopicQueue1";
    public static final String QUEUE_NAME2 = "safeTopicQueue2";
    public static final String BINDING_KEY = "safe_binding";
    public static final String BINDING_KEY1 = "safe_binding.one";
    public static final String BINDING_KEY2 = "safe_binding.two";

    @Bean
    public CachingConnectionFactory cachingConnectionFactory1() {
        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
        cachingConnectionFactory.setHost(HOST);
        cachingConnectionFactory.setPort(PORT);
        cachingConnectionFactory.setUsername(USERNAME);
        cachingConnectionFactory.setPassword(PASSWORD);
        cachingConnectionFactory.setPublisherConfirms(true);
        cachingConnectionFactory.setPublisherReturns(true);
        return cachingConnectionFactory;
    }

    @Bean
    public RabbitTemplate rabbitTemplate1(@Qualifier("cachingConnectionFactory1") CachingConnectionFactory cachingConnectionFactory, ConfirmCallBackListener confirmCallBackListener, ReturnCallBackListener returnCallBackListener) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate();
        rabbitTemplate.setConnectionFactory(cachingConnectionFactory);
        rabbitTemplate.setMandatory(true);
        rabbitTemplate.setConfirmCallback(confirmCallBackListener);
        rabbitTemplate.setReturnCallback(returnCallBackListener);
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public SimpleRabbitListenerContainerFactory rabbitListenerContainerFactory(@Qualifier("cachingConnectionFactory1") CachingConnectionFactory connectionFactory){
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setMessageConverter(new Jackson2JsonMessageConverter());
        factory.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        return factory;
    }

    @Bean
    public DirectExchange safeExchange() {
        return new DirectExchange(EXCHANGE_NAME,true,false);
    }

    @Bean
    public Queue safeDirectQueue() {
        return new Queue(QUEUE_NAME, true, false, false);
    }

    @Bean
    public TopicExchange safeTopicExchange() {
        return new TopicExchange(TOPIC_EXCHANGE_NAME,true,false);
    }

    @Bean
    public FanoutExchange safeFanoutExchange() {
        return new FanoutExchange(FANOUT_EXCHANGE_NAME,true,false);
    }

    @Bean
    public Queue safeTopicQueue1() {
        return new Queue(QUEUE_NAME1, true, false, false);
    }

    @Bean
    public Queue safeTopicQueue2() {
        return new Queue(QUEUE_NAME2, true, false, false);
    }

    @Bean
    public Binding safeBinding() {
        return BindingBuilder.bind(safeDirectQueue()).to(safeExchange()).with(BINDING_KEY);
    }

    @Bean
    public Binding safeBinding1() {
        return BindingBuilder.bind(safeTopicQueue1()).to(safeTopicExchange()).with(BINDING_KEY1);
    }

    @Bean
    public Binding safeBinding2() {
        return BindingBuilder.bind(safeTopicQueue2()).to(safeTopicExchange()).with(BINDING_KEY2);
    }

    @Bean
    public Binding safeBinding3() {
        return BindingBuilder.bind(safeDirectQueue()).to(safeFanoutExchange());
    }

    @Bean
    public Binding safeBinding4() {
        return BindingBuilder.bind(safeTopicQueue1()).to(safeFanoutExchange());
    }

    @Bean
    public Binding safeBinding5() {
        return BindingBuilder.bind(safeTopicQueue2()).to(safeFanoutExchange());
    }
}
