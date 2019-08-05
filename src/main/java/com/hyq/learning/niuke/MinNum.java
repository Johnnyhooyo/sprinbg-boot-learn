package com.hyq.learning.niuke;

import java.util.ArrayList;

/**
 * @ClassName MinNum
 * @Author dibulidohu
 * @Date 2019/7/30 16:07
 * @Description
 */
public class MinNum {

    public static void main(String[] args) {
        MinNum minNum = new MinNum();
        int[] intput = {4,5,1,6,2,7,3,8};
        minNum.GetLeastNumbers_Solution(intput, 4);
    }

    public ArrayList<Integer> GetLeastNumbers_Solution(int [] input, int k) {
        ArrayList<Integer> list = new ArrayList<>();
        if(k > input.length) {
            return list;
        }
        heapSort(input);
        int mark = Integer.MIN_VALUE;
        int count = 0;
        for(int i = 0; i < input.length && count < k; i++) {
            if(input[i] != mark) {
                list.add(input[i]);
                mark = input[i];
                count++;
            }
        }
        return count == k ? list : new ArrayList<>();
    }
    public void heapSort(int[] arr) {
        for(int i = arr.length - 1; i > 0; i--){
            heapBuild(arr, i);
            int temp = arr[0];
            arr[0] = arr[i];
            arr[i] = temp;
        }
    }

    public void heapBuild(int[] arr, int end) {
        for(int i = ((end + 1) / 2 - 1); i >= 0; i--) {
            int left = i * 2 + 1;
            int right = i * 2 + +2;
            if(arr[i] < arr[left]) {
                int temp = arr[i];
                arr[i] = arr[left];
                arr[left] = temp;
            }
            if(right <= end && arr[i] < arr[right]) {
                int temp = arr[i];
                arr[i] = arr[right];
                arr[right] = temp;
            }
        }
    }
}
