package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2021-02-19 10:25
 * @description: 给定一个由若干 0 和 1 组成的数组 A，我们最多可以将 K 个值从 0 变成 1 。
 *
 * 返回仅包含 1 的最长（连续）子数组的长度。
 *
 * 示例 1：
 *
 * 输入：A = [1,1,1,0,0,0,1,1,1,1,0], K = 2
 * 输出：6
 * 解释：
 * [1,1,1,0,0,1,1,1,1,1,1]
 * 粗体数字从 0 翻转到 1，最长的子数组长度为 6。
 **/
public class LongOneArr {

    public static void main(String[] args) {
        LongOneArr l
                 = new LongOneArr();
        int i = l.longestOnes(new int[]{1, 1, 1, 0, 0, 0, 1, 1, 1, 1, 0, 1, 1}, 2);
        System.out.println(i);
    }

    public int longestOnes(int[] A, int K) {
        int res = 0;
        int zeroCount = 0;
        for (int i = 0, j= 0; j < A.length; ) {
            if (zeroCount < K) {
                if (A[j] == 0) {
                    zeroCount++;
                }
                j++;
                res = Math.max(res, j - i);
            } else if (zeroCount == K && A[j] == 1) {
                j++;
                res = Math.max(res, j - i);
            } else {
                if (A[i] == 0) {
                    zeroCount--;
                }
                i++;
            }
        }
        return res;
    }
}
