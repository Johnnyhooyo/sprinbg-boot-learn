package com.hyq.learning.leetcode.graph;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * @author：huyuanqiang
 * @time: 2020-04-14 19:35
 * @description:
 **/
public class SetZeros {

    public static void main(String[] args) {
        int[][] board = {{1,1,5,4},
                         {2,0,1,5},
                         {1,1,1,2},
                         {2,3,4,7}};
        setZeroes(board);
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
    }

    public static void setZeroes(int[][] matrix) {
        if (matrix.length == 0) {
            return;
        }
        List<Integer> row = new ArrayList<>();
        List<Integer> line = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] == 0) {
                    row.add(i);
                    line.add(j);
                }
            }
        }
        for (Integer i : row) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = 0;
            }
        }
        for (Integer i : line) {
            for (int j = 0; j < matrix.length; j++) {
                matrix[j][i] = 0;
            }
        }
    }
}
