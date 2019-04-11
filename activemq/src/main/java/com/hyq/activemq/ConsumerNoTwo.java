package com.hyq.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.Session;

/**
 * @author dibulidohu
 * @classname Consumer
 * @date 2019/4/1016:52
 * @description
 */
@Slf4j
@Component
public class ConsumerNoTwo {

    @JmsListener(destination = "footballQueue", containerFactory = "queueListener")
    public void footballQueueConsumer(String message) {
        log.info("footballQueueConsumer2:" + message);
    }

    @JmsListener(destination = "basketballQueue", containerFactory = "queueListener")
    public void basketballQueueConsumer(String message, Session session) {
        log.info("basketballQueueConsumer2:" + message);
        try {
            session.rollback();

        } catch (JMSException e) {
            e.printStackTrace();
        }
    }


    @JmsListener(destination = "footballTopic", containerFactory = "topicListener")
    public void footballTopicConsumer(String message) {
        log.info("footballTopicConsumer2:" + message);
    }

    @JmsListener(destination = "basketballTopic", containerFactory = "topicListener")
    public void basketballTopicConsumer(String message) {
        log.info("basketballTopicConsumer2:" + message);
    }

    @JmsListener(destination = "ackQueue", containerFactory = "ackQueueListener")
    public void ackQueueConsumer(Message message) {
        try {
            log.info("ackQueueConsumer2:" + message.getStringProperty("value"));
            Thread.sleep(10000);
            //message.acknowledge();
            log.info("i'm not ack");
        } catch (Exception e) {
            e.printStackTrace();
        }
        throw new RuntimeException();
    }

   @JmsListener(destination = "ackQueue", containerFactory = "ackQueueListenerWithTransaction")
   public void ackQueueDLQConsumer(Message message, Session session) {
       try {
           log.info("ackQueueConsumer2.dlq:" + message.getStringProperty("value"));
           Thread.sleep(10000);
           log.info("i'm not ack yet");
           session.rollback();
       } catch (Exception e) {
           log.error(e.getMessage());
       }
   }
}
