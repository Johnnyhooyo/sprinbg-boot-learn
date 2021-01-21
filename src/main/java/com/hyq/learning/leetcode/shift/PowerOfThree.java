package com.hyq.learning.leetcode.shift;

/**
 * @author：huyuanqiang
 * @time: 2019-11-09 14:29
 * @description: 你能不使用循环或者递归来完成本题吗？
 **/
public class PowerOfThree {

    public static void main(String[] args) {
        System.out.println(isPowerOfThree(9));
    }

    public static boolean isPowerOfThree(int n) {
        String s = Integer.toString(n, 3);
        return s.matches("^10*$");
    }
}
