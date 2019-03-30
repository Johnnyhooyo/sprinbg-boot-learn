package com.hyq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.ServletRequestHandledEvent;

/**
 * @author dibulidohu
 * @classname ServletRequestHandleListener
 * @date 2019/3/3014:46
 * @description  listening ServletRequestHandledEvent, you can do something with below params: url=[/listener]; client=[127.0.0.1]; method=[GET]; servlet=[dispatcherServlet]; session=[null]; user=[null]; time=[1028ms]; status=[OK]
 */
@Slf4j
@Component
public class ServletRequestHandleListener implements ApplicationListener<ServletRequestHandledEvent> {

    @Override
    public void onApplicationEvent(ServletRequestHandledEvent servletRequestHandledEvent) {
      log.info("ServletRequestHandledEvent:{}" ,servletRequestHandledEvent);
    }
}
