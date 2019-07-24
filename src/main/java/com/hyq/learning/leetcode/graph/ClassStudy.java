package com.hyq.learning.leetcode.graph;

import java.util.*;

/**
 * @author dibulidohu
 * @classname ClassStudy
 * @date 2019/6/1015:40
 * @description 现在你总共有 n 门课需要选，记为 0 到 n-1。
 *
 * 在选修某些课程之前需要一些先修课程。 例如，想要学习课程 0 ，你需要先完成课程 1 ，我们用一个匹配来表示他们: [0,1]
 *
 * 给定课程总量以及它们的先决条件，判断是否可能完成所有课程的学习？
 */
public class ClassStudy {

    public static void main(String[] args) {
        int[][] ints = {{0,1},{1,0},{1,2}};
        boolean b = canFinish(3, ints);
        System.out.println(b);
    }

    /**
     * numCourses:课程总数
     * prerequisites：边缘列表形式
     */
    public static boolean canFinish(int numCourses, int[][] prerequisites) {
        //吧边缘列表转为邻接列表
        HashMap<Integer, List<Integer>> requisites = new HashMap<>();
        for(int[] ints : prerequisites) {
            List<Integer> list = requisites.computeIfAbsent(ints[1], k -> new ArrayList<>());
            list.add(ints[0]);
        }
        //找到入度为0的节点
        int[] indegree = new int[numCourses];
        for (List<Integer> list : requisites.values()) {
            for (Integer integer : list) {
                indegree[integer]++;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                list.add(i);
            }
        }
        if (list.size() == 0) {
            return false;
        }
        boolean[] visited = new boolean[numCourses];
        boolean[] res = new boolean[1];
        res[0] = true;
        List<Integer> topoRes = new ArrayList<>();
        Stack<Integer> path = new Stack<>();
        for (Integer integer : list) {
            dfs(integer, requisites, topoRes, visited, path, res);
        }
        return res[0] && topoRes.size() == numCourses;
    }

    private static void dfs(int start, HashMap<Integer, List<Integer>> requisites, List<Integer> topoRes, boolean[] visited, Stack<Integer> path, boolean[] res) {
        if(res[0]){
            if (visited[start] && path.contains(start)) {
                res[0] = false;
                return;
            } else if (visited[start]) {
                return;
            }
            path.push(start);
            visited[start] = true;
            List<Integer> list = requisites.get(start);
            if (null != list) {
                for (Integer integer : list) {
                    dfs(integer, requisites, topoRes, visited, path, res);
                }
            }
            path.pop();
            topoRes.add(start);
        }
    }
}
