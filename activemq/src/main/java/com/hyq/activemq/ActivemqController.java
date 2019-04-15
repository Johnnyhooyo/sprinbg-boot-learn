package com.hyq.activemq;

import lombok.extern.slf4j.Slf4j;
import org.apache.activemq.command.ActiveMQMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.jms.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.stream.IntStream;

/**
 * @author dibulidohu
 * @classname ActiveMQController
 * @date 2019/4/1016:47
 * @description
 */
@Slf4j
@RestController
public class ActivemqController {

    @Autowired
    private JmsTemplate jmsTemplate;

    @Resource
    private Queue footballQueue;

    @Resource
    private Queue basketballQueue;

    @Resource
    private Topic footballTopic;

    @Resource
    private Topic basketballTopic;

    @Resource
    private Queue receiveQueue;

    @Resource
    private Queue ackQueue;

    @ResponseBody
    @RequestMapping("/mq/queue/football")
    public void footballQueueProducer(HttpServletRequest request, HttpServletResponse response) {
        log.info("footballQueue.send");
        IntStream.range(1, 101).forEach(i -> jmsTemplate.convertAndSend(footballQueue, "i love football.queue" + i));
    }

    @ResponseBody
    @RequestMapping("/mq/queue/basketball")
    public void basketballQueueProducer(HttpServletRequest request, HttpServletResponse response) {
        log.info("basketballQueue.send");
        jmsTemplate.convertAndSend(basketballQueue, "i love basketball.queue");
    }

    @ResponseBody
    @RequestMapping("/mq/topic/football")
    public void footballTopicProducer(HttpServletRequest request, HttpServletResponse response) {
        log.info("footballTopic.send");
        jmsTemplate.convertAndSend(footballTopic, "i love football.topic");
    }

    @ResponseBody
    @RequestMapping("/mq/topic/basketball")
    public void basketballTopicProducer(HttpServletRequest request, HttpServletResponse response) {
        log.info("basketballTopic.send");
        jmsTemplate.convertAndSend(basketballTopic, "i love basketball.topic");
    }

    @ResponseBody
    @RequestMapping("/mq/queue/ack")
    public void queueAck(HttpServletRequest request, HttpServletResponse response) {
        log.info("queueAck.send");
        Message message = new ActiveMQMessage();
        try {
            message.setStringProperty("value", "value_content");
        } catch (JMSException e) {
            e.printStackTrace();
        }
        jmsTemplate.convertAndSend(ackQueue, message);
    }

    @ResponseBody
    @RequestMapping("/mq/topic/sendAndReceive")
    public void sendAndReceive(HttpServletRequest request, HttpServletResponse response) throws JMSException {
        log.info("sendAndReceive.send");
        Message message = jmsTemplate.sendAndReceive(receiveQueue, new MessageCreator() {
            @Override
            public Message createMessage(Session session) throws JMSException {
                //return session.createTextMessage("sendAndReceive");
                ActiveMQMessage activeMQMessage = new ActiveMQMessage();
                activeMQMessage.setStringProperty("receive", "answer me!");
                return activeMQMessage;
            }
        });
        if (null != message) {
            log.info("message:{}", message.getStringProperty("back"));
        }
    }
}
