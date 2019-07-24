package com.hyq.learning.leetcode.graph;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dibulidohu
 * @classname AllPathTwo
 * @date 2019/6/1213:17
 * @description 给定一个可包含重复数字的序列，返回所有不重复的全排列。
 */
public class AllPathTwo {

    public static void main(String[] args) {
        int[] arr = {2,7,3};
        StopWatch stopWatch = new StopWatch("method");
        stopWatch.start("1");
        List<List<Integer>> lists = permuteUnique(arr);
        stopWatch.stop();
        System.out.println(stopWatch);
        System.out.println(lists);
    }
    private static List<List<Integer>> res = new ArrayList<>();
    private static int[] used;

    public static List<List<Integer>> permuteUnique(int[] nums) {
        if (nums.length > 0) {
            //Arrays.sort(nums);
            used = new int[nums.length];
            List<Integer> list = new ArrayList<>();
            find(nums, list);
        }
        return res;
    }

    public static void find(int[] nums, List<Integer> list) {
        if(list.size() == nums.length){
            res.add(new ArrayList<>(list));
            return;
        }
        for(int i = 0; i < nums.length; i++){
            if(used[i] == 0){
                used[i] = 1;
                list.add(nums[i]);
                find(nums, list);
                used[i] = 0;
                list.remove(list.size() - 1);
            }
        }
    }
}
