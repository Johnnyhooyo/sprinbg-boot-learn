package com.hyq.learning.leetcode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author：huyuanqiang
 * @time: 2020-12-31 15:00
 * @description:
 * 给定一个区间的集合，找到需要移除区间的最小数量，使剩余区间互不重叠。
 *
 * 注意:
 *
 * 可以认为区间的终点总是大于它的起点。
 * 区间 [1,2] 和 [2,3] 的边界相互“接触”，但没有相互重叠。
 * 示例 1:
 *
 * 输入: [ [1,2], [2,3], [3,4], [1,3] ]
 *
 * 输出: 1
 *
 * 解释: 移除 [1,3] 后，剩下的区间没有重叠。
 *
 * 可以排序start 也可以排序end  start倒叙 end顺序
 **/
public class DistinctArray {

    public static void main(String[] args) {
        DistinctArray distinctArray = new DistinctArray();
        int i = distinctArray.eraseOverlapIntervals(new int[][]{{1, 2}, {3, 5}, {1, 7}, {4, 5}});
        System.out.println(i);
    }


    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length <= 1 ) {
            return 0;
        }
        List<int[]> ints = Arrays.asList(intervals);

        ints.sort((o1, o2) -> o1[0] - o2[0] == 0 ? o2[1] - o1[1] : o2[0] - o1[0]);

        int max = ints.get(0)[0];
        int res = 1;
        for (int i = 1; i < ints.size(); i++) {
            int[] ints1 = ints.get(i);
            if (ints1[1] <= max) {
                res++;
                max = ints1[0];
            }
        }
        return intervals.length - res;
    }


    public List<List<Integer>> largeGroupPositions(String s) {
        char ch = '1';
        int count = 1;
        List<List<Integer>> ints = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            if (s.charAt(i) == ch) {
                count++;
            } else {
                if (count >= 3) {
                    ArrayList<Integer> list = new ArrayList<>();
                    list.add(i-count);
                    list.add(i-1);
                    ints.add(list);
                }
                ch = s.charAt(i);
                count = 1;
            }
        }
        if (count >= 3) {
            ArrayList<Integer> list = new ArrayList<>();
            list.add(s.length()-count);
            list.add(s.length()-1);
            ints.add(list);
        }
        return ints;
    }

}
