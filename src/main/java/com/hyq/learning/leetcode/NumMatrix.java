package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2021-03-02 14:33
 * @description:
 *
 * ["NumMatrix","sumRegion","sumRegion","sumRegion"]
 * [[[[3,0,1,4,2],[5,6,3,2,1],[1,2,0,1,5],[4,1,0,1,7],[1,0,3,0,5]]],[2,1,4,3],[1,1,2,2],[1,2,2,4]]
 **/
public class NumMatrix {

    public static void main(String[] args) {
        int[][] arr = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix numMatrix = new NumMatrix(arr);
        numMatrix.sumRegion(2,1,4,3);
    }
    private int[][] sumMatrix;

    public NumMatrix(int[][] matrix) {
        if(matrix.length == 0) {
            return;
        }
        sumMatrix = new int[matrix.length][matrix[0].length];
        sumMatrix[0][0] = matrix[0][0];
        for(int i = 1; i < matrix.length; i++) {
            sumMatrix[i][0] = sumMatrix[i - 1][0] + matrix[i][0];
        }

        for(int i = 1; i < matrix[0].length; i++) {
            sumMatrix[0][i] = sumMatrix[0][i - 1] + matrix[0][i];
        }

        for(int i = 1; i < matrix.length; i++) {
            for(int j = 1; j < matrix[i].length; j++) {
                sumMatrix[i][j] = sumMatrix[i-1][j] + sumMatrix[i][j-1] - sumMatrix[i-1][j-1] + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        if (null == sumMatrix) {
            return 0;
        }
        if(row1 == 0 && col1 == 0) {
            return sumMatrix[row2][col2];
        } else if (row1 == 0) {
            return sumMatrix[row2][col2] - sumMatrix[row2][col1 - 1];
        } else if (col1 == 0) {
            return sumMatrix[row2][col2] - sumMatrix[row1 - 1][col2];
        } else {
            return sumMatrix[row2][col2] - sumMatrix[row1 - 1][col2]  - sumMatrix[row2][col1 - 1] + sumMatrix[row1 - 1][col1 - 1];
        }
    }

}
