package com.hyq.learning.leetcode;

/**
 * @ClassName MaxSubListSun
 * @Author dibulidohu
 * @Date 2019/6/19 10:13
 * @Description 给定一个整数数组 nums ，找到一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 */
public class MaxSubArray {

    public static void main(String[] args) {
        int prices[] = {-1,-1,-2,-2};
        System.out.println(maxSubArray(prices));
        System.out.println(maxSubs(prices));
    }

    /**
     *  * dp 思路：一个数字记录 前N个数字的最大值
     *  *  再用一个数字记录到目前数字的连续数字的最大值 如果为负数就重新开始计算
     *  *  每次取最前最大的和目前算出来的最大的
     */
    public static int maxSubArray(int[] nums) {
        int max = nums[0];
        int num = 0;
        for (int i : nums) {
            if (num > 0) {
                num += i;
            } else {
                num = i;
            }
            max = Math.max(num, max);
        }
        return max;
    }

    /**
     * 分治 思路：最大和是 maxLeft maxRight bounderLeft + bounderRight 三者的最大者
     * 8 -1 7  以-1分开 前面max=7 后面max=7  链接是max=14
     * [8,-19,5,-4,20]
     */
    public static int maxSubs(int[] nums) {
        return maxSub(nums, 0, nums.length - 1);
    }

    public static int maxSub(int[] nums, int start, int end) {
        int index = (start + end) / 2;
        if (end == start) {
            return nums[start];
        } else {
            int maxleft = maxSub(nums, start, index);
            int maxright = maxSub(nums, index + 1, end);
            int bl = bounderLeft(nums, start, index);
            int br = bounderRight(nums, index + 1, end);
            return max(maxleft, maxright, (bl + br));
        }
    }

    private static int bounderLeft(int[] nums, int index, int end) {
        int num = 0;
        int max = nums[end];
        for (int i = end; i >= index; i--) {
            num += nums[i];
            max = Math.max(max, num);
        }
        return max;
    }

    private static int bounderRight(int[] nums, int index, int end) {
        int num = 0;
        int max = nums[index];
        for (int i = index; i <= end; i++) {
            num += nums[i];
            max = Math.max(max, num);
        }
        return max;
    }

    public static int max(int a, int b, int c){
        if (a >= b && a >= c) {
            return a;
        } else if (b >= a && b >= c) {
            return b;
        } else {
            return c;
        }
    }
}


