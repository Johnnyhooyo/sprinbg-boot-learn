package com.hyq.learning.leetcode.graph;

import org.springframework.util.StopWatch;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dibulidohu
 * @classname SquareNumber
 * @date 2019/6/119:47
 * @description 给定一个非负整数数组 A，如果该数组每对相邻元素之和是一个完全平方数，则称这一数组为正方形数组。
 *
 * 返回 A 的正方形排列的数目。两个排列 A1 和 A2 不同的充要条件是存在某个索引 i，使得 A1[i] != A2[i]。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/number-of-squareful-arrays
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class SquareNumber {

    public static void main(String[] args) {
        int[] arr = {2,7,2};
        StopWatch stopWatch = new StopWatch("method");
        stopWatch.start("1");
        int i = numSquarefulPerms(arr);
        stopWatch.stop();
        System.out.println(stopWatch);
        System.out.println(i);
        System.out.println(res);
    }

    private static List<List<Integer>> res = new ArrayList<>();

    public static int numSquarefulPerms(int[] A) {
        for(int i = 0; i < A.length; i++) {
            List<Integer> first = new ArrayList<>();
            for (List<Integer> list : res) {
                first.add(list.get(0));
            }
            if (res.size() == 0 || !first.contains(A[i])) {
                List<Integer> lists = new ArrayList<>();
                lists.add(i);
                find(lists, A);
            }
        }
        return res.size();
    }

    public static void find(List<Integer> nums, int[] arr) {
        if (nums.size() == arr.length) {
            List<Integer> list = new ArrayList<>();
            for (Integer integer : nums) {
                list.add(arr[integer]);
            }
            if (!res.contains(list)) {
                res.add(list);
            }
            return;
        }
        int size = nums.size();
        Integer last = nums.get(size - 1);
        //temp用来剪掉重复的值
        List<Integer> temp = new ArrayList<>();
        for(int i = 0; i < arr.length; i++) {
            if (!nums.contains(i) && isSquare(arr[i] + arr[last]) && !temp.contains(arr[i])) {
                temp.add(arr[i]);
                List<Integer> lists = new ArrayList<>(nums);
                lists.add(i);
                find(lists, arr);
            }
        }
    }

    public static boolean isSquare(int num) {
        double sqrt = Math.sqrt(num);
        return sqrt - (int)sqrt == 0;
    }
 }
