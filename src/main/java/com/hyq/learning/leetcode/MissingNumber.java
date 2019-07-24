package com.hyq.learning.leetcode;

/**
 * @ClassName MissingNumber
 * @Author dibulidohu
 * @Date 2019/7/5 20:41
 * @Description
 */
public class MissingNumber {

    public static void main(String[] args) {
        int[] nums = {1,0,3};
        System.out.println(missingNumber(nums));
    }

    /***
     * 异或运算  自己异或自己=0 异或运算有交换律  所以用下表和对应数值疑惑 最后剩下缺的值
     */
    public static int missingNumber(int[] nums) {
        int miss = nums.length;
        for(int i = 0; i < nums.length; i++) {
            miss ^= i ^ nums[i];
        }
        return miss;
    }

    /***
     * 高斯运算 等差数列和 sum = n*(n+1)/2
     */
    public static int missingNumbers(int[] nums)  {
        int sum = (nums.length - 1) * nums.length / 2;
        int sum1 = 0;
        for (int i : nums) {
            sum1 += i;
        }
        return sum - sum1;
    }
}
