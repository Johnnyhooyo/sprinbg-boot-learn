package com.hyq.learning.leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

/**
 * @author：huyuanqiang
 * @time: 2021-02-19 11:27
 * @description:
 * 给定一个字符串 (s) 和一个字符模式 (p) ，实现一个支持 '?' 和 '*' 的通配符匹配。
 *
 * '?' 可以匹配任何单个字符。
 * '*' 可以匹配任意字符串（包括空字符串）。
 * 两个字符串完全匹配才算匹配成功。
 *
 * 说明:
 *
 * s 可能为空，且只包含从 a-z 的小写字母。
 * p 可能为空，且只包含从 a-z 的小写字母，以及字符 ? 和 *。
 **/
public class Pattern {

    public static void main(String[] args) {

        List<String> strings = new ArrayList<>();
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");
        strings.add("123");

        int i = Runtime.getRuntime().availableProcessors();
        int commonPoolParallelism = ForkJoinPool.getCommonPoolParallelism();
        System.out.println(commonPoolParallelism + "--" +i);

        new Thread(() -> strings.parallelStream().forEach(o -> {
            System.out.println(Thread.currentThread().getName() + "A");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();

        try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> strings.parallelStream().forEach(o -> {
            System.out.println(Thread.currentThread().getName() + "B");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        })).start();
    }

    public boolean isMatch(String s, String p) {
        return false;
    }
}
