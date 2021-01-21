package com.hyq.learning.leetcode.hash;

/**
 * @author：huyuanqiang
 * @time: 2020-06-04 17:55
 * @description: https://zhuanlan.zhihu.com/p/93647900   并查集
 **/
public class UnionSet {

    public static void main(String[] args) {
        UnionSet multiply = new UnionSet();
        //["c==c","f!=a","f==b","b==c"]
        boolean b = multiply.equationsPossible(new String[]{"c==c","f!=a","f==b","c==b","b==d","d==h"});
        System.out.println(b);
    }

    int[] deep = new int[26];
    int[] arr = new int[26];

    public boolean equationsPossible(String[] equations) {


        for (int i = 0; i < 26; i++) {
            arr[i] = i;
            deep[i] = 1;
        }
        for (String equation : equations) {
            if (equation.charAt(1) != '!') {
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                merge(a, b);
            }
        }
        for (String equation : equations) {
            if (equation.charAt(1) == '!') {
                int a = equation.charAt(0) - 'a';
                int b = equation.charAt(3) - 'a';
                if (find(a) ==find(b)) {
                    return false;
                }
            }
        }
        return true;
    }

    private void merge(int a, int b) {
        int findA = find(a);
        int findB = find(b);
        if (deep[findA] <= deep[findB]) {
            arr[findA] = findB;
        } else {
            arr[findB] = findA;
        }
        if (deep[findA] == deep[findB] && findA != findB) {
            deep[findB]++;
        }
    }

    private int find(int a) {
        return arr[a] == a ? a : (arr[a] = find(arr[a]));
    }
}
