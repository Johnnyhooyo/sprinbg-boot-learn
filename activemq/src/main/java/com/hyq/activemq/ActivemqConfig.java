package com.hyq.activemq;

import org.apache.activemq.command.ActiveMQQueue;
import org.apache.activemq.command.ActiveMQTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;

import javax.jms.ConnectionFactory;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

/**
 * @author dibulidohu
 * @classname ActivemqConfig
 * @date 2019/4/1016:50
 * @description
 */
@Configuration
public class ActivemqConfig {

    @Bean
    public Topic footballTopic() {
        return new ActiveMQTopic("footballTopic");
    }

    @Bean
    public Topic basketballTopic() {
        return new ActiveMQTopic("basketballTopic");
    }

    @Bean
    public Queue footballQueue() {
        return new ActiveMQQueue("footballQueue");
    }

    @Bean
    public Queue basketballQueue() {
        return new ActiveMQQueue("basketballQueue");
    }

    @Bean
    public Queue receiveQueue() {
        return new ActiveMQQueue("receiveQueue");
    }

    @Bean
    public Queue ackQueue() {
        return new ActiveMQQueue("ackQueue");
    }

    @Bean
    public JmsListenerContainerFactory<?> topicListener(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(true);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> queueListener(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    /***
     *  AUTO_ACKNOWLEDGE = 1    自动确认         CLIENT_ACKNOWLEDGE = 2    客户端手动确认            DUPS_OK_ACKNOWLEDGE = 3    自动批量确认
     * SESSION_TRANSACTED = 0    事务提交并确认
     */
    @Bean
    public JmsListenerContainerFactory<?> ackQueueListener(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setSessionTransacted(false);
        factory.setSessionAcknowledgeMode(4);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }

    @Bean
    public JmsListenerContainerFactory<?> ackQueueListenerWithTransaction(ConnectionFactory connectionFactory) {
        DefaultJmsListenerContainerFactory factory = new DefaultJmsListenerContainerFactory();
        factory.setPubSubDomain(false);
        factory.setSessionTransacted(true);
        factory.setSessionAcknowledgeMode(Session.CLIENT_ACKNOWLEDGE);
        factory.setConnectionFactory(connectionFactory);
        return factory;
    }
}
