package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2021-03-31 19:09
 * @description:
 **/
public class Clock {

    public static void main(String[] args) {
        System.out.println(angleClock(12, 40));
    }

    public static double angleClock(int hour, int minutes) {
        double h = hour/12.0*360 % 360;
        double hm = minutes/60.0*30 % 360;
        double m = minutes/60.0*360 % 360;
        double abs = Math.abs(h + hm - m);
        return Math.min(360 - abs, abs);
    }
}
