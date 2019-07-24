package com.hyq.learning.leetcode.tree;

import java.util.List;

/**
 * @ClassName ListTreeNode
 * @Author dibulidohu
 * @Date 2019/7/1 19:27
 * @Description
 */
public class ListTreeNode {
    int val;
    List<ListTreeNode> children;

    public ListTreeNode(int val, List<ListTreeNode> children) {
        this.val = val;
        this.children = children;
    }
}
