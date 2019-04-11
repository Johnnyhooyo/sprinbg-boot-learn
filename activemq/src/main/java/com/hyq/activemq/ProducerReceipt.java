package com.hyq.activemq;

import lombok.extern.slf4j.Slf4j;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

/**
 * @author dibulidohu
 * @classname ProducerReceipt
 * @date 2019/4/1110:35
 * @description
 */
@Slf4j
@Component
public class ProducerReceipt {

    @JmsListener(destination = "receipt.basketballQueue", containerFactory = "queueListener")
    public void producerReceipt(String message) {
        log.info("receipt.basketballQueue" + message);
    }
}
