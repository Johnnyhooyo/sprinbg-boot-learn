package com.hyq.learning.leetcode.array;

import com.hyq.learning.leetcode.sort.TopologicalSort;

/**
 * @author：huyuanqiang
 * @time: 2021-02-22 14:51
 * @description:
 * 给你一个 m x n 的矩阵 matrix 。如果这个矩阵是托普利茨矩阵，返回 true ；否则，返回 false 。
 *
 * 如果矩阵上每一条由左上到右下的对角线上的元素都相同，那么这个矩阵是 托普利茨矩阵
 **/
public class ToeplitzMatrix {

    public boolean isToeplitzMatrix(int[][] matrix) {
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[i].length; j++) {
                if (matrix[i][j] != matrix[i-1][j-1]) {
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String[] args) {
        String s = new String("00110");
        ToeplitzMatrix toeplitzMatrix = new ToeplitzMatrix();
        boolean b = toeplitzMatrix.hasAllCodes(s, 2);
        System.out.println(b);
    }

    public boolean hasAllCodes(String s, int k) {
        int[] res = new int[2 << k - 1];
        for(int i = 0; i < s.length() - k + 1; i++) {
            res[str2Num(s.substring(i, i + k))] = 1;
        }

        for(int i = 0; i < res.length; i++) {
            if (res[i] == 0) {
                return false;
            }
        }
        return true;
    }

    public int str2Num(String s) {
        int len = s.length();

        int res = 0;

        for(int i = 0; i < len; i++) {
            res+= (int) s.charAt(i) - 48 << len - i - 1;
        }
        return res;
    }
}
