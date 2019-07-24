package com.hyq.learning.leetcode;

/**
 * @ClassName FirstMissingPositive
 * @Author dibulidohu
 * @Date 2019/7/3 20:52
 * @Description
 */
public class FirstMissingPositive {

    public static void main(String[] args) {
        int[] nums = {0,2,2,1,1};
        System.out.println(firstMissingPositive(nums));
    }

    public static int firstMissingPositive(int[] nums) {
        sort(nums, 0, nums.length - 1);
        int start = 0;
        for(int num : nums) {
            if(num > 0) {
                break;
            }
            start++;
        }
        int res = 1;
        int last = -1;
        for(; start < nums.length; start++) {
            if(last == nums[start]) {
                continue;
            } else {
                last = nums[start];
            }
            if(res != nums[start]) {
                break;
            }
            res++;
        }
        return res;
    }

    private static void sort(int[] nums, int start, int end) {
        if(start < end) {
            int point = nums[end];
            int p1 = start;
            for(int p2 = start; p2 < end; p2++)  {
                if(nums[p2] < point) {
                    int temp = nums[p1];
                    nums[p1] = nums[p2];
                    nums[p2] = temp;
                    p1++;
                }
            }
            nums[end] = nums[p1];
            nums[p1] = point;
            sort(nums, start, p1 -1);
            sort(nums, p1 + 1, end);
        }
    }
}
