package com.karthik.java.dp;

public class MaximumScoreMultiplying {

    public int maximumScore(int[] nums, int[] multipliers) {
        int N = nums.length;
        int M = multipliers.length;
        int[][] dp = new int[M + 1][M + 1];
        for (int j = M - 1; j >= 0; j--) {
            for (int i = 0; i <= j; i++) {
                int num = multipliers[j];
                int fromStart = nums[i] * num + dp[i + 1][j + 1];
                int fromEnd = nums[N - j - 1 + i] * num + dp[i][j + 1];
                dp[i][j] = Math.max(fromStart, fromEnd);
            }
        }
        return dp[0][0];
    }
}
