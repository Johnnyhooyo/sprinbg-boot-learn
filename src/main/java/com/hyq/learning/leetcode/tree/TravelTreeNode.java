package com.hyq.learning.leetcode.tree;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dibulidohu
 * @classname MidSortTree
 * @date 2019/5/2219:20
 * @description
 */
public class TravelTreeNode {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        treeNode.right = treeNode1;
        treeNode1.left = treeNode2;
        List<Integer> list = postorderTraversalBetter(treeNode);
        System.out.println(list);
    }

    public static List<Integer> inorderTraversal(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        List<Integer> res = new ArrayList<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            while (stack.peek().left != null) {
                stack.push(stack.peek().left);
            }
            while (!stack.isEmpty()) {
                TreeNode pop = stack.pop();
                res.add(pop.val);
                if (pop.right != null) {
                    stack.push(pop.right);
                    break;
                }
            }
        }
        return res;
    }

    public List<Integer> inorderTraversalBeeter(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode pp = root;
        while (pp != null || !stack.isEmpty()) {
            if (pp != null) {
                stack.push(pp);
                pp = pp.left;
            } else {
                pp = stack.pop();
                res.add(pp.val);
                pp = pp.right;
            }
        }
        return res;
    }

    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            TreeNode pop = stack.pop();
            res.add(pop.val);
            if (null != pop.right) {
                stack.push(pop.right);
            }
            if (null != pop.left) {
                stack.push(pop.left);
            }
        }
        return res;
    }

    public static List<Integer> postorderTraversal(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        if(null == root) {
            return res;
        }
        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);
        TreeNode lastNode = null;
        while(!stack.isEmpty()) {
            while(stack.peek().left != null) {
                stack.push(stack.peek().left);
            }
            while (!stack.isEmpty()) {
                if (stack.peek().right == lastNode || stack.peek().right == null) {
                    TreeNode pop = stack.pop();
                    res.add(pop.val);
                    lastNode  = pop;
                } else {
                    stack.push(stack.peek().right);
                    break;
                }
            }
        }
        return res;
    }

    public static List<Integer> postorderTraversalBetter(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        Stack<TreeNode> stack = new Stack<>();
        TreeNode last = null;
        while(!stack.isEmpty() || root != null) {
            if (root != null) {
                stack.push(root);
                root = root.left;
            } else {
                if (stack.peek().right != null && stack.peek().right != last) {
                    stack.push(stack.peek().right);
                    root = stack.peek().left;
                } else {
                    TreeNode pop = stack.pop();
                    res.add(pop.val);
                    last = pop;
                    root = null;
                }
            }
        }
        return res;
    }
}
