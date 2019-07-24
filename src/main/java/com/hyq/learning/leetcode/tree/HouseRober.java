package com.hyq.learning.leetcode.tree;

import java.math.BigDecimal;
import java.util.HashMap;

/**
 * @ClassName HouseRober
 * @Author dibulidohu
 * @Date 2019/6/26 17:39
 * @Description 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
 *
 * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/house-robber
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class HouseRober {

    public static void main(String[] args) {
        System.out.println(System.currentTimeMillis());
        int[] a = {1,2,1,1};
        System.out.println(robInCyc(a));

        TreeNode treeNode = new TreeNode(3);
        TreeNode treeNode1 = new TreeNode(4);
        TreeNode treeNode2 = new TreeNode(5);
        TreeNode treeNode3 = new TreeNode(1);
        TreeNode treeNode4 = new TreeNode(3);
        TreeNode treeNode5 = new TreeNode(1);
//        TreeNode treeNode6 = new TreeNode(1);
//        TreeNode treeNode7 = new TreeNode(3);
//        TreeNode treeNode8 = new TreeNode(2);
        treeNode.left = treeNode1;
        treeNode.right = treeNode2;
        treeNode1.left = treeNode3;
        treeNode1.right = treeNode4;
        treeNode2.right = treeNode5;
        System.out.println(rob(treeNode));
        System.out.println(robInArray(treeNode));
    }
    /***
     * 你是一个专业的小偷，计划偷窃沿街的房屋。每间房内都藏有一定的现金，影响你偷窃的唯一制约因素就是相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *  *
     *  * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *  *
     *  * 来源：力扣（LeetCode）
     *  * 链接：https://leetcode-cn.com/problems/house-robber
     *  * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     */
    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return  nums[0] > nums[1] ? nums[0] : nums[1];
        }
        int[] money = new int[nums.length];
        money[0] = nums[0];
        money[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length; i++) {
            money[i] = money[i-1] > money[i-2] + nums[i] ? money[i-1] : money[i-2] + nums[i];
        }
        return money[nums.length - 1];
    }

    /***
     * 你是一个专业的小偷，计划偷窃沿街的房屋，每间房内都藏有一定的现金。这个地方所有的房屋都围成一圈，这意味着第一个房屋和最后一个房屋是紧挨着的。同时，相邻的房屋装有相互连通的防盗系统，如果两间相邻的房屋在同一晚上被小偷闯入，系统会自动报警。
     *
     * 给定一个代表每个房屋存放金额的非负整数数组，计算你在不触动警报装置的情况下，能够偷窃到的最高金额。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-ii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 两个money数组可以用一个
     */
    public static int robInCyc(int[] nums) {
        if (nums.length == 0) {
            return 0;
        } else if (nums.length == 1) {
            return nums[0];
        } else if (nums.length == 2) {
            return  nums[0] > nums[1] ? nums[0] : nums[1];
        }
        int[] money = new int[nums.length - 1];
        money[0] = nums[0];
        money[1] = nums[0] > nums[1] ? nums[0] : nums[1];
        for (int i = 2; i < nums.length - 1; i++) {
            money[i] = money[i-1] > money[i-2] + nums[i] ? money[i-1] : money[i-2] + nums[i];
        }

        int[] money1 = new int[nums.length - 1];
        money1[0] = nums[1];
        money1[1] = nums[2] > nums[1] ? nums[2] : nums[1];
        for (int i = 3; i < nums.length; i++) {
            money1[i-1] = money1[i-2] > money1[i-3] + nums[i] ? money1[i-2] : money1[i-3] + nums[i];
        }
        return money[money.length - 1] > money1[money1.length - 1] ?  money[money.length - 1] : money1[money1.length - 1];
    }

    /***
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路1：这个问题可以这样思考，如上面这个例子来看，其实就是两种结果，
     * 一种是 root.val + A 子树的不带根结点的打家劫舍结果 + B 子树的不带根结点的打家劫舍结果，
     * 另一种呢，就是不包含 root 的两个子树 A 和 B 的打家劫舍值之和。
     * 代码如下，不带 cache 会超时，但是由于很多时候结点的打家劫舍值是会重复计算，所以加个 cache 就会通过。 我认为逻辑还算比较清晰的。

     */
    public static int rob(TreeNode root) {
        return robMax(root);
    }

    public static int robMax(TreeNode root) {
        int with = robWithRoot(root);
        int without = robWithOutRoot(root);
        return with > without ? with : without;
    }

    public static int robWithRoot(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null) {
            return root.val + robWithOutRoot(root.right);
        } else if (root.right == null) {
            return root.val + robWithOutRoot(root.left);
        }
        return root.val + robWithOutRoot(root.left) + robWithOutRoot(root.right);
    }

    public static int robWithOutRoot(TreeNode root) {
        if (root == null) {
            return 0;
        } else if (root.left == null) {
            return robMax(root.right);
        } else if (root.right == null) {
            return robMax(root.left);
        }
        return robMax(root.left) + robMax(root.right);
    }

    /***
     * 在上次打劫完一条街道之后和一圈房屋后，小偷又发现了一个新的可行窃的地区。这个地区只有一个入口，我们称之为“根”。 除了“根”之外，每栋房子有且只有一个“父“房子与之相连。一番侦察之后，聪明的小偷意识到“这个地方的所有房屋的排列类似于一棵二叉树”。 如果两个直接相连的房子在同一天晚上被打劫，房屋将自动报警。
     *
     * 计算在不触动警报的情况下，小偷一晚能够盗取的最高金额。
     *
     * 来源：力扣（LeetCode）
     * 链接：https://leetcode-cn.com/problems/house-robber-iii
     * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
     *
     * 思路2：用个两位数组，一个标识不抢根基欸的那的最大值  一个标识抢了根节点的最大值

     */
    public static int robInArray(TreeNode root) {
        int without = robMaxInArray(root)[0];
        int with = robMaxInArray(root)[1];
        return without > with ? without : with;
    }

    public static int[] robMaxInArray(TreeNode root) {
        int[] res = new int[2];
        if (root == null) {
            return res;
        }
        int[] left = robMaxInArray(root.left);
        int[] right = robMaxInArray(root.right);
        res[0] = (left[1] > left[0] ? left[1] : left[0]) + (right[1] > right[0] ? right[1] : right[0]);
        res[1] = left[0] + right[0] + root.val;
        return res;
    }
}
