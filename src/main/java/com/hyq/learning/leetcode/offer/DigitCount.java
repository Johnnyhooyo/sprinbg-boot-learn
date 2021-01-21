package com.hyq.learning.leetcode.offer;

/**
 * @author：huyuanqiang
 * @time: 2020-07-01 08:33
 * @description:
 **/
public class DigitCount {

    public static void main(String[] args) {
        DigitCount digitCount = new DigitCount();
        int i = digitCount.countDigitOne(12);
        System.out.println(i);
    }

    public int countDigitOne(int n) {
        int res = 0;
        int digit = n % 10;
        int low = 0;
        int high = n / 10;
        int cur = 1;
        while(high != 0 || digit != 0) {
            if(digit == 0) {
                res += high * cur;
            } else if (digit == 1) {
                res += (high * cur + 1 + low);
            } else {
                res += (high + 1) * cur;
            }
            low = digit * cur + low;
            digit = high % 10;
            high = high / 10;
            cur *= 10;
        }
        return res;
    }
}
