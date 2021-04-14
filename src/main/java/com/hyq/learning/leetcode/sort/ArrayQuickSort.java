package com.hyq.learning.leetcode.sort;

/**
 * @author dibulidohu
 * @classname ArrayQuickSort
 * @date 2019/5/917:50
 * @description
 */
public class ArrayQuickSort {

    public static void main(String[] args) {
        int [] arr = {3,6,2,5,7,7,9,3,2,7,9,45,34,56,31,9};
        quickSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }

        int[] res = {1,3,2,5,4};
        isContinuous(res);
    }

    private static void quickSort(int[] arr, int m, int n) {
        if (m < n) {
            int point = arr[n];
            int p1 = m;
            for (int p2 = m; p2 <= n; p2++) {
                if (arr[p2] < point) {
                    int temp = arr[p1];
                    arr[p1] = arr[p2];
                    arr[p2] = temp;
                    p1++;
                }
            }
            arr[n] = arr[p1];
            arr[p1] = point;
            quickSort(arr, m, p1 - 1);
            quickSort(arr, p1 + 1, n);
        }
    }

    public static boolean isContinuous(int [] numbers) {
        if(numbers.length > 17 || numbers.length < 1) {
            return false;
        }
        quickSort(numbers, 0, numbers.length - 1);
        int i = 0;
        while(numbers[i] == 0) {
            i++;
        }
        int zero = i;
        int num = 0;
        for(; i < numbers.length; i++) {
            if(num == 0) {
                num = numbers[i];
            } else {
                num++;
                while(numbers[i] != num) {
                    num++;
                    zero--;
                    if(zero < 0) {
                        break;
                    }
                }
            }
        }
        return zero >= 0;
    }
}
