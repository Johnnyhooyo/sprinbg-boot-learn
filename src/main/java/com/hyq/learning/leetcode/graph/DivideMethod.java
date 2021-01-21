package com.hyq.learning.leetcode.graph;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @author：huyuanqiang
 * @time: 2021-01-06 16:08
 * @description:
 **/
public class DivideMethod {

    public static void main(String[] args) {
        DivideMethod divideMethod = new DivideMethod();
//[["a","b"],["b","c"],["a","c"],["d","e"]]
//[2.0,3.0,6.0,1.0]
//[["a","c"],["b","c"],["a","e"],["a","a"],["x","x"],["a","d"]]

        List<List<String>> arrayLists = Lists.newArrayList(Lists.newArrayList("a", "b"), Lists.newArrayList("b", "c"),
                Lists.newArrayList("a", "c"), Lists.newArrayList("d", "e"));
        double[] d = new double[]{2.0,3.0,6.0,1.0};
        List<List<String>> q = Lists.newArrayList(Lists.newArrayList("a", "c"), Lists.newArrayList("b", "c"),
                Lists.newArrayList("a", "e"), Lists.newArrayList("a", "a"),Lists.newArrayList("x", "x")
                ,Lists.newArrayList("a", "d"));
        double[] doubles = divideMethod.calcEquation(arrayLists, d, q);
        for (double aDouble : doubles) {
            System.out.println(aDouble);
        }
    }

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        HashMap<String, GraphNode> nodeMap = new HashMap<>(16);
        buildGraph(equations, nodeMap);

        HashMap<String, Double> equationsAndValue = new HashMap<>();
        int i = 0;
        for (List<String> equation : equations) {
            equationsAndValue.put(equation.get(1) + "/" + equation.get(0), 1/values[i]);
            equationsAndValue.put(equation.get(0) + "/" + equation.get(1), values[i++]);
        }

        System.out.println("equation:" + equationsAndValue);

        double[] res = new double[queries.size()];
        int j = 0;
        for (List<String> query : queries) {
            if (query.get(0).equals(query.get(1))) {
                if (nodeMap.containsKey(query.get(0))) {
                    res[j++] = 1.0;
                } else {
                    res[j++] = -1.0;
                }
                continue;
            }
            List<String> path = findPath(nodeMap, query);
            System.out.println(query + "path:" + path);
            if (path.size() == 0) {
                res[j++] = -1.0;
            } else {
                res[j++] = cal(path, equationsAndValue);
            }
        }

        return res;
    }

    private double cal(List<String> path, HashMap<String, Double> equationsAndValue) {
        double res = 1;
        for (String s : path) {
            res *= equationsAndValue.get(s);
        }
        return res;
    }

    private List<String> findPath(HashMap<String, GraphNode> nodeMap, List<String> queries) {
        String start = queries.get(0);
        String end = queries.get(1);

        if (!nodeMap.containsKey(start) || !nodeMap.containsKey(end)) {
            return  new ArrayList<>();
        }

        List<String> pass = new ArrayList<>();
        pass.add(start);
        return dfs(nodeMap.get(start), end, pass);
    }

    private List<String> dfs(GraphNode graphNode, String end, List<String> pass) {
        List<String> res = new ArrayList<>();

        for (GraphNode relation : graphNode.getRelations()) {
            if (pass.contains(relation.getStr())) {
                continue;
            }
            if (end.equals(relation.getStr())){
                res.add(graphNode.str + "/" + relation.getStr());
                return res;
            }
            pass.add(graphNode.str);
            List<String> dfs = dfs(relation, end, pass);
            if (dfs.size() > 0) {
                res.add(graphNode.str + "/" + relation.getStr());
                res.addAll(dfs);
                return res;
            }
        }

        return res;
    }

    private void buildGraph(List<List<String>> equations, HashMap<String, GraphNode> nodeMap) {
        for (List<String> equation : equations) {
            GraphNode left ;
            if (nodeMap.containsKey(equation.get(0))) {
                left = nodeMap.get(equation.get(0));
            } else {
                left = new GraphNode(equation.get(0));
            }

            GraphNode right ;
            if (nodeMap.containsKey(equation.get(1))) {
                right = nodeMap.get(equation.get(1));
            } else {
                right = new GraphNode(equation.get(1));
            }

            left.putRelation(right);
            right.putRelation(left);

            nodeMap.put(left.getStr(), left);
            nodeMap.put(right.getStr(), right);
        }
    }


    class GraphNode {
        private String str;
        private List<GraphNode> relations;

        public GraphNode(String str) {
            this.str = str;
        }

        public List<GraphNode> getRelations() {
            return relations;
        }

        public String getStr() {
            return str;
        }

        public void putRelation(GraphNode graphNode) {
            if (null == relations) {
                relations = new ArrayList<>();
            }
            if (relations.stream().noneMatch(o -> o.getStr().equals(graphNode.getStr()))) {
                relations.add(graphNode);
            }
        }
    }
}
