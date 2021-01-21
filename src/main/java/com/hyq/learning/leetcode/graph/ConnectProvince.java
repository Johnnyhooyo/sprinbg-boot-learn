package com.hyq.learning.leetcode.graph;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * @author：huyuanqiang
 * @time: 2021-01-07 11:26
 * @description: 并查集
 **/
public class ConnectProvince {

    public static void main(String[] args) {
        ConnectProvince connectProvence = new ConnectProvince();
//        int circleNum = connectProvence.findCircleNum(new int[][]{{1, 0, 0, 1}, {0, 1, 1, 1}, {0, 1, 1, 0}, {1, 0, 1, 1}});
        //[[1,1,0],[1,1,0],[0,0,1]]
//        int circleNum = connectProvence.findCircleNum(new int[][]{{1, 1, 0}, {1, 1, 0}, {0, 0, 1}});
        //[[1,0,0,0,0,0,0,0,0,1,0,0,0,0,0],
        // [0,1,0,1,0,0,0,0,0,0,0,0,0,1,0],
        // [0,0,1,0,0,0,0,0,0,0,0,0,0,0,0],
        // [0,1,0,1,0,0,0,1,0,0,0,1,0,0,0],
        // [0,0,0,0,1,0,0,0,0,0,0,0,1,0,0],
        // [0,0,0,0,0,1,0,0,0,0,0,0,0,0,0],
        // [0,0,0,0,0,0,1,0,0,0,0,0,0,0,0],
        // [0,0,0,1,0,0,0,1,1,0,0,0,0,0,0],
        // [0,0,0,0,0,0,0,1,1,0,0,0,0,0,0],
        // [1,0,0,0,0,0,0,0,0,1,0,0,0,0,0],
        // [0,0,0,0,0,0,0,0,0,0,1,0,0,0,0],
        // [0,0,0,1,0,0,0,0,0,0,0,1,0,0,0],
        // [0,0,0,0,1,0,0,0,0,0,0,0,1,0,0],
        // [0,1,0,0,0,0,0,0,0,0,0,0,0,1,0],
        // [0,0,0,0,0,0,0,0,0,0,0,0,0,0,1]]

        int[][] ints = {{1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 1, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                        {0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 1, 0, 1, 0, 0, 0, 1, 0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 1, 1, 0, 0, 0, 0, 0, 0},
                        {1, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0, 0},
                        {0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
                        {0, 0, 0, 0, 1, 0, 0, 0, 0, 0, 0, 0, 1, 0, 0},
                        {0, 1, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1, 0},
                        {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 1}};
        int circleNum = connectProvence.findCircleNum(ints);
        System.out.println(circleNum);
    }

    public int findCircleNum(int[][] isConnected) {
        if (isConnected.length == 0) {
            return 0;
        }
        int[] pa = new int[isConnected.length];
        for (int i = 0; i < pa.length; i++) {
            pa[i] = i;
        }

        int[] rank = new int[isConnected.length];

        Arrays.fill(rank, 1);

        for (int i = 0; i < isConnected.length; i++) {
            for (int j = i+1; j < isConnected.length; j++) {
                if (isConnected[i][j] == 1) {
                    merge(pa, rank, i, j);
                }
            }
        }

        Set<Integer> set = new HashSet<>();
        for (int i = 0; i < pa.length; i++) {
            set.add(findPa(pa, i));
        }

        return set.size();
    }

    private void merge(int[] pa, int[] rank, int i, int j) {
        int ip = findPa(pa, i);
        int jp = findPa(pa, j);
        if (rank[ip] > rank[jp]) {
            System.out.println("把" + jp + "挂在" + ip + "下");
            pa[jp] = pa[ip];
        } else if (rank[jp] > rank[ip]) {
            pa[jp] = pa[ip];
            System.out.println("把" + ip + "挂在" + jp + "下");
        } else {
            System.out.println("把" + jp + "挂在" + ip + "下");
            System.out.println(ip + "的深度增加1");
            pa[jp] = pa[ip];
            rank[ip]++;
        }

     }

    private int findPa(int[] pa, int i) {
        return pa[i] == i ? i : (pa[i] = findPa(pa, pa[i]));
    }
}
