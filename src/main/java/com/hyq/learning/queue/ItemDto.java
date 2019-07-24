package com.hyq.learning.queue;

import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * @ClassName ItemDto
 * @Author dibulidohu
 * @Date 2019/7/24 10:29
 * @Description
 */
public class ItemDto<T> implements Delayed {

    private long expireTime;
    private T data;

    public ItemDto(long expireTime, T data) {
        super();
        this.expireTime = expireTime + System.currentTimeMillis();
        this.data = data;
    }

    public long getExpireTime() {
        return expireTime;
    }

    public T getData() {
        return data;
    }

    @Override
    public long getDelay(TimeUnit unit) {
        return unit.convert(this.expireTime - System.currentTimeMillis(), unit);
    }

    @Override
    public int compareTo(Delayed o) {
        long time = getDelay(TimeUnit.NANOSECONDS) - o.getDelay(TimeUnit.NANOSECONDS);
        return (time == 0) ? 0 : (time < 0 ? -1 : 1);
    }
}
