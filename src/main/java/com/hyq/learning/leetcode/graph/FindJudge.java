package com.hyq.learning.leetcode.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dibulidohu
 * @classname FindJudge
 * @date 2019/6/614:15
 * @description
 */
public class FindJudge {

    public static void main(String[] args) {
        int[][] trust = {{1,3},{1,2},{2,3},{4,3}};
        int judges = findJudges(4, trust);
        System.out.println(judges);
    }

    public static int findJudges(int N, int[][] trust) {
        int[][] grap = new int[N][2];
        for(int[] tu : trust) {
            grap[tu[0] - 1][0]++;
            grap[tu[1] - 1][1]++;
        }
        int num = 0;
        for(int[] pp : grap) {
            num++;
            if(pp[0] == 0 && pp[1] == N -1){
                return num;
            }
        }
        return -1;
    }

    public int findJudge(int N, int[][] trust) {
        if(N < 1) {
            return -1;
        }
        List<Integer> judges = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            judges.add(i);
        }
        for(int i = 0; i< trust.length; i++) {
            judges.remove(Integer.valueOf(trust[i][0]));
        }
        if (judges.size() != 1) {
            return -1;
        }
        int count= 0;
        for(int i = 0; i< trust.length; i++) {
            if (trust[i][1] == judges.get(0)) {
                count++;
            }
        }
        return count == N -1 ? judges.get(0) : -1;
    }
}
