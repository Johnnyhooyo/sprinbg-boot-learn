package com.hyq.learning.work;

import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.ThreadPoolExecutor;

/**
 * @author：huyuanqiang
 * @time: 2020-11-14 15:00
 * @description:
 **/
public class Test {

    public static void main(String[] args) {
        ThreadPoolTaskExecutor threadPoolTaskExecutor = new ThreadPoolTaskExecutor();
        threadPoolTaskExecutor.setCorePoolSize(10);
        threadPoolTaskExecutor.setMaxPoolSize(20);
        threadPoolTaskExecutor.setQueueCapacity(10);
        threadPoolTaskExecutor.setThreadNamePrefix("async-kafka-backup-thread-");
        threadPoolTaskExecutor.setRejectedExecutionHandler(new ThreadPoolExecutor.DiscardPolicy());
        threadPoolTaskExecutor.initialize();

        for (int i = 0; i < 100; i++) {
            boolean isAdd = true;
            while (isAdd) {
                if (threadPoolTaskExecutor.getActiveCount() < threadPoolTaskExecutor.getMaxPoolSize()) {
                    // 异步执行
                    int finalI = i;
                    threadPoolTaskExecutor.execute(() -> {
                        System.out.println("run" + finalI + java.lang.Thread.currentThread().getName());
                        try {
                            Thread.sleep(3000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    });
                    isAdd = false;
                }
            }
            System.out.println("-------" + i + "---------");
        }
        System.out.println("------end-------");
    }
}
