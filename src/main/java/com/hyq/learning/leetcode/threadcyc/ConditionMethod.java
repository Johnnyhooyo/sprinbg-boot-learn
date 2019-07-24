package com.hyq.learning.leetcode.threadcyc;

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
    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Lock.lock();
                    if (mark != 0) {
                        condition1.await();
                    }
                    System.out.println("A");
                    mark = 1;
                    condition2.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Lock.unlock();
            }
        }
    }
    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Lock.lock();
                    if (mark != 1) {
                        condition2.await();
                    }
                    System.out.println("B");
                    mark = 2;
                    condition3.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Lock.unlock();
            }
        }
    }
    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    Lock.lock();
                    if (mark != 2) {
                        condition3.await();
                    }
                    System.out.println("C");
                    mark = 0;
                    condition1.signal();
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                Lock.unlock();
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
