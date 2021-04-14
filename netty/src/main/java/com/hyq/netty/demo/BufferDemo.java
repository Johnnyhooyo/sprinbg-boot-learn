package com.hyq.netty.demo;

import java.nio.IntBuffer;

/**
 * @author：huyuanqiang
 * @time: 2021-01-28 15:59
 * @description:
 **/
public class BufferDemo {
    public static void main(String[] args) {
        //创建一个IntBuffer, 大小为 5, 即可以存放5个int
        IntBuffer intBuffer = IntBuffer.allocate(5);
        for(int i = 0; i < intBuffer.capacity(); i++) {
            intBuffer.put(i);
        }
        intBuffer.clear();
        while (intBuffer.hasRemaining()) {
            System.out.println(intBuffer.get());
        }
    }
}
