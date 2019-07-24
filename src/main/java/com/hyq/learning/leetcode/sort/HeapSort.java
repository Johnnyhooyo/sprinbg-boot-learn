package com.hyq.learning.leetcode.sort;

/**
 * @author dibulidohu
 * @classname HeapSort
 * @date 2019/6/59:51
 * @description  堆排序
 * 一个节点的子节点是 2*i+1 2*i+2 但有可能超出范围
 * 从最后一个位置开始往前走 每调整一次把最前面的数交换到最后，然后再调整0到size-i的位置
 * 每次把最 大 的数往上冒泡 最后是 -小堆
 * 每次把最 小 的数往上冒泡 最后是 -大堆
 *
 * 堆排序的插入可以参考优先级队列的插入  PriorityQueue
 */
public class HeapSort {

    public static void main(String[] args) {
        int[] a = {4,1,6,8,4,9,3,8,5,5};
        sort(a);
    }

    public static void heapBuild(int[] arr, int size) {
        for (int i = size / 2 - 1; i >= 0; i--) {
            if (arr[i] < arr[2 * i + 1]) {
                int temp = arr[i];
                arr[i] = arr[2 * i + 1];
                arr[2 * i + 1] = temp;
            }
            if (2 * i + 2 < size && arr[i] < arr[2 * i + 2]) {
                int temp = arr[i];
                arr[i] = arr[2 * i + 2];
                arr[2 * i + 2] = temp;
            }
        }
    }

    public static void sort(int[] arr) {
        for (int i = arr.length; i >= 1; i--) {
            heapBuild(arr, i);
            int temp = arr[i - 1];
            arr[i - 1] = arr[0];
            arr[0] = temp;
        }
    }
}
