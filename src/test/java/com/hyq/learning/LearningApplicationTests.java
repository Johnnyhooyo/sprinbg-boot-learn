package com.hyq.learning;

import com.hyq.learning.sigton.Sigton1;
import com.hyq.learning.sigton.Sigton2;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.concurrent.Executor;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LearningApplicationTests {

    @Resource
    Executor asyncServerExecutor;

    @Test
    public void sigtonTest() {
        System.out.println("start");
        asyncServerExecutor.execute(() -> {
            Sigton1 instance = Sigton1.getInstance();
            System.out.println(instance);
        });
        Sigton2 instance = Sigton2.getInstance();
        System.out.println(instance);
    }

}
