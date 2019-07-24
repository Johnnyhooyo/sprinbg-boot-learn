package com.hyq.learning.leetcode;

/**
 * @author dibulidohu
 * @classname RingBuffer
 * @date 2019/6/416:59
 * @description 设计你的循环队列实现。 循环队列是一种线性数据结构，其操作表现基于 FIFO（先进先出）原则并且队尾被连接在队首之后以形成一个循环。它也被称为“环形缓冲器”。
 */
public class RingBuffer {

    public static void main(String[] args) {
        MyCircularQueue queue = new MyCircularQueue(3);
        boolean b3 = queue.enQueue(1);
        boolean b2 = queue.enQueue(2);
        boolean b1 = queue.enQueue(3);
        boolean b = queue.enQueue(4);
        int rear = queue.Rear();
        boolean full = queue.isFull();
        boolean b4 = queue.deQueue();
        boolean b5 = queue.enQueue(4);
        boolean b6 = queue.deQueue();

        MyCircularQueue circularQueue = new MyCircularQueue(6);
        boolean b7 = circularQueue.enQueue(6);
        int rear1 = circularQueue.Rear();
        int rear2 = circularQueue.Rear();
        boolean b8 = circularQueue.deQueue();
        boolean b9 = circularQueue.enQueue(5);
        int rear3 = circularQueue.Rear();
        boolean b10 = circularQueue.deQueue();
        int front = circularQueue.Front();
        boolean b11 = circularQueue.deQueue();
        boolean b12 = circularQueue.deQueue();
        boolean b13 = circularQueue.deQueue();
    }

    static class MyCircularQueue {
        private int start = -1;
        private int last = -1;
        private int[] arr;
        private int k;

        /** Initialize your data structure here. Set the size of the queue to be k. */
        public MyCircularQueue(int k) {
            arr = new int[k];
            this.k = k;
        }

        /** Insert an element into the circular queue. Return true if the operation is successful. */
        public boolean enQueue(int value) {
            if(last - start >= k - 1) {
                return false;
            } else if(last < k - 1) {
                arr[++last] = value;
                if (start == -1) {
                    start = 0;
                }
            } else {
                for(int i = 0; i<= last - start; i++) {
                    arr[i] = arr[i+start];
                }
                last = last - start + 1;
                start = 0;
                arr[last] = value;
            }
            return true;
        }

        /** Delete an element from the circular queue. Return true if the operation is successful. */
        public boolean deQueue() {
            if(start < last) {
                return false;
            } else {
                arr[start++] = 0;
                return true;
            }
        }

        /** Get the front item from the queue. */
        public int Front() {
            if(start < last) {
                return -1;
            } else {
                return arr[start];
            }
        }

        /** Get the last item from the queue. */
        public int Rear() {
            if(start < last) {
                return -1;
            } else {
                return arr[last];
            }
        }

        /** Checks whether the circular queue is empty or not. */
        public boolean isEmpty() {
            return start == -1 || start < last;
        }

        /** Checks whether the circular queue is full or not. */
        public boolean isFull() {
            return (last - start) == (k -1);
        }
    }
}
