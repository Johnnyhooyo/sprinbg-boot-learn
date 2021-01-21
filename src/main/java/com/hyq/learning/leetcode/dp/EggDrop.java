package com.hyq.learning.leetcode.dp;

/**
 * @author：huyuanqiang
 * @time: 2020-04-13 19:17
 * @description:  此题未做 题意理解不透
 **/
public class EggDrop {

    public static void main(String[] args) {
        System.out.println(superEggDrop(2, 5));
        System.out.println(superEggDrop(2, 6));
        System.out.println(superEggDrop(2, 7));
        System.out.println(superEggDrop(3, 14));
    }

    public static int superEggDrop(int k, int n) {
        if (k == 1) {
            return n;
        }
        if (n == 0) {
            return 0;
        }
        int mid = (n+1) / 2;
        int pre = superEggDrop(k - 1, n - mid);
        int after = superEggDrop(k, mid - 1);
        return 1 + Math.min(pre, after);
    }
}
