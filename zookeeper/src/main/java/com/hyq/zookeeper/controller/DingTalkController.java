package com.hyq.zookeeper.controller;

import com.google.common.collect.Lists;
import com.hyq.zookeeper.core.dingding.DingDing;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dibulidohu
 * @classname DingTalkController
 * @date 2019/5/21 13:16
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/ding")
public class DingTalkController {


    @RequestMapping(value = "/test", method = {RequestMethod.POST})
    public void send(@RequestBody String message) {
        DingDing dingDing = new DingDing();
        DingDing.Message message1 = dingDing.new Message();

        DingDing.At at = dingDing.new At();
        at.setAtMobiles(Lists.newArrayList());
        at.setIsAtAll(false);

        DingDing.TextBody textBody = dingDing.new TextBody();
        textBody.setContent(message);

        message1.setMsgtype("text");
        message1.setText(textBody);
        message1.setAt(at);
        dingDing.send(message1);
        DingDing.ActionCard actionCard = dingDing.new ActionCard();
        actionCard.setSingleURL("www.baidu.com");
        actionCard.setBtnOrientation("0");
        actionCard.setTitle("new message");
        actionCard.setText("hyq is awesome !!!!!!!!!!!");
        actionCard.setSingleTitle("read more");

        DingDing.Message message2 = dingDing.new Message();
        message2.setMsgtype("actionCard");
        message2.setActionCard(actionCard);
        message2.setAt(at);
        boolean send = dingDing.send(message2);
        if (send) {
            log.info("send success");
        }
    }
}
