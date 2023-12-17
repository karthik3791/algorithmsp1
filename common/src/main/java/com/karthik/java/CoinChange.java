package com.karthik.java;

import java.util.Arrays;

public class CoinChange {

    public int coinChange(int[] coins, int amount) {
        int[][] dp = new int[coins.length][amount + 1];
        for (int i = 0; i < dp.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);

        for (int i = 0; i < dp.length; i++)
            dp[i][0] = 1;

        for (int j = 1; j <= amount; j++)
            if (j % coins[0] == 0)
                dp[0][j] = (j / coins[0]);


        for (int i = 1; i < coins.length; i++)
            for (int j = 1; j <= amount; j++) {
                dp[i][j] = dp[i - 1][j];
                if (j - coins[i] >= 0)
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i][j - coins[i]]);
            }
        return dp[coins.length - 1][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length - 1][amount];
    }

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        c.coinChange(new int[]{3}, 2);
    }
}
