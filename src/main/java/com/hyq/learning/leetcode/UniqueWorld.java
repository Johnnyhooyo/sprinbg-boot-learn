package com.hyq.learning.leetcode;

import java.util.*;

/**
 * @author：huyuanqiang
 * @time: 2019-09-12 16:54
 * @description: a world exists only once and not exists in other letter
 **/
public class UniqueWorld {

    public static void main(String[] args) {
        String[] strings = uncommonFromSentences(" apple  is poll", "banana is poll");
        System.out.println(Arrays.toString(strings));
    }

    public static String[] uncommonFromSentences(String A, String B) {
        String str = A.concat(" ").concat(B);
        String[] split = str.trim().split("\\s+");
        List<String> list = new ArrayList<>();
        List<String> list1 = new ArrayList<>();
        for (String s : split) {
            if (!list1.contains(s)) {
                if (list.contains(s)) {
                    list.remove(s);
                    list1.add(s);
                } else {
                    list.add(s);
                }
            }
        }
        return list.toArray(new String[0]);
    }
}
