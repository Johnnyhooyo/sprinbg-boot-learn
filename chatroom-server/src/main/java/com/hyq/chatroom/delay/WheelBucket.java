package com.hyq.chatroom.delay;

import lombok.extern.slf4j.Slf4j;

import java.util.Date;
import java.util.Objects;

/**
 * @author：huyuanqiang
 * @time: 2021-01-22 11:27
 * @description:
 **/
@Slf4j
public class WheelBucket {
    private final Object rootLock = new Object();
    private volatile Wheel root;

    private final Object tailLock = new Object();
    private volatile Wheel tail;

    public void timeUp() {
        if (Objects.nonNull(root)) {
            root.execute(new Date());
        }
    }

    public void pushWheel(Wheel wheel) {
        synchronized (rootLock) {
            if (Objects.isNull(root)) {
                root = wheel;
                tail = wheel;
                return;
            }
        }

    }
}
