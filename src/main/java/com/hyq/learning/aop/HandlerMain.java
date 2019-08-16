package com.hyq.learning.aop;

import java.util.LinkedList;

/**
 * @ClassName HandlerMain
 * @Author dibulidohu
 * @Date 2019/8/6 16:03
 * @Description
 */
public class HandlerMain {
    public static void main(String[] args) {
        double v = 2.0 / 0;
        System.out.println(v);
        double sqrt = Math.sqrt(-2);
        System.out.println(sqrt);
        InvocationServiceHandler handler = new InvocationServiceHandler(new InvocationServiceImpl());
        InvocationService proxy = handler.getProxy();
        proxy.print();
        proxy.println();
    }
}
