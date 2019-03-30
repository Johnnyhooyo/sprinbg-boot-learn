package com.hyq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author dibulidohu
 * @classname MyEventListener
 * @date 2019/3/3014:17
 * @description
 */
@Slf4j
@Component
public class MyEventListener implements ApplicationListener {

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if (applicationEvent instanceof MyEvent) {
            MyEvent myEvent = (MyEvent) applicationEvent;
            log.info("------------myEvent:{}", myEvent.toString());
        } else {
            log.info("------------event:{}", applicationEvent.toString());
        }
    }
}
