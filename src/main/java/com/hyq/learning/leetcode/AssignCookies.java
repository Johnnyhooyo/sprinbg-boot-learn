package com.hyq.learning.leetcode;

import com.google.common.collect.Lists;

import java.util.*;

/**
 * @author：huyuanqiang
 * @time: 2020-12-25 10:34
 * @description:
 * 假设你是一位很棒的家长，想要给你的孩子们一些小饼干。但是，每个孩子最多只能给一块饼干。
 *
 * 对每个孩子 i，都有一个胃口值 g[i]，这是能让孩子们满足胃口的饼干的最小尺寸；并且每块饼干 j，都有一个尺寸 s[j] 。
 * 如果 s[j] >= g[i]，我们可以将这个饼干 j 分配给孩子 i ，这个孩子会得到满足。你的目标是尽可能满足越多数量的孩子，并输出这个最大数值。
 **/
public class AssignCookies {

    public static void main(String[] args) {
        //[1,2,3,5,3,1,23,5]
        //[1,1,5,23,5,6]
        int [] g = new int[]{1,2,3,5,3,1,23,5};
        int [] s = new int[]{1,1,5,23,5,6};

        AssignCookies assignCookies = new AssignCookies();
        int contentChildren = assignCookies.findContentChildren(g, s);
        System.out.println(contentChildren);
    }

    public int findContentChildren(int[] g, int[] s) {
        ArrayList<Integer> cookieList = Lists.newArrayList();
        for (int i : s) {
            cookieList.add(i);
        }
        ArrayList<Integer> childList = Lists.newArrayList();
        for (int i : g) {
            childList.add(i);
        }
        cookieList.sort(Integer::compareTo);
        childList.sort(Integer::compareTo);

        int res = 0;
        for (int i = 0,j = 0; i < g.length && j < s.length; ) {
            if (cookieList.get(j) < childList.get(i)) {
                j++;
                continue;
            }
            res++;
            i++;
            j++;
        }
        return res;
    }
}
