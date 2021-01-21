package com.hyq.learning.leetcode;


/**
 * @author：huyuanqiang
 * @time: 2020-12-30 10:19
 * @description:
 * 有一堆石头，每块石头的重量都是正整数。
 *
 * 每一回合，从中选出两块 最重的 石头，然后将它们一起粉碎。假设石头的重量分别为 x 和 y，且 x <= y。那么粉碎的可能结果如下：
 *
 * 如果 x == y，那么两块石头都会被完全粉碎；
 * 如果 x != y，那么重量为 x 的石头将会完全粉碎，而重量为 y 的石头新重量为 y-x。
 * 最后，最多只会剩下一块石头。返回此石头的重量。如果没有石头剩下，就返回 0。
 *
 * 示例：
 *
 * 输入：[2,7,4,1,8,1]
 * 输出：1
 * 解释：
 * 先选出 7 和 8，得到 1，所以数组转换为 [2,4,1,1,1]，
 * 再选出 2 和 4，得到 2，所以数组转换为 [2,1,1,1]，
 * 接着是 2 和 1，得到 1，所以数组转换为 [1,1,1]，
 * 最后选出 1 和 1，得到 0，最终数组转换为 [1]，这就是最后剩下那块石头的重量。
 **/
public class WeightOfStone {
    public static void main(String[] args) {
        WeightOfStone weightOfStone = new WeightOfStone();

        int weight = weightOfStone.weight(new int[]{2, 7, 4, 1, 20, 1});
        System.out.println(weight);
    }

    public int weight(int [] a) {
        if (a.length == 1) {
            return a[0];
        }
        for (int i = 0; i < a.length; i++) {
            heapSort(a);
            if (a[0] == a[1]) {
                a[0] = 0;
                a[1] = 0;
            }  else if (a[0] > a[1]) {
                a[0] = a[0] - a[1];
                a[1] = 0;
            }
        }
        return a[0];
    }

    private void heapSort(int[] a) {
        for (int i = a.length - 1; i >= 0; i--) {
            buildHeap(a, i);
            swap(a, 0, i);
        }
    }

    //小堆 把大的数往上  大堆 把小的数往上
    private void buildHeap(int[] a, int index) {
        for (int i = index / 2; i >= 0; i--) {
            if (2*i + 1 <= index && a[i] > a[2*i + 1]) {
                swap(a, i, 2*i + 1);
            }
            if (2*i + 2 <= index && a[i] > a[2*i + 2]) {
                swap(a, i, 2*i + 2);
            }
        }
    }

    private void swap(int[] a, int i, int i1) {
        int temp = a[i];
        a[i] = a[i1];
        a[i1] = temp;
    }
}
