package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2020-12-29 17:35
 * @description: 数组 偶数左边 奇数 右边
 * 原址做
 **/
public class SplitArray {

    public static void main(String[] args) {
        SplitArray splitArray = new SplitArray();
        int[] slit = splitArray.slit(new int[]{1, 3, 2, 5, 4, 2, 3, 5, 6});
        for (int i : slit) {
            System.out.println(i);
        }
    }

    public int[] slit(int [] a) {
        int left = 0;
        int right = a.length - 1;
        int needSwap = -1;
        int actor = 1;
        while (left < right) {
            if (actor == 1) {
                if (a[left] % 2 == 0) {
                    left++;
                } else {
                    actor = 2;
                    if (needSwap == -1) {
                        needSwap = left;
                    } else {
                        int temp = a[needSwap];
                        a[needSwap] = a[left];
                        a[left] = temp;
                        needSwap = -1;
                        left++;
                        right--;
                    }
                }
            } else  {
                if (a[right] % 2 == 1) {
                    right--;
                } else {
                    actor = 1;
                    if (needSwap == -1) {
                        needSwap = right;
                    } else {
                        int temp = a[needSwap];
                        a[needSwap] = a[right];
                        a[right] = temp;
                        needSwap = -1;
                        left++;
                        right--;
                    }
                }
            }
        }
        return a;
    }
}
