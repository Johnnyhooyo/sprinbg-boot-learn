package com.hyq.learning.leetcode.threadcyc;

import java.util.concurrent.Semaphore;

/**
 * @author dibulidohu
 * @classname SemaphoreMethod
 * @date 2019/5/715:28
 * @description
 */
public class SemaphoreMethod {
    private static Semaphore semaphoreA = new Semaphore(1);
    private static Semaphore semaphoreB = new Semaphore(0);
    private static Semaphore semaphoreC = new Semaphore(0);

    static class ThreadA extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    semaphoreA.acquire();
                    System.out.println("A");
                    semaphoreB.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadB extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    semaphoreB.acquire();
                    System.out.println("B");
                    semaphoreC.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    static class ThreadC extends Thread {
        @Override
        public void run() {
            try {
                while (true) {
                    semaphoreC.acquire();
                    System.out.println("C");
                    semaphoreA.release();
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        new ThreadA().start();
        new ThreadB().start();
        new ThreadC().start();
    }
}
