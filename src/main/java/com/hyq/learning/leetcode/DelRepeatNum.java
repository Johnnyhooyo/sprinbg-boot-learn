package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2021-04-06 16:11
 * @description:
 **/
public class DelRepeatNum {

    public static void main(String[] args) {
        DelRepeatNum delRepeatNum = new DelRepeatNum();
        delRepeatNum.removeDuplicates(new int[] {1,1,1,2,2,3});
    }

    public int removeDuplicates(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        int del = 0;
        int temp = nums[0];
        int count = 0;
        for(int i = 1; i < nums.length; i++) {
            int index = nums[i];
            if (del > 0) {
                int p = nums[i - del];
                nums[i - del] = index;
                nums[i] = p;
            }
            if (index == temp) {
                if(++count > 1) {
                    del++;
                }
            } else {
                temp = index;
                count = 0;
            }
        }
        return nums.length - del;
    }
}
