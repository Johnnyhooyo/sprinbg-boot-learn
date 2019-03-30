package com.hyq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @author dibulidohu
 * @classname MyEventOnlyListener
 * @date 2019/3/3014:51
 * @description
 */
@Slf4j
@Component
public class MyEventOnlyListener implements ApplicationListener<MyEvent> {
    @Override
    public void onApplicationEvent(MyEvent myEvent) {
        Object eventSource = myEvent.getSource();
        log.info("in this method, only MyEvent can be caught; source is :{}", eventSource.toString());
    }
}
