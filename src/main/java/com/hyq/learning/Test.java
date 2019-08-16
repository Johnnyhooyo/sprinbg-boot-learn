package com.hyq.learning;

import java.util.*;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;

/**
 * @author dibulidohu
 * @classname Test
 * @date 2019/5/2121:04
 * @description
 */
public class Test {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            list.add(i);
        }
        list.remove(1);
        list.add(11);
        list.add(12);
        long l = System.currentTimeMillis();
        Stack<Integer> stack = new Stack<>();
        for (int i = 0; i < 1000; i++) {
            stack.push(i);
        }
        long mid = System.currentTimeMillis();
        System.out.println("push cost :" + (mid - l));
        for (int i = 0; i < 1000; i++) {
            stack.peek();
        }
        long mm = System.currentTimeMillis();
        System.out.println("peek cost :" + (mm - mid));
        for (int i = 0; i < 1000; i++) {
            stack.pop();
        }
        System.out.println("pop cost :" + (System.currentTimeMillis() - mm));


        Vector<Integer> vector = new Vector<>();
        for (int i = 0; i < 1000; i++) {
            vector.add(i);
        }
        System.out.println(vector.size());
        vector.add(11);
        System.out.println( vector.capacity());
        vector.trimToSize();
        System.out.println( vector.capacity());
        long l1 = System.currentTimeMillis();
        Enumeration<Integer> elements = vector.elements();
        while (elements.hasMoreElements()) {
            elements.nextElement();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(elements.nextElement());
        }
        long l2 = System.currentTimeMillis();
        System.out.println("elements cost:" + (l2 - l1));
        Iterator<Integer> iterator = vector.iterator();
        while (iterator.hasNext()) {
            iterator.next();
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
           // System.out.println(iterator.next());
        }
        long l3 = System.currentTimeMillis();
        System.out.println("iterator cost:" + (l3 - l2));
        for (Integer aVector : vector) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(aVector);
        }
        long l4 = System.currentTimeMillis();
        System.out.println("forEach cost:" + (l4 - l3));
        int size = vector.size();
        for (int i=0; i<size; i++) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //System.out.println(vector.get(i));
        }
        System.out.println("index cost:" + (System.currentTimeMillis() - l4));
    }
}
