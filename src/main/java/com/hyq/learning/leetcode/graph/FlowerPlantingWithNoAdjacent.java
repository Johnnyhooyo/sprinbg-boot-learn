package com.hyq.learning.leetcode.graph;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @author dibulidohu
 * @classname FlowerPlantingWithNoAdjacent
 * @date 2019/6/615:40
 * @description 有 N 个花园，按从 1 到 N 标记。在每个花园中，你打算种下四种花之一。
 *
 * paths[i] = [x, y] 描述了花园 x 到花园 y 的双向路径。
 *
 * 另外，没有花园有 3 条以上的路径可以进入或者离开。
 *
 * 你需要为每个花园选择一种花，使得通过路径相连的任何两个花园中的花的种类互不相同。
 *
 * 以数组形式返回选择的方案作为答案 answer，其中 answer[i] 为在第 (i+1) 个花园中种植的花的种类。花的种类用  1, 2, 3, 4 表示。保证存在答案。
 */
public class FlowerPlantingWithNoAdjacent {

    public static void main(String[] args) {
        int[][] trust = {{1,2},{3,4}};
        int[] ints = gardenNoAdj(4, trust);
        System.out.println(Arrays.toString(ints));
    }

    public static int[] gardenNoAdj(int N, int[][] paths) {
        int result[] = new int[N];

        List<List<Integer>> list = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            list.add(new ArrayList<>());
        }
        for (int[] integer : paths) {
            int min = Math.min(integer[0], integer[1]);
            int max = Math.max(integer[0], integer[1]);
            list.get(max).add(min);
        }
        //上面这一段很巧妙 把所有关联的两个点 大在前 小在后 构建出一个三角的二维数组 节省空间
        //下面的思路就是 从小到大 后面有关联的点 把从1-4中删除后面以有的花 剩下的第一个放入当前位置
        for (int i = 1; i <= N; i++) {
            List<Integer> integerList = list.get(i);
            List<Integer> flower = new ArrayList<>();
            for (int j = 1; j < 5; j++) {
                flower.add(j);
            }
            for (Integer integer : integerList) {
                flower.remove(Integer.valueOf(result[integer - 1]));
            }
            result[i - 1] = flower.get(0);
        }
        return result;
    }
}
