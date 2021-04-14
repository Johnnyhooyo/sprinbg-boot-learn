package com.hyq.chatroom.delay;

import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.TimeUnit;

/**
 * @author：huyuanqiang
 * @time: 2021-01-22 11:15
 * @description: 默认的时间轮
 **/
@Slf4j
public class DefaultWheelTimer implements WheelTimer {

    private TimeUnit wheelTimeUnit;
    private int wheelTime;
    private int wheelLength;

    private int curPosition;

    private WheelBucket[] wheelBuckets;

    private volatile boolean isRunning;

    public DefaultWheelTimer(TimeUnit timeUnit, int wheelTime, int wheelLength) {
        this.wheelTimeUnit = timeUnit;
        this.wheelTime = wheelTime;
        this.wheelLength = wheelLength;
        this.buildBuckets();
    }

    private void buildBuckets() {
        wheelBuckets = new WheelBucket[wheelLength];
        for (int i = 0; i < wheelBuckets.length; i++) {
            wheelBuckets[i] = new WheelBucket();
        }
    }

    @Override
    public void push(BaseTimeUpTask timeUpTask, int time, TimeUnit timeUnit) {
        if (!isRunning) {
            throw new RuntimeException("current wheelTimer is not running, please try again after running");
        }
        long delayTime = wheelTimeUnit.convert(time, timeUnit);
        if (delayTime % wheelTime > 0) {
            throw new RuntimeException("precision is not match");
        }
        long span = delayTime / wheelTime;
        int round = (int) span / wheelLength;
        int position = (int) span % wheelLength;

        Wheel wheel = new Wheel(round, position + curPosition, timeUpTask);

        wheelBuckets[position - 1].pushWheel(wheel);
    }

    @Override
    public void run() {
        isRunning = true;
    }

    @Override
    public void shutdown() {

    }
}
