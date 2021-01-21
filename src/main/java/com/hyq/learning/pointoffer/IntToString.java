package com.hyq.learning.pointoffer;

/**
 * @author：huyuanqiang
 * @time: 2020-02-25 18:24
 * @description:
 **/
public class IntToString {

    public static void main(String[] args) {
        int i = translateNum(12238);
        System.out.println(i);
    }

    public static int translateNum(int num) {
        char[] chars = Integer.toString(num).toCharArray();
        int last = chars[0] - '0';
        int count = 1;
        for (int i = 1; i < chars.length; i++) {
            if (last > 2 || last == 0) {
                last = chars[i] - '0';
                continue;
            }
            if (last == 1) {
                count ++;
                last = chars[i] - '0';
                continue;
            }
            if (chars[i] - '0' < 6) {
                count ++;
                last = chars[i] - '0';
                continue;
            }
            last = chars[i] - '0';
        }
        return count;
    }
}
