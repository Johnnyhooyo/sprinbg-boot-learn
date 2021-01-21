package com.hyq.learning.leetcode.tree;

/**
 * @author：huyuanqiang
 * @time: 2020-04-11 14:35
 * @description:
 **/
public class IsBalanceTree {
    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(0);
        TreeNode treeNode1 = new TreeNode(1);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        TreeNode treeNode5 = new TreeNode(5);
        TreeNode treeNode6 = new TreeNode(6);
        TreeNode treeNode7 = new TreeNode(7);
        TreeNode treeNode8 = new TreeNode(8);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode3.left = treeNode6;
        treeNode3.right = treeNode7;
        System.out.println(isBalanced(treeNode));
        System.out.println(deep(treeNode1));
        System.out.println(deep(treeNode2));
    }

    public static boolean isBalanced(TreeNode root) {
        if (null == root) {
            return true;
        }
        if (isBalanced(root.left) && isBalanced(root.right)) {
            return deep(root.left) - deep(root.right) <= 1;
        }
        return false;
    }

    public static int deep (TreeNode node) {
        if (null == node) {
            return 0;
        }
        return Math.max(deep(node.right), deep(node.left)) + 1;
    }

    public static int solutionTwo(TreeNode node) {
        if (null == node) {
            return 0;
        }
        int left = solutionTwo(node.left);
        if (-1 == left) {
            return -1;
        }
        int right = solutionTwo(node.right);
        if (-1 == right) {
            return -1;
        }
        return Math.abs(left - right) > 1 ? -1 : Math.max(left, right) +1;
    }
}
