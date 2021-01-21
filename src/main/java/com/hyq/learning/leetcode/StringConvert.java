package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2020-04-11 14:34
 * @description:
 **/
public class StringConvert {

    public static String decodeString(String s) {
        StringBuilder res = new StringBuilder();
        char[] chars = s.toCharArray();
        int num = 0;
        boolean getNum = true;
        int count = 0;
        int subBegin = 0;
        for (int i = 0; i < chars.length; i++) {
            if (getNum &&'0' <= chars[i] && '9' >= chars[i]) {
                num = num * 10 + Integer.parseInt(String.valueOf(chars[i]));
            } else if ('[' == chars[i]) {
                count++;
                if (count == 1) {
                    subBegin = i+1;
                }
                getNum = false;
            } else if (']' == chars[i]) {
                if (--count == 0) {
                    String temp = decodeString(s.substring(subBegin, i));
                    for (int j = 0; j < num; j++) {
                        res.append(temp);
                    }
                    num = 0;
                    getNum = true;
                }
            } else if (getNum && 'a' <= chars[i] && 'z' >= chars[i]) {
                res.append(chars[i]);
            } else if (getNum && 'A' <= chars[i] && 'Z' >= chars[i]) {
                res.append(chars[i]);
            }
        }
        return res.toString();
    }
}
