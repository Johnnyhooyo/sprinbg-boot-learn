package com.hyq.learning.aop;

/**
 * @ClassName InvocationServiceImpl
 * @Author dibulidohu
 * @Date 2019/8/6 15:57
 * @Description
 */
public class InvocationServiceImpl implements InvocationService {
    @Override
    public void print() {
        System.out.println("real service print content");
    }

    @Override
    public void println() {
        System.out.println("println service");
    }
}
