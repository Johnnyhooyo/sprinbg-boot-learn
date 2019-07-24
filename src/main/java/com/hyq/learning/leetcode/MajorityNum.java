package com.hyq.learning.leetcode;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.TreeMap;

/**
 * @ClassName MajorityNum
 * @Author dibulidohu
 * @Date 2019/6/13 19:33
 * @Description 给定一个大小为 n 的数组，找到其中的众数。众数是指在数组中出现次数大于 ⌊ n/2 ⌋ 的元素。
 *
 * 你可以假设数组是非空的，并且给定的数组总是存在众数。
 *
 * Ⅱ 所有大于[n/3]的  [1,2,2,3,2,1,1,3]
 */
public class MajorityNum {
    public static void main(String[] args) {
        int[] ints = {1,2,2,3,2,1,1,3};
        //System.out.println(majorityElement(ints));
        System.out.println(majorityElementTwo(ints));
    }

    public static List<Integer> majorityElementTwo(int[] nums) {
        int count = 0;
        int countMax = 0;
        int maj = 0;
        int max = 0;
        for (int i = 0; i < nums.length; i++) {
            if ((maj == 0 || maj == nums[i]) && nums[i] != max) {
                maj = nums[i];
                count++;
            } else if (max == 0 || max == nums[i]){
                max = nums[i];
                countMax++;
            } else {
                count--;
                countMax--;
                if (count == 0) {
                    maj = 0;
                }
                if (countMax == 0) {
                    max = 0;
                }
            }
        }
        count = 0;
        countMax = 0;
        List<Integer> res = new ArrayList<>();
        for (int num : nums) {
            if (num == maj) {
                count++;
            } else if (num == max) {
                countMax++;
            }
        }
        if (count > nums.length / 3) {
            res.add(maj);
        }
        if (countMax > nums.length / 3) {
            res.add(max);
        }
        return res;
    }

    public static int majorityElement(int[] nums) {
        int count = 1;
        int maj = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if (maj == nums[i])
                count++;
            else {
                count--;
                if (count == 0) {
                    maj = nums[i + 1];
                }
            }
        }
        return maj;
    }
}
