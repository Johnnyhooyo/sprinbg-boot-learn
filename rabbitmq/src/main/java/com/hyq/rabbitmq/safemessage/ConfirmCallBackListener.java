package com.hyq.rabbitmq.safemessage;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author dibulidohu
 * @classname ConfirmCallBackListener
 * @date 2019/4/2310:09
 * @description
 */
@Slf4j
@Service("confirmCallBackListener")
public class ConfirmCallBackListener implements RabbitTemplate.ConfirmCallback {

    private Gson gson = new Gson();

    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        if (ack) {
            log.info("message is confirm :{}", gson.toJson(correlationData));
        } else {
            log.info("correlationData:{}; is not ack cause:{}", correlationData, cause);
        }
    }
}
