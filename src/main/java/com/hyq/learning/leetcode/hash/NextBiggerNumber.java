package com.hyq.learning.leetcode.hash;

import java.util.Arrays;
import java.util.Stack;

/**
 * @author：huyuanqiang
 * @time: 2020-06-11 17:16
 * @description:
 **/
public class NextBiggerNumber {

    public static void main(String[] args) {
        NextBiggerNumber nextBiggerNumber = new NextBiggerNumber();
        int[] ints = nextBiggerNumber.dailyTemperatures(new int[]{73, 74, 75, 71, 69, 72, 76, 73});
        System.out.println(Arrays.toString(ints));
    }

    /**
     * 请根据每日 气温 列表，重新生成一个列表。对应位置的输出为：要想观测到更高的气温，至少需要等待的天数。如果气温在这之后都不会升高，请在该位置用 0 来代替。
     *
     * 例如，给定一个列表 temperatures = [73, 74, 75, 71, 69, 72, 76, 73]，你的输出应该是 [1, 1, 4, 2, 1, 1, 0, 0]。
     *
     * 提示：气温 列表长度的范围是 [1, 30000]。每个气温的值的均为华氏度，都是在 [30, 100] 范围内的整数。
     *
     * tip:    栈 哈希
     */
    public int[] dailyTemperatures(int[] temp) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[temp.length];
        for (int j = 0; j < temp.length; j++) {
            while (!stack.isEmpty() && temp[stack.peek()] < temp[j]) {
                Integer pop = stack.pop();
                res[pop] = j - pop;
            }
            stack.push(j);
        }
        return res;
    }

    /**
     * 给定两个 没有重复元素 的数组 nums1 和 nums2 ，其中nums1 是 nums2 的子集。找到 nums1 中每个元素在 nums2 中的下一个比其大的值。
     *
     * nums1 中数字 x 的下一个更大元素是指 x 在 nums2 中对应位置的右边的第一个比 x 大的元素。如果不存在，对应位置输出 -1 。
     */
    public int[] nextGreaterElement(int[] nums1, int[] nums2) {
        Stack<Integer> stack = new Stack<>();
        int[] res = new int[nums2.length];
        for (int j = 1; j < nums2.length; j++) {
            while (!stack.isEmpty() && nums1[stack.peek()] < nums2[j]) {
                Integer pop = stack.pop();
                res[pop] = j - pop;
            }
            stack.push(j);
        }
        return res;
    }
}
