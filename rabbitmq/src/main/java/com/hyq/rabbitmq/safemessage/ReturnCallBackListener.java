package com.hyq.rabbitmq.safemessage;

import com.google.gson.Gson;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

/**
 * @author dibulidohu
 * @classname ReturnCallBackListener
 * @date 2019/4/2310:11
 * @description
 */
@Slf4j
@Service("returnCallBackListener")
public class ReturnCallBackListener implements RabbitTemplate.ReturnCallback {

    private Gson gson = new Gson();

    /***
     * 当消息从交换机到队列失败时，该方法被调用。（若成功，则不调用）
     * 需要注意的是：该方法调用后，MsgSendConfirmCallBack中的confirm方法也会被调用，且ack = true
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("message is return :{}, cause:{}", gson.toJson(message), replyText);
        log.info("message:{};replyCode:{},replyText:{},exchange:{},routingKey:{}", message, replyCode, replyText, exchange, routingKey);
    }
}
