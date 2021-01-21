package com.hyq.learning.leetcode;

/**
 * @author：huyuanqiang
 * @time: 2020-12-25 14:51
 * @description:
 * 多于一个'A'（缺勤）或超过两个连续的'L'（迟到） P/L/A
 **/
public class CheckRecord {

    public static void main(String[] args) {
        CheckRecord record = new CheckRecord();
        int i = record.checkRecord(8);
        System.out.println(i);
    }


    public int checkRecord(int n) {
        if (n == 1) {return 3;}
        if (n == 2) {
            //pp pl lp ap pa la al ll
            return 8;
        }

        //dp[深度][][] p = 0 / l = 1/ a = 2
        int[][][] dp = new int[n][3][3];

        dp[1][0][0] = 1;
        dp[1][0][1] = 1;
        dp[1][0][2] = 1;
        dp[1][1][0] = 1;
        dp[1][1][1] = 1;
        dp[1][1][2] = 1;
        dp[1][2][0] = 1;
        dp[1][2][1] = 1;

        for (int i = 2; i < n; i++) {
            dp[i][0][0] = (dp[i - 1][0][0] + dp[i-1][1][0] + dp[i-1][2][0])%1000000007;
            dp[i][0][1] = (dp[i - 1][0][0] + dp[i-1][1][0] + dp[i-1][2][0])%1000000007;
            dp[i][0][2] = (dp[i - 1][0][0] + dp[i-1][1][0]                )%1000000007;
            dp[i][1][0] = (dp[i - 1][0][1] + dp[i-1][1][1] + dp[i-1][2][1])%1000000007;
            dp[i][1][1] = (dp[i - 1][0][1]                 + dp[i-1][2][1])%1000000007;
            dp[i][1][2] = (dp[i - 1][0][1] + dp[i-1][1][1]                )%1000000007;
            dp[i][2][0] = (dp[i - 1][0][2] + dp[i-1][1][2]                )%1000000007;
            dp[i][2][1] = (dp[i - 1][0][2] + dp[i-1][1][2]                )%1000000007;
        }

        return (dp[n-1][0][0] + dp[n-1][0][1] + dp[n-1][0][2] + dp[n-1][1][0]
                + dp[n-1][1][1] + dp[n-1][1][2] + dp[n-1][2][0] + dp[n-1][2][1]) % 1000000007;
    }
}
