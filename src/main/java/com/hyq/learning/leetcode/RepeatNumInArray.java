package com.hyq.learning.leetcode;

import java.util.Arrays;

/**
 * @author dibulidohu
 * @classname RepeatNumInArray
 * @date 2019/5/1113:04
 * @description
 */
public class RepeatNumInArray {

    public static void main(String[] args) {
        int []  s = {9,3,4,2,2};
        //int duplicate = findDuplicate(s);
       // System.out.println(duplicate);
        String[] split = "j sd ".split(" ");
        System.out.println(Arrays.toString(split));
    }
    public static int findDuplicate(int[] nums) {
        /**   给定一个包含 n + 1 个整数的数组 nums，其数字都在 1 到 n 之间（包括 1 和 n），可知至少存在一个重复的整数。假设只有一个重复的整数，找出这个重复的数。
         快慢指针思想, fast 和 slow 是指针, nums[slow] 表示取指针对应的元素
         注意 nums 数组中的数字都是在 1 到 n 之间的(在数组中进行游走不会越界),
         因为有重复数字的出现, 所以这个游走必然是成环的, 环的入口就是重复的元素,
         即按照寻找链表环入口的思路来做
         **/
        int fast = 0, slow = 0;
        while(true) {
            fast = nums[nums[fast]];
            slow = nums[slow];
            if(slow == fast) {
                fast = 0;
                while(nums[slow] != nums[fast]) {
                    fast = nums[fast];
                    slow = nums[slow];
                }
                return nums[slow];
            }
        }
    }

    /**   查找数组中的重复数字  数组中只有一个重复的数字，但它可能不止重复出现一次。
     **/
 /*   public int findDuplicate(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            for(int j = i + 1; j < nums.length; j++) {
                if(nums[i] == nums[j]) {
                    return nums[i];
                }
            }
        }
        return nums[nums.length -1];
    }*/
}
