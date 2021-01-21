package com.hyq.learning.thread;

import com.google.common.collect.Lists;
import com.hyq.learning.LearningApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.*;

@SpringBootTest(classes = LearningApplication.class)
@RunWith(SpringJUnit4ClassRunner.class)
public class ThreadLocalLearnTest {

    @Autowired
    ThreadLocalLearn threadLocalLearn;

    @Test
    public void test1() {
        ThreadPoolExecutor poolExecutor =
                new ThreadPoolExecutor(2, 2, 1000, TimeUnit.MICROSECONDS, new ArrayBlockingQueue<Runnable>(2));

        ArrayList<String> objects = Lists.newArrayList("1", "2", "3", "4");
        objects.forEach(o -> poolExecutor.execute(() -> threadLocalLearn.test(o)));
        String s = threadLocalLearn.mark.get();
        System.out.println(s);
    }
}