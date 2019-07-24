package com.hyq.learning.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dibulidohu
 * @classname ChildSet
 * @date 2019/6/610:15
 * @description 给定一组不含重复元素的整数数组 nums，返回该数组所有可能的子集（幂集）。
 */
public class ChildSet {

    public static void main(String[] args) {
        int[] nums = {1,2,3};
        List<List<Integer>> subsets = subsets(nums);
        System.out.println(subsets);
    }


    public static List<List<Integer>> subsets(int[] nums) {
        if(nums.length == 0) {
            List<Integer> ll = new ArrayList<>();
            List<List<Integer>> res = new ArrayList<>();
            res.add(ll);
            return res;
        }
        int[] news = new int[nums.length - 1];
        for(int i = 0; i < news.length; i++) {
            news[i] = nums[i];
        }

        List<List<Integer>> list = subsets(news);
        List<List<Integer>> rr = new ArrayList<>(list);
        for(List<Integer> ints : list) {
            List<Integer> temp = new ArrayList<>(ints);
            temp.add(nums[nums.length -1]);
            rr.add(temp);
        }
        return rr;
    }
}
