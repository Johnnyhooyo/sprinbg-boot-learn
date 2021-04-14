package com.hyq.learning.leetcode.graph;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.concurrent.LinkedBlockingDeque;

/**
 * @author：huyuanqiang
 * @time: 2021-02-18 17:10
 * @description:
 **/
public class ClassStudy2 {

    public static void main(String[] args) {
        int[][] ints = {{0,1},{1,0},{1,2}};
        int[] res = topologicalSort(3, ints);
        System.out.println(res);
    }

    private static int[] topologicalSort(int i, int[][] ints) {
        //寻找是否有入度为0 即没有依赖课程， 可以优先学习的
        Deque<Integer> deque = new LinkedBlockingDeque<>();
        int[] inDegree = new int[i];
        for (int[] anInt : ints) {
            inDegree[anInt[0]]++;
        }
        for (int j = 0; j < inDegree.length; j++) {
            if (inDegree[j] == 0) {
                deque.push(j);
            }
        }

        if (deque.size() == 0) {
            return new int[0];
        }

        HashMap<Integer, List<Integer>> dependencyMap = new HashMap<>();
        for (int[] anInt : ints) {
            List<Integer> list = dependencyMap.computeIfAbsent(anInt[1], k -> new ArrayList<>());
            list.add(anInt[0]);
        }

        int[] res = new int[i];
        int index = 0;

        while (!deque.isEmpty()) {
            Integer poll = deque.pollLast();
            assert poll != null;
            res[index++] = poll;
            List<Integer> list = dependencyMap.get(poll);
            if (CollectionUtils.isEmpty(list)) {
                continue;
            }
            for (Integer integer : list) {
                if (--inDegree[integer] == 0) {
                    deque.push(integer);
                }
            }
        }
        int zero = 0;
        for (int re : res) {
            if (re == 0) {
               zero++;
            }
        }
        return zero > 1 ? new int[0] : res;
    }
}
