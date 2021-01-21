package com.hyq.learning.thread;

import org.springframework.stereotype.Service;

/**
 * @author：huyuanqiang
 * @time: 2020-05-09 16:51
 * @description:
 **/
@Service
public class ThreadLocalLearn {

    ThreadLocal<String> mark = new ThreadLocal<>();
    ThreadLocal<Integer> intMark = new ThreadLocal<>();

    public void test (String s) {
        String name = Thread.currentThread().getName();
        if ("1".equals(s)) {
            mark.set(s);
            intMark.set(Integer.valueOf(s));
        }
        String s1 = mark.get();
        System.out.println(name + s + "====" + s1);
        Integer integer = intMark.get();
        System.out.println(name + s + "====" + integer);

//        mark.remove();
    }
}
