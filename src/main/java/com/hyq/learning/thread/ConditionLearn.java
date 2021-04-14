package com.hyq.learning.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author：huyuanqiang
 * @time: 2021-04-09 16:53
 * @description:
 **/
public class ConditionLearn {

    static Lock lock = new ReentrantLock();
    static Condition condition = lock.newCondition();
    static int count = 0;


    public static void main(String[] args) {

    }

    class t1 implements Runnable {

        @Override
        public void run() {
            lock.lock();
            if (count++ % 2 == 0) {
                System.out.println("A");
            }

        }
    }
}
