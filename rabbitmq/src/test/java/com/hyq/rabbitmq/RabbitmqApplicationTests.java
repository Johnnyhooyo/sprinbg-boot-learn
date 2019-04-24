package com.hyq.rabbitmq;

import com.hyq.rabbitmq.simple.Producer10;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RabbitmqApplicationTests {

    @Test
    public void contextLoads() {
    }

    @Autowired
    Producer10 producer;

    @Test
    public void simple() {
        producer.sendMessage();
    }

}
