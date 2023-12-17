package com.karthik.java.dp;

import java.util.Arrays;

public class CoinChange {

    public int coinChangeLimited(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 0; i <= coins.length; i++)
            dp[i][0] = 0;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                if (dp[i - 1][j] != Integer.MAX_VALUE)
                    dp[i][j] = dp[i - 1][j];
                if (coins[i - 1] <= j && dp[i - 1][j - coins[i - 1]] != Integer.MAX_VALUE)
                    dp[i][j] = Math.min(dp[i][j], 1 + dp[i - 1][j - coins[i - 1]]);
            }
        }
        return dp[coins.length][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length][amount];
    }

    // THis is with O[N] space
    public int coinChange(int[] coins, int amount) {
        int[] dp = new int[amount + 1];
        for (int i = 1; i <= amount; i++) {
            dp[i] = Integer.MAX_VALUE; // assume that we cannot hit amount with any of the coins first. This might happen if each coin is > amount or the sum of coins might be > amount.
            for (int j = 0; j < coins.length; j++) {
                if (coins[j] <= i && dp[i - coins[j]] != Integer.MAX_VALUE)
                    dp[i] = Math.min(dp[i], 1 + dp[i - coins[j]]);
            }
        }
        return dp[amount] == Integer.MAX_VALUE ? -1 : dp[amount];
    }


    // This is with space O[m x n]
    public int coinChangeNsquare(int[] coins, int amount) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++)
            Arrays.fill(dp[i], Integer.MAX_VALUE);
        for (int i = 0; i <= coins.length; i++)
            dp[i][0] = 0;

        for (int i = 1; i <= coins.length; i++) {
            for (int j = 1; j <= amount; j++) {
                for (int k = 0; k * coins[i - 1] <= j; k++) {
                    if (dp[i - 1][j - k * coins[i - 1]] != Integer.MAX_VALUE)
                        dp[i][j] = Math.min(dp[i][j], k + dp[i - 1][j - k * coins[i - 1]]);
                }
            }
        }
        return dp[coins.length][amount] == Integer.MAX_VALUE ? -1 : dp[coins.length][amount];
    }

    public static void main(String[] args) {
        CoinChange c = new CoinChange();
        int[] coins = {1, 2, 5};
        c.coinChangeLimited(coins, 11);
    }
}
