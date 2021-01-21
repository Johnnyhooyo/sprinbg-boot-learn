package com.hyq.learning;

import java.util.Arrays;

/**
 * @author：huyuanqiang
 * @time: 2021-01-20 14:16
 * @description:
 **/
public class TransposeMatrix {


    public static void main(String[] args) {
        TransposeMatrix transposeMatrix = new TransposeMatrix();
        int[][] transpose = transposeMatrix.transpose(new int[][]{{1, 2, 3, 4,}, {2, 3, 4, 5}, {8, 9, 10, 22}});
        System.out.println(Arrays.deepToString(transpose));
    }

    public int[][] transpose(int[][] A) {
        int[][] ints = new int[A[0].length][A.length];
        for (int i = 0; i < A.length; i++) {
            for (int j = 0; j < A[i].length; j++) {
                ints[j][i] = A[i][j];;
            }
        }
        return ints;
    }
}
