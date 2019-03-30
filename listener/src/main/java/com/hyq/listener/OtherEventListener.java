package com.hyq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author dibulidohu
 * @classname OtherEventListener
 * @date 2019/3/3014:21
 * @description
 */
@Slf4j
@Component
public class OtherEventListener implements ApplicationListener {
    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof OtherEvent) {
            OtherEvent otherEvent = (OtherEvent) applicationEvent;
            log.info("***********otherEvent:{}", otherEvent.toString());
        } else {
            log.info("**********event:{}", applicationEvent.toString());
        }
    }
}
