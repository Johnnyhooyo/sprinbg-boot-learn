package com.hyq.learning.leetcode.threadcyc;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author dibulidohu
 * @classname ConditionMethod
 * @date 2019/5/715:27
 * @description
 */
public class ConditionMethod {
    private static ReentrantLock Lock = new ReentrantLock();
    private static Condition condition1 = Lock.newCondition();
    private static Condition condition2 = Lock.newCondition();
    private static Condition condition3 = Lock.newCondition();

    private static int mark = 0;
    private static AtomicInteger count = new AtomicInteger(0);
    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    if (30 <= count.get()) {
                        break;
                    }
                    Lock.lock();
                    System.out.println(Thread.currentThread().getName() + "get lock");
                    if (mark != 0) {
                        System.out.println(Thread.currentThread().getName() + "await");

                        condition1.await();
                        System.out.println(Thread.currentThread().getName() + "go");

                    }
                    System.out.println(count.get() + "A"  + "---" + Lock.getHoldCount());
                    mark = 1;
                    condition2.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "exit");
                Lock.unlock();
                condition3.signal();
                condition2.signal();
            }
        }
    }
    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    if (30 <= count.get()) {
                        break;
                    }
                    Lock.lock();
                    System.out.println(Thread.currentThread().getName() + "get lock");
                    if (mark != 1) {
                        System.out.println(Thread.currentThread().getName() + "await");

                        condition2.await();
                        System.out.println(Thread.currentThread().getName() + "go");

                    }
                    System.out.println(count.get() + "B" + "---" + Lock.getHoldCount());
                    mark = 2;
                    condition3.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "exit");
                Lock.unlock();
                condition1.signal();
                condition3.signal();
            }
        }
    }
    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    if (30 <= count.get()) {
                        break;
                    }
                    Lock.lock();
                    System.out.println(Thread.currentThread().getName() + "get lock");
                    if (mark != 2) {
                        System.out.println(Thread.currentThread().getName() + "await");
                        condition3.await();
                        System.out.println(Thread.currentThread().getName() + "go");
                    }
                    System.out.println(count.getAndAdd(1) + "C"  + "---" + Lock.getHoldCount());

                    mark = 0;
                    condition1.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                System.out.println(Thread.currentThread().getName() + "exit");
                Lock.unlock();
                condition1.signal();
                condition2.signal();
            }
        }
    }


    public static void main(String[] args) {
        ThreadA threadA = new ThreadA();
        ThreadB threadB = new ThreadB();
        ThreadC threadC = new ThreadC();
        threadB.start();
        threadA.start();
        threadC.start();
    }

}
