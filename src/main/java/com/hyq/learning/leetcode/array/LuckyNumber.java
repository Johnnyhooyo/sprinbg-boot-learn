//package com.hyq.learning.leetcode.array;
//
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * @author：huyuanqiang
// * @time: 2020-07-01 20:29
// * @description:
// **/
//public class LuckyNumber {
//
//    public static void main(String[] args) {
//        LuckyNumber luckyNumber = new LuckyNumber();
//        List<Integer> list = luckyNumber.luckyNumbers(new int[][]{
//                {1, 2, 3, 4},
//                {4, 2, 3, 5},
//                {6, 1, 6, 2}});
//        for (Integer integer : list) {
//            System.out.println(integer);
//        }
//    }
//
//    public List<Integer> ls(int[][] matrix) {
//        List<Integer> res = new ArrayList();
//        if (matrix.length == 0) {
//            return res;
//        }
//        int[] maxLine = new int[matrix.length];
//        int[] minRow = new int[matrix[0].length];
//
//    }
//
//    public List<Integer> luckyNumbers (int[][] matrix) {
//        List<Integer> res = new ArrayList();
//        for (int i = 0; i < matrix.length; i++) {
//            for (int j = 0; j < matrix[i].length; j++) {
//                if (isMinInRow(i, j, matrix)) {
//                    if (isMaxInLine(i, j, matrix)) {
//                        res.add(matrix[i][j]);
//                    }
//                    break;
//                }
//            }
//        }
//        return res;
//    }
//
//    private boolean isMaxInLine(int i, int j, int[][] matrix) {
//        for (int k = j == 0 ? 1 : 0; k < matrix.length; k++) {
//            if (k == j) {
//                continue;
//            }
//            if (matrix[k][j] > matrix[i][j]) {
//                return false;
//            }
//        }
//        return true;
//    }
//
//    private boolean isMinInRow(int i, int j, int[][] matrix) {
//        int[] ints = matrix[i];
//        for (int k = j + 1; k < ints.length; k++) {
//            if (ints[k] < ints[j]) {
//                return false;
//            }
//        }
//        return true;
//    }
//}
