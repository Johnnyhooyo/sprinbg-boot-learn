package com.hyq.learning.leetcode;

/**
 * @ClassName MaxProductSubarray
 * @Author dibulidohu
 * @Date 2019/6/26 9:49
 * @Description 给定一个整数数组 nums ，找出一个序列中乘积最大的连续子序列（该序列至少包含一个数）。
 * 思路： 记录当前之前的可能最大值和最小值 每次计算完 用最大值和结果只比较 取大值给结果
 *    当当前值是负数  max和min 对调一下 因为负负得正
 */
public class MaxProductSubarray {

    public static void main(String[] args) {
        int[] a = {2,3,-2,4,-5,3};
        System.out.println(maxProduct(a));
    }

    public static int maxProduct(int[] nums) {
        int min = nums[0];
        int max = nums[0];
        int res = nums[0];
        for(int i = 1; i < nums.length; i++) {
            if (nums[i] < 0) {
                int temp = max;
                max = min;
                min = temp;
            }
            max = max * nums[i] > nums[i] ? nums[i] * max : nums[i];
            min = min * nums[i] < nums[i] ? nums[i] * min : nums[i];
            res = res > max ? res : max;
        }
        return res;
    }
}
