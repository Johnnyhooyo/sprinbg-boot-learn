package com.hyq.learning.niuke.treenode;

/**
 * @ClassName RebuildTree
 * @Author dibulidohu
 * @Date 2019/7/17 20:32
 * @Description 列{1,2,4,7,3,5,6,8}和中序遍历序列{4,7,2,1,5,3,8,6}
 */
public class RebuildTree {

    public static void main(String[] args) {
        int[] a = {1,2,4,7,3,5,6,8};
        int[] b = {4,7,2,1,5,3,8,6};
        TreeNode treeNode = reConstructBinaryTree(a, b);
        System.out.println(treeNode);
    }


    public static TreeNode reConstructBinaryTree(int [] pre,int [] in) {
        if(pre.length == 0) {
            return null;
        }
        TreeNode root = new TreeNode(pre[0]);
        int i = 0;
        for(; i < in.length; i++) {
            if(in[i] == pre[0]) {
                break;
            }
        }
        int[] bef = new int[i];
        int k = i;
        for(int j = i - 1; j >= 0; j--) {
            bef[j] = pre[k--];
        }
        int[] aft = new int[i];
        for(int j = 0; j < i; j++) {
            aft[j] = in[j];
        }
        root.left = reConstructBinaryTree(bef, aft);

        int[] rb = new int[pre.length - i - 1];
        k = i + 1;
        for(int j = 0; j < rb.length; j++) {
            rb[j] = pre[k++];
        }
        int[] rl = new int[pre.length - i - 1];
        k = i + 1;
        for(int j = 0; j < rl.length; j++) {
            rl[j] = in[k++];
        }
        root.right = reConstructBinaryTree(rb, rl);
        return root;
    }
}
