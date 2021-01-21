package com.hyq.learning.leetcode.graph;

/**
 * @author：huyuanqiang
 * @time: 2020-04-14 14:51
 * @description: 1 - alive 0 - dead -1 - resurrection 2 - die
 *
 * 0,-1 dead  1,2 alive
 **/
public class LifeGame {

    public static void main(String[] args) {
        int[][] board = {{0,1,0},
                         {0,0,1},
                         {1,1,1},
                         {0,0,0}};
        gameOfLife(board);
    }

    public static void gameOfLife(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                int count = count(board, i , j);
                if (board[i][j] == 0 && count == 3) {
                    board[i][j] = -1;
                } else if (board[i][j] == 1 && count > 3) {
                    board[i][j] = 2;
                } else if (board[i][j] == 1 && count < 2) {
                    board[i][j] = 2;
                }
            }
        }
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j] == -1) {
                    board[i][j] = 1;
                } else if (board[i][j] == 2) {
                    board[i][j] = 0;
                }
            }
        }
    }

    private static int count(int[][] board, int i, int j) {
        int count = 0;
        for (int k = -1; k < 2; k++) {
            for (int l = -1; l < 2; l++) {
                int i1 = i + k;
                int i2 = j + l;
                if (i1 >= 0 && i1 < board.length
                        && i2 >= 0 && i2 < board[i1].length) {
                    if (i1 == i && i2 == j) {
                        continue;
                    }
                    if (board[i1][i2] == 1 || board[i1][i2] == 2) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

}
