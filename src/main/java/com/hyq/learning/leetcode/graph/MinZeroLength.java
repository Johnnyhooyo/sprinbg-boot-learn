package com.hyq.learning.leetcode.graph;

import java.util.Stack;

/**
 * @author：huyuanqiang
 * @time: 2020-04-15 14:14
 * @description:  todo 待做
 **/
public class MinZeroLength {

    public int[][] updateMatrix(int[][] matrix) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    continue;
                }
                matrix[i][j] = length(matrix, i, j);
            }
        }
        return matrix;
    }

    private int length(int[][] matrix, int i, int j) {
        Stack<int[]> stack = new Stack<>();
        return 0;
    }
}
