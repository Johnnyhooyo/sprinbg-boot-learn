package com.hyq.learning.leetcode.tree;

/**
 * @ClassName BinaryTreePruning
 * @Author dibulidohu
 * @Date 2019/6/27 20:14
 * @Description 给定二叉树根结点 root ，此外树的每个结点的值要么是 0，要么是 1。
 *
 * 返回移除了所有不包含 1 的子树的原二叉树。
 *
 * ( 节点 X 的子树为 X 本身，以及所有 X 的后代。)
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/binary-tree-pruning
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class BinaryTreePruning {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        TreeNode treeNode1 = new TreeNode(0);
        TreeNode treeNode2 = new TreeNode(1);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(1);
        TreeNode treeNode5 = new TreeNode(0);
//        TreeNode treeNode6 = new TreeNode(1);
//        TreeNode treeNode7 = new TreeNode(3);
//        TreeNode treeNode8 = new TreeNode(2);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.right = treeNode5;
        System.out.println(pruneTree(treeNode));
    }

    public static TreeNode pruneTree(TreeNode root) {
        prune(root);
        return root;
    }

    public static boolean prune(TreeNode root) {
        if (root == null) {
            return true;
        } else {
            boolean prune = prune(root.left);
            if (prune) {
                root.left = null;
            }
            boolean prune1 = prune(root.right);
            if (prune1) {
                root.right = null;
            }
            if (root.val == 0 && prune && prune1){
                return true;
            }
        }
        return false;
    }
}
