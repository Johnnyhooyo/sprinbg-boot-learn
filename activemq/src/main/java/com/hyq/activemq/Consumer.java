package com.hyq.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Component;

import javax.jms.Message;

/**
 * @author dibulidohu
 * @classname Consumer
 * @date 2019/4/1016:52
 * @description
 */
@Slf4j
@Component
public class Consumer {

    @JmsListener(destination = "footballQueue", containerFactory = "queueListener")
    public void footballQueueConsumer(String message) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("footballQueueConsumer1:" + message);
    }

    @JmsListener(destination = "basketballQueue", containerFactory = "queueListener")
    @SendTo("receipt.basketballQueue")
    public String basketballQueueConsumer(String message) {
        log.info("basketballQueueConsumer1:" + message);
        return "basketballQueueConsumer1.receipt";
    }


    @JmsListener(destination = "footballTopic", containerFactory = "topicListener")
    public void footballTopicConsumer(String message) {
        log.info("footballTopicConsumer1:" + message);
    }

    @JmsListener(destination = "basketballTopic", containerFactory = "topicListener")
    public void basketballTopicConsumer(String message) {
        log.info("basketballTopicConsumer1:" + message);
    }


    @JmsListener(destination = "ackQueue", containerFactory = "ackQueueListener")
    public void ackQueueConsumer(Message message) {
        try {
            log.info("ackQueueConsumer1:" + message.getStringProperty("value"));
            Thread.sleep(10000);
            message.acknowledge();
            log.info("already ack");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
