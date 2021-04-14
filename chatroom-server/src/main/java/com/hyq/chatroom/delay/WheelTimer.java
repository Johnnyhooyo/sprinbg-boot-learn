package com.hyq.chatroom.delay;

import java.util.concurrent.TimeUnit;

/**
 * @author：huyuanqiang
 * @time: 2021-01-22 10:40
 * @description:
 **/
public interface WheelTimer {
    /**
     * 往时间轮内添加任务
     * @param timeUpTask 到期执行的任务
     * @param time 时间
     * @param timeUnit 时间单位
     */
    public void push(BaseTimeUpTask timeUpTask, int time, TimeUnit timeUnit);

    /**
     * 时间轮启动
     */
    void run();

    /**
     * 时间轮关闭
     */
    void shutdown();
}
