package com.hyq.learning.leetcode.graph;

/**
 * @author dibulidohu
 * @classname BalanceGraph
 * @date 2019/6/1020:12
 * @description 给定一个无向图graph，当这个图为二分图时返回true。
 *
 * 如果我们能将一个图的节点集合分割成两个独立的子集A和B，并使图中的每一条边的两个节点一个来自A集合，一个来自B集合，我们就将这个图称为二分图。
 */
public class BalanceGraph {

    public boolean isBipartite(int[][] graph) {
        int[] colors = new int[graph.length];
        for(int i = 0; i < graph.length; i++) {
            if(colors[i] == 0) {
                colors[i] = 1;
            }
            for(int j = 0; j < graph[i].length; j++) {
                if(colors[graph[i][j]] == 0) {
                    colors[graph[i][j]] = (colors[i] == 1 ? 2 : 1);
                } else if(colors[graph[i][j]] == colors[i]) {
                    return false;
                }
            }
        }
        return true;
    }
}
