package com.hyq.learning.leetcode.graph;

/**
 * @author：huyuanqiang
 * @time: 2020-04-14 14:03
 * @description: 地上有一个m行n列的方格，从坐标 [0,0] 到坐标 [m-1,n-1] 。一个机器人从坐标 [0, 0] 的格子开始移动，
 * 它每次可以向左、右、上、下移动一格（不能移动到方格外），也不能进入行坐标和列坐标的数位之和大于k的格子。例如，当k为18时，
 * 机器人能够进入方格 [35, 37] ，因为3+5+3+7=18。但它不能进入方格 [35, 38]，因为3+5+3+8=19。请问该机器人能够到达多少个格子？
 *
 * 38
 * 15
 * 9
 **/
public class MovingRobot {

    public static void main(String[] args) {
        System.out.println(movingCount(38, 15, 9));

    }

    public static int movingCount(int m, int n, int k) {
        int count = 0;
        int[][] board = new int[m][n];
        work(board, k, 0, 0);
        for (int[] ints : board) {
            for (int anInt : ints) {
                if (anInt == 1) {
                    count++;
                }
            }
        }
        for (int[] ints : board) {
            for (int anInt : ints) {
                System.out.print(anInt + " ");
            }
            System.out.println();
        }
        return count;
    }

    private static void work(int[][] board, int k, int i, int j) {
        if (i >= board.length || j >= board[i].length || board[i][j] == 1 ) {
            return;
        }
        int num = 0;
        int n = i;
        int m = j;
        while (i > 0) {
            num += (i % 10);
            i = i / 10;
        }
        while (j > 0) {
            num += (j % 10);
            j = j / 10;
        }
        if (num <= k) {
            board[n][m] = 1;
        } else {
            return;
        }
        work(board, k, n+1, m);
        work(board, k, n, m+1);
    }
}
