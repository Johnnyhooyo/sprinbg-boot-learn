package com.hyq.learning.leetcode.tree;

import java.util.Arrays;

/**
 * @author：huyuanqiang
 * @time: 2021-02-22 15:53
 * @description:
 **/
public class TreeNotice {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(3);
        TreeNode treeNode2 = new TreeNode(2);
        TreeNode treeNode3 = new TreeNode(3);
        TreeNode treeNode4 = new TreeNode(4);
        treeNode.right = treeNode1;
        treeNode1.left = treeNode2;
        treeNode2.left = treeNode3;
        treeNode3.left = treeNode4;
        TreeNotice treeNotice = new TreeNotice();
        treeNotice.minCameraCover(treeNode);
    }

    public int minCameraCover(TreeNode root) {
        if (root.left == null && root.right == null) {
            return 1;
        }
        int[] cover = cover(root);
        return Math.min(cover[1], cover[2]);
    }

    /**
     *
     * @param node
     * @return res[0] 子树需要的监控节点 res[1] 子节点的根结点是否有监控 0 没有监控到 1 被监控 2 自己就是监控
     *
     *
     * 子树 可能的状态 0 1 2
     * 1 = Math.min(l2 + Math,min(R1, R2), Math.min(L1, L2) + R2)
     * 2 = L0 + R0 + 1
     * 0 = L1 + R1
     */
    public int[] cover(TreeNode node) {
        if (node == null) {
            return new int[] {0,0,1};
        }
        int[] res = new int[3];
        int[] r = cover(node.right);
        int[] l = cover(node.left);
        res[0] = r[1] + l[1];
        res[1] = Math.min(l[2] + Math.min(r[1], r[2]), r[2] + Math.min(l[1], l[2]));
        res[2] = Math.min(l[0], Math.min(l[1], l[2])) + Math.min(r[0], Math.min(r[1], r[2])) +1;
        return res;
    }
}
