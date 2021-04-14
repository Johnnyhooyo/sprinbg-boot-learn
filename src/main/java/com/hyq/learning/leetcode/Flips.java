package com.hyq.learning.leetcode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * @author：huyuanqiang
 * @time: 2021-02-18 14:17
 * @description:
 * 在仅包含 0 和 1 的数组 A 中，一次 K 位翻转包括选择一个长度为 K 的（连续）子数组，同时将子数组中的每个 0 更改为 1，而每个 1 更改为 0。
 *
 * 返回所需的 K 位翻转的最小次数，以便数组没有值为 0 的元素。如果不可能，返回 -1。
 **/
public class Flips {

    public static void main(String[] args) {
        Flips flips = new Flips();

        int i = flips.minKBitFlips(new int[]{0,0,0,1,0,1,1,0}, 3);
        System.out.println(i);

        int i1 = flips.diffMethod(new int[]{0, 0, 0, 1, 0, 1, 1, 0}, 3);
        System.out.println(i1);
    }

    /**
     * 滑动窗口
     */
    public int windowMethod(int[] A, int K) {
        int res = 0;
        Deque<Integer> que = new LinkedList<>();
        for (int i = 0; i < A.length; i++) {
            if (que.size() > 0 && i > que.peek() + K - 1) {
                que.removeFirst();
            }
            //1.本来是1，翻转奇数次变为0，所以需要再次翻转，放入队列
            //2.本来是0，翻转偶数次还是0，所以需要再次翻转，放入队列
            if (que.size() % 2 == A[i]) {
                if (i + K > A.length) {
                    return -1;
                }
                que.add(i);
                res += 1;
            }
        }
        return res;
    }


    /**
     * 差分
     */
    public int diffMethod(int[] A, int K) {
        int[] diff = new int[A.length + 1];
        int cvCount = 0;
        int res = 0;

        for (int i = 0; i < A.length; i++) {
           cvCount += diff[i];
            if ((cvCount + A[i]) % 2 == 0) {
                if (i + K > A.length) {
                    return -1;
                }

                res++;
                cvCount++;
                diff[i+K]--;

            }
        }
        return res;
    }

    /**
     * 暴力 O(n*k)
     */
    public int minKBitFlips(int[] A, int K) {
        int res = 0;

        if (K > A.length) {
            return -1;
        }

        for (int i = 0; i < A.length - (K - 1); i++) {
            if (A[i] == 0) {
                flipNextK(A, K, i);
                res++;
            }
        }

        for (int i = A.length - 1; i > A.length - K ; i--) {
            if (A[i] == 0) {
                return -1;
            }
        }

        return res;
    }

    private void flipNextK(int[] a, int k, int i) {
        for (int j = i; j < i + k; j++) {
            a[j] = a[j] == 0 ? 1 : 0;
        }
    }

}
