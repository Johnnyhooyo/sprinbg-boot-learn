package com.hyq.learning.leetcode.sort;

/**
 * @author dibulidohu
 * @classname MergeSort
 * @date 2019/5/919:11
 * @description   核心思想：分治。
 */
public class MergeSort {

    public static void main(String[] args) {
        int [] arr = {3,6,2,5,7,7,9,3,2,7,9,45,34,56,31,9};
        int [] res = new int[arr.length];
        mergeSort(arr, 0, arr.length - 1, res);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private static void mergeSort(int[] arr, int left, int right, int[] res) {
        int mid = (left + right) / 2;
        if (left < right) {
            mergeSort(arr, left, mid, res);
            mergeSort(arr, mid + 1, right, res);
            int i = left, j = mid + 1;
            int k = 0;
            while (i <= mid && j <= right) {
                if (arr[i] > arr[j]) {
                    res[k++] = arr[j++];
                } else {
                    res[k++] = arr[i++];
                }
            }
            while (i <= mid) {
                res[k++] = arr[i++];
            }
            while (j <= right) {
                res[k++] = arr[j++];
            }
            for (int index = 0 ; index < k; index++) {
                arr[left+index] = res[index];
            }
        }
    }
}
