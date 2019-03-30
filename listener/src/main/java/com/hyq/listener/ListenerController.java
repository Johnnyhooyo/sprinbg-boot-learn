package com.hyq.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author dibulidohu
 * @classname ListenerController
 * @date 2019/3/3014:42
 * @description
 */
@Slf4j
@RestController
@RequestMapping("/listener")
public class ListenerController {

    @Autowired
    ApplicationContext context;

    @ResponseBody
    @GetMapping
    public void listener() {
        MyEvent myEvent = new MyEvent("this is my test", 111, "my event");
        context.publishEvent(myEvent);
        OtherEvent otherEvent = new OtherEvent("this is other test", 222, "other event");
        context.publishEvent(otherEvent);
    }
}
