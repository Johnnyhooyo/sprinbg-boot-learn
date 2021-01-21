package com.hyq.learning.leetcode.array;

/**
 * @author：huyuanqiang
 * @time: 2020-06-03 17:44
 * @description: 找到为排序数组中没出现的最小正整数
 **/
public class MinArray {

    public static void main(String[] args) {
        MinArray minArray = new MinArray();
        int[] arr = {1,2,5,8,-2,-4,3,4};
        int i = minArray.minArray(arr);
        System.out.println(i);
    }

    public int minArray(int[] arr) {
        if (arr.length == 0) {
            return 1;
        }
        for (int i = 0; i < arr.length; i++) {
            int temp = arr[i];
            while (temp > 0 && temp != i + 1 && temp < arr.length) {
                int t = arr[temp - 1];
                arr[temp - 1] = temp;
                arr[i] = t;
                temp = t;
            }
        }
        for (int i = 0; i < arr.length; i++) {
            if (i+1 != arr[i]) {
                return i + 1;
            }
        }
        return arr.length;
    }
}
