package com.hyq.learning.leetcode;

import java.util.LinkedList;
import java.util.List;

/**
 * @author：huyuanqiang
 * @time: 2019-09-12 11:15
 * @description: Integer.MIN_VALUE时，绝对值会出错 需要特殊处理（可以不管值的大小 直接转为long）
 **/
public class Function2Decimal {

    public static void main(String[] args) {
        Function2Decimal function2Decimal = new Function2Decimal();
        String s = function2Decimal.fractionToDecimal(-7,  12);
        System.out.println(s);
    }

    public String fractionToDecimal(int numerator, int denominator) {
        boolean negative = false;
        if (numerator < 0 && denominator > 0
                || numerator > 0 && denominator < 0) {
            negative = true;
        }
        long num = Math.abs((long)numerator);
        long den = Math.abs((long)denominator);
        long before = num / den;
        StringBuilder stringBuilder = negative ? new StringBuilder("-").append(before) : new StringBuilder(String.valueOf(before));
        StringBuilder after = new StringBuilder();
        List<Long> list = new LinkedList<>();
        long rr = num % den;
        if (rr == 0) {
            return stringBuilder.toString();
        }
        stringBuilder.append(".");
        boolean isCircle = false;
        int index = 0;
        while (rr != 0) {
            if (list.contains(rr * 10)) {
                isCircle = true;
                index = list.indexOf(rr * 10);
                break;
            }
            list.add(rr * 10);
            after.append(rr * 10 / denominator);
            rr = rr * 10 % denominator;
        }
        if (isCircle) {
            String substring = after.substring(index);
            String substring1 = after.substring(0, index);
            stringBuilder.append(substring1).append("(").append(substring).append(")") ;
        } else {
            stringBuilder.append(after);
        }
        return stringBuilder.toString();
    }
}
