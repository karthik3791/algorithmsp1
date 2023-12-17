package com.karthik.java.dp;

public class MinDifficultyJobSchedule {

    public int minDifficulty(int[] jobDifficulty, int d) {
        int[][] dp = new int[d + 1][jobDifficulty.length + 1];
        for (int i = 0; i < d; i++)
            dp[i][jobDifficulty.length] = Integer.MAX_VALUE;
        for (int j = 0; j < jobDifficulty.length; j++)
            dp[d][j] = Integer.MAX_VALUE;

        for (int i = d - 1; i >= 0; i--)
            for (int j = jobDifficulty.length - 1; j >= 0; j--) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int k = j; k < jobDifficulty.length; k++) {
                    max = Math.max(max, jobDifficulty[k]);
                    if (dp[i + 1][k + 1] != Integer.MAX_VALUE)
                        min = Math.min(min, max + dp[i + 1][k + 1]);
                }
                dp[i][j] = min;
            }
        return dp[0][0] == Integer.MAX_VALUE ? -1 : dp[0][0];
    }

}
