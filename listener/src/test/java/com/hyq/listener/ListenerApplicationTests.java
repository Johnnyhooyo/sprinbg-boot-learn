package com.hyq.listener;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ListenerApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    ApplicationContext context;

    @Test
    public void eventTest() {
        MyEvent myEvent = new MyEvent("this is my test", 111, "my event");
        context.publishEvent(myEvent);
        OtherEvent otherEvent = new OtherEvent("this is other test", 222, "other event");
        context.publishEvent(otherEvent);
    }

}
