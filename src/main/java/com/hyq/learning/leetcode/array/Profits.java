package com.hyq.learning.leetcode.array;

/**
 * @author：huyuanqiang
 * @time: 2021-01-09 14:46
 * @description:
 * 给定一个数组，它的第 i 个元素是一支给定的股票在第 i 天的价格。
 *
 * 设计一个算法来计算你所能获取的最大利润。你最多可以完成 两笔 交易。
 *
 * 注意：你不能同时参与多笔交易（你必须在再次购买前出售掉之前的股票）。
 *
 *  
 *
 * 示例 1:
 *
 * 输入：prices = [3,3,5,0,0,3,1,4]
 * 输出：6
 * 解释：在第 4 天（股票价格 = 0）的时候买入，在第 6 天（股票价格 = 3）的时候卖出，这笔交易所能获得利润 = 3-0 = 3 。
 *      随后，在第 7 天（股票价格 = 1）的时候买入，在第 8 天 （股票价格 = 4）的时候卖出，这笔交易所能获得利润 = 4-1 = 3 。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/best-time-to-buy-and-sell-stock-iii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 **/
public class Profits {

    public static void main(String[] args) {
        Profits profits = new Profits();
        int i = profits.maxProfit(new int[]{5,4,3,2,1});
        System.out.println(i);
    }

    /**
     * 给定最开始手头的钱是0元。
     * 动态规划，每一次结束有以下几种状态：
     * 0。第一手购买 利润：Math.max(-prices[i], dp[i-1][0])
     * 1。第一手卖出 利润：Math.max(dp[i-1][0] + princes[i], dp[i-1][1])
     * 2。第二手买入 利润：Math.max(dp[i-1][2], dp[i-1][1]-prices[i])
     * 3。第二手卖出 利润：Math.max(dp[i-1][3], dp[i-1][2]+prices[i])
     *
     * 但是在前几手，不存在的情况下，怎么处理。Math.MIN_VALUE;
     */
    public int maxProfit(int[] prices) {
        if (prices.length <= 1) {
            return 0;
        }
        int[][] dp = new int[prices.length][4];

        dp[0][0] = -prices[0];
        dp[0][1] = 0;
        dp[0][2] = 0;
        dp[0][3] = 0;


        for (int i = 1; i < prices.length; i++) {
            dp[i][0] = Math.max(-prices[i], dp[i-1][0]);
            dp[i][1] = Math.max(dp[i-1][0] + prices[i], dp[i-1][1]);
            dp[i][2] = i < 2 ? Integer.MIN_VALUE : Math.max(dp[i-1][2], dp[i-1][1]-prices[i]);
            dp[i][3] = i < 3 ? Integer.MIN_VALUE : Math.max(dp[i-1][3], dp[i-1][2]+prices[i]);
        }
        return Math.max(Math.max(dp[prices.length-1][0], dp[prices.length-1][1]), Math.max(dp[prices.length-1][2], dp[prices.length-1][3]));
    }
}
