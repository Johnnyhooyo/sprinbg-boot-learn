package com.hyq.learning.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dibulidohu
 * @classname PostOrderMultiTree
 * @date 2019/5/2316:15
 * @description
 */
public class PostOrderMultiTree {


    public static void main(String[] args) {
        PostOrderMultiTree p
                 = new PostOrderMultiTree();
        p.excute();
    }

    public void excute() {
        ListTreeNode node5 = new ListTreeNode(6, null);
        List<ListTreeNode> three  = new ArrayList<>();
        three.add(node5);
        ListTreeNode node2 = new ListTreeNode(5, null);
        ListTreeNode node3 = new ListTreeNode(0, null);
        List<ListTreeNode> two  = new ArrayList<>();
        two.add(node2);
        two.add(node3);
        ListTreeNode node1 = new ListTreeNode(10, two);
        ListTreeNode node4 = new ListTreeNode(3, three);
        List<ListTreeNode> one = new ArrayList<>();
        one.add(node1);
        one.add(node4);
        ListTreeNode node = new ListTreeNode(1, one);
        long l = System.currentTimeMillis();
        List<Integer> postorder = postorder(node);
        System.out.println("cost : " + (System.currentTimeMillis() - l));
        System.out.println(postorder);
    }

    public List<Integer> postorder(ListTreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(null == root) {
            return res;
        }
        ListTreeNode last = null;
        Stack<ListTreeNode> stack = new Stack<>();
        stack.push(root);
        while(!stack.isEmpty()) {
            List<ListTreeNode> nodes = stack.peek().children;
            if(nodes != null && !nodes.contains(last)) {
                for(int i = nodes.size() - 1; i>=0; i--) {
                    stack.push(nodes.get(i));
                }
            } else {
                ListTreeNode node = stack.pop();
                res.add(node.val);
                last = node;
            }
        }
        return res;
    }
}
