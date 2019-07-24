package com.hyq.learning.leetcode.threadcyc;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dibulidohu
 * @classname CircularDoubleEndedQueue
 * @date 2019/6/415:07
 * @description  设计实现双端队列。
 */
public class CircularDoubleEndedQueue {

    public static void main(String[] args) {
        MyCircularDeque circularDeque = new MyCircularDeque(3);
        boolean b = circularDeque.insertFront(8);
        boolean b1 = circularDeque.insertLast(8);
        boolean b2 = circularDeque.insertLast(2);
        int front = circularDeque.getFront();
        boolean b3 = circularDeque.deleteLast();
        int rear = circularDeque.getRear();
        boolean b4 = circularDeque.insertFront(9);
        boolean b5 = circularDeque.deleteFront();
        int rear1 = circularDeque.getRear();
        boolean b6 = circularDeque.insertLast(2);
        boolean full = circularDeque.isFull();
    }

    static class MyCircularDeque {
        private int size;
        private AtomicInteger curSize = new AtomicInteger(0);
        private List<Integer> left;
        private List<Integer> right;

        /** Initialize your data structure here. Set the size of the deque to be k. */
        public MyCircularDeque(int k) {
            this.size = k;
            left = new ArrayList<>();
            right = new ArrayList<>();
        }

        /** Adds an item at the front of Deque. Return true if the operation is successful. */
        public boolean insertFront(int value) {
            curSize.incrementAndGet();
            if(curSize.get() > size) {
                curSize.decrementAndGet();
                return false;
            } else {
                left.add(value);
            }
            return true;
        }

        /** Adds an item at the rear of Deque. Return true if the operation is successful. */
        public boolean insertLast(int value) {
            curSize.incrementAndGet();
            if(curSize.get() > size) {
                curSize.decrementAndGet();
                return false;
            } else {
                right.add(value);
            }
            return true;
        }

        /** Deletes an item from the front of Deque. Return true if the operation is successful. */
        public boolean deleteFront() {
            if(left.size() == 0 && right.size() == 0) {
                return false;
            } else if(left.size() == 0) {
                right.remove(0);
                curSize.decrementAndGet();
                return true;
            } else {
                left.remove(left.size() -1);
                curSize.decrementAndGet();
                return true;
            }
        }

        /** Deletes an item from the rear of Deque. Return true if the operation is successful. */
        public boolean deleteLast() {
            if(left.size() == 0 && right.size() == 0) {
                return false;
            } else if(right.size() == 0) {
                left.remove(0);
                curSize.decrementAndGet();
                return true;
            } else {
                right.remove(right.size() -1);
                curSize.decrementAndGet();
                return true;
            }
        }

        /** Get the front item from the deque. */
        public int getFront() {
            if(left.size() == 0 && right.size() == 0) {
                return -1;
            } else if(left.size() == 0) {
                return right.get(0);
            } else {
                return left.get(left.size() - 1);
            }
        }

        /** Get the last item from the deque. */
        public int getRear() {
            if(left.size() == 0 && right.size() == 0) {
                return -1;
            } else if(right.size() == 0) {
                return left.get(0);
            } else {
                return right.get(right.size() -1 );
            }
        }

        /** Checks whether the circular deque is empty or not. */
        public boolean isEmpty() {
            return curSize.get() == 0;
        }

        /** Checks whether the circular deque is full or not. */
        public boolean isFull() {
            return curSize.get() == size;
        }
    }
}
