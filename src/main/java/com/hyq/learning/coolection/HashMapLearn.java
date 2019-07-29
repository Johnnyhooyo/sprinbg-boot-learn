package com.hyq.learning.coolection;

import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName HashMapLearn
 * @Author dibulidohu
 * @Date 2019/7/26 10:43
 * @Description
 */
public class HashMapLearn {

    public static void main(String[] args) {
        ConcurrentHashMap<String, String> concurrentHashMap = new ConcurrentHashMap<>(16);
        concurrentHashMap.put("a", "ss");
        System.out.println(concurrentHashMap);
        HashMap<String, String> hashMap = new HashMap<>(8);
        for (int i = 0; i < 1000; i++ ) {
            hashMap.put(String.valueOf(i), "index：" + i);
        }
        System.out.println(hashMap);
    }
}
