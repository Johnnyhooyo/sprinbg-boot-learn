package com.hyq.learning.leetcode.tree;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * @author dibulidohu
 * @classname ArrOrderNode
 * @date 2019/5/2319:51
 * @description
 */
public class ArrOrderNode {

    public static void main(String[] args) {
        Stack<TreeNode> stack = new Stack<>();
        stack.push(null);
        stack.push(null);
        System.out.println(stack.size());
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> res = new ArrayList<>();
        List<TreeNode> nodes = new ArrayList<>();
        nodes.add(root);
        while(!CollectionUtils.isEmpty(nodes)) {
            List<Integer> temp = new ArrayList<>();
            List<TreeNode> trees = new ArrayList<>();
            for(TreeNode nn : nodes){
                if(nn != null) {
                    temp.add(nn.val);
                    trees.add(nn.left);
                    trees.add(nn.right);
                }
            }
            res.add(temp);
            nodes = trees;
        }
        return res;
    }
}