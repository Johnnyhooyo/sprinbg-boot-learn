package com.hyq.learning.queue;

import java.util.concurrent.DelayQueue;

/**
 * @ClassName DelayQueueLearn
 * @Author dibulidohu
 * @Date 2019/7/24 10:17
 * @Description DelayQueue：基于PriorityQueue，一种延时阻塞队列，DelayQueue中的元素只有当其指定的延迟时间到了，才能够从队列中获取到该元素。
 * DelayQueue也是一个无界队列，因此往队列中插入数据的操作（生产者）永远不会被阻塞，而只有获取数据的操作（消费者）才会被阻塞.
 */
public class DelayQueueLearn {

    private static DelayQueue<ItemDto<String>> delayQueue = new DelayQueue<>();

    private static class ProductThread extends Thread {
        @Override
        public void run() {
            ItemDto<String> cacheData = new ItemDto<>(5 * 1000, "cache data");
            delayQueue.add(cacheData);
        }
    }

    private static class ConsumerThread implements Runnable {

        @Override
        public void run() {
            try {
                ItemDto<String> take = delayQueue.take();
                System.out.println(System.currentTimeMillis());
                System.out.println(take.getData());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        ProductThread productThread = new ProductThread();
        ConsumerThread consumerThread = new ConsumerThread();
        productThread.start();
        Thread thread = new Thread(consumerThread);
        thread.start();
    }
}
