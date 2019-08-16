package com.hyq.learning.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @ClassName AopLearn
 * @Author dibulidohu
 * @Date 2019/8/6 15:03
 * @Description
 */
public class InvocationServiceHandler implements InvocationHandler {

    private InvocationService invocationService;

    public InvocationServiceHandler(InvocationService invocationService) {
        this.invocationService = invocationService;
    }

    public InvocationService getProxy() {
        return (InvocationService) Proxy.newProxyInstance(invocationService.getClass().getClassLoader(), invocationService.getClass().getInterfaces(), this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("handler before method");
        Object invoke = method.invoke(invocationService, args);
        System.out.println("handler after method");
        return invoke;
    }
}
