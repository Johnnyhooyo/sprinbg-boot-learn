package com.hyq.learning.leetcode;

import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName GeneratorParentheses
 * @Author dibulidohu
 * @Date 2019/6/19 14:21
 * @Description 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 */
public class GeneratorParentheses {

    public static void main(String[] args) {
        List<String> strings = generateParenthesis(3);
        for (String string : strings) {
            System.out.println(string);
        }
    }

    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        bfs("(", n - 1, n, res);
        return res;
    }

    private static void bfs(String temp, int left, int right, List<String> res) {
        if (left == 0 && right == 0) {
            res.add(temp);
            return;
        }
        String head = "(";
        String tail = ")";

            if (left > 0) {
                String ll = temp + head;
                bfs(ll, left - 1, right, res);
            }
            if (right > 0 && right > left) {
                String ll = temp + tail;
                bfs(ll, left, right - 1, res);
            }

    }
}
