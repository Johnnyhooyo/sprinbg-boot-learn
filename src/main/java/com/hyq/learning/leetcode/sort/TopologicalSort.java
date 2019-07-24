package com.hyq.learning.leetcode.sort;

import java.util.*;

/**
 * @author dibulidohu
 * @classname TopologicalSort ---拓扑排序
 * @date 2019/6/1014:13
 * @description
 * 入度：设有向图中有一结点v，其入度即为当前所有从其他结点出发，终点为v的的边的数目。也就是所有指向v的有向边的数目。
 * 出度：设有向图中有一结点v，其出度即为当前所有起点为v，指向其他结点的边的数目。也就是所有由v发出的边的数目。
 * 在了解了入度和出度的概念之后，再根据拓扑排序的定义，我们自然就能够得出结论：要想完成拓扑排序，我们每次都应当从入度为0的结点开始遍历。因为只有入度为0的结点才能够成为拓扑排序的起点。
 * 否则根据拓扑排序的定义，只要一个结点v的入度不为0，则至少有一条边起始于其他结点而指向v，那么这条边的起点在拓扑排序的顺序中应当位于v之前，则v不能成为当前遍历的起点。
 * 由此我们可以进一步得出一个改进的深度优先遍历或广度优先遍历算法来完成拓扑排序。以广度优先遍历为例，这一改进后的算法与普通的广度优先遍历唯一的区别在于我们应当保存每一个结点对应的入度，
 * 并在遍历的每一层选取入度为0的结点开始遍历（而普通的广度优先遍历则无此限制，可以从该吃呢个任意一个结点开始遍历）。这个算法描述如下：
 *
 * 1.初始化一个int[] inDegree保存每一个结点的入度。
 * 2.对于图中的每一个结点的子结点，将其子结点的入度加1。
 * 3.选取入度为0的结点开始遍历，并将该节点加入输出。
 * 4.对于遍历过的每个结点，更新其子结点的入度：将子结点的入度减1。
 * 5.重复步骤3，直到遍历完所有的结点。
 * 6.如果无法遍历完所有的结点，则意味着当前的图不是有向无环图。不存在拓扑排序。
 *
 * 链接：https://www.jianshu.com/p/3347f54a3187
 */
public class TopologicalSort {

    /**
     * num:节点个数
     * adjacencyList：邻接表示法    --图的表示法 https://blog.csdn.net/woaidapaopao/article/details/51732947
     * DFS
     */
    public List<Integer> topologicalSortDFS(int num, int[][] adjacencyList) {
        //找到入度为0的节点
        int[] indegree = new int[num];
        for (int[] ints : adjacencyList) {
            for (int anInt : ints) {
                indegree[anInt]++;
            }
        }
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < num; i++) {
            if (indegree[i] == 0) {
                list.add(i);
            }
        }
        if (list.size() == 0) {
            return new ArrayList<>();
        }
        boolean[] visited = new boolean[adjacencyList.length];
        List<Integer> topoRes = new ArrayList<>();
        for (Integer integer : list) {
            dfs(integer, adjacencyList, topoRes, visited);
        }
        return topoRes.size() == num ? topoRes : new ArrayList<>();
    }

    private void dfs(int start, int[][] adjacencyList, List<Integer> topoRes, boolean[] visited) {
        if (visited[start]) {
            return;
        }
        visited[start] = true;
        for (int index : adjacencyList[start]) {
            dfs(index, adjacencyList, topoRes, visited);
        }
        topoRes.add(start);
    }

    /**
     * num:节点个数
     * adjacencyList：邻接表示法    --图的表示法 https://blog.csdn.net/woaidapaopao/article/details/51732947
     * BFS
     */
    public List<Integer> topologicalSort(int num, int[][] adjacencyList) {
        List<Integer> topoRes = new ArrayList<>();
        int[] indegree = new int[num];
        for (int[] ints : adjacencyList) {
            for (int anInt : ints) {
                indegree[anInt]++;
            }
        }
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = 0; i < num; i++) {
            if (indegree[i] == 0) {
                deque.offer(i);
            }
        }
        while (!deque.isEmpty()) {
            Integer poll = deque.poll();
            topoRes.add(poll);
            for (int index : adjacencyList[poll]) {
                indegree[index]--;
                if (indegree[index] == 0) {
                    deque.offer(index);
                }
            }
        }
        return topoRes.size() == num ? topoRes : new ArrayList<>();
    }
}
