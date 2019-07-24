package com.hyq.learning.leetcode.tree;

import com.google.common.collect.Lists;
import sun.reflect.generics.tree.Tree;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * @author dibulidohu
 * @classname BalanceTreeDepth
 * @date 2019/5/2511:46
 * @description
 */
public class BalanceTreeDepth {

    public static void main(String[] args) {
        int min = Math.min(2, 2);
        System.out.println(min);
        ArrayList<Object> objects = Lists.newArrayList();
    }

    public int maxDepth(TreeNode root) {
        if(null == root) {
            return 0;
        }
        return 1 + Math.max(maxDepth(root.left), maxDepth(root.right));
    }

    public TreeNode convert(int[] arr) {
        if (arr.length == 0) {
            return null;
        }
        TreeNode res = new TreeNode(arr[0]);
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(res);
        for (int i = 1; i < arr.length ;) {
            TreeNode poll = queue.poll();
            TreeNode left = new TreeNode(arr[i++]);
            poll.left = left;
            if (i < arr.length) {
                TreeNode right = new TreeNode(arr[i++]);
                poll.right = right;
                queue.add(right);
            }
            queue.add(left);
        }
        return res;
    }
    public int maxDepth1(TreeNode root) {
        Queue<TreeNode> q = new LinkedList<>();
        Queue<TreeNode> queue = new LinkedBlockingQueue<>();
        queue.add(root);
        int dep = 0;
        while (!queue.isEmpty()) {
            int size = queue.size();
            dep++;
            for (int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if (null != node) {
                    if (node.right != null) {
                        queue.add(node.right);
                    }
                    if (node.left != null) {
                        queue.add(node.left);
                    }
                }
            }
        }
        return dep;
    }


    public int minDepth(TreeNode root) {
        if(root == null) {
            return 0;
        }
        if(root.left == null && root.right == null) {
            return 1;
        } else if (root.left == null) {
            return minDepth(root.right) + 1;
        } else if (root.right == null) {
            return minDepth(root.left) + 1;
        } else {
            return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
        }
    }
}
