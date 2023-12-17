package com.karthik.java.dp;

public class CoinChange2 {

    /*
      For example, say we have [1,2,5] and amount 3
      This approach will not count dupes..because at each dp[i][j] we deal with a single coin index and we consider upto k times this single coin
      and then move on to the other coins. We never count  duplicate combinations...
     */

    public int change(int amount, int[] coins) {
        int[][] dp = new int[coins.length + 1][amount + 1];
        for (int i = 0; i <= coins.length; i++)
            dp[i][0] = 1;

        for (int i = 1; i <= coins.length; i++)
            for (int j = 1; j <= amount; j++) {
                for (int k = 0; k * coins[i - 1] <= j; k++) {
                    dp[i][j] += dp[i - 1][j - k * coins[i - 1]];
                }
            }
        return dp[coins.length][amount];
    }


    //This does not work because we count duplicate routes
    /*
      For example, say we have [1,2,5] and amount 3

      dp[1] = 1
      dp[2] = 2
      dp[3] = dp[2] + dp[1] = 3
      (1,1,1) (1,2) (2,1)
      But in reality, we only have (1,2), (1,1,1) = 2 ways
     */
    public int changeWrong(int amount, int[] coins) {
        int[] dp = new int[amount + 1];
        dp[0] = 1;
        for (int j = 1; j <= amount; j++) {
            for (int i = 0; i < coins.length; i++) {
                if (coins[i] <= j)
                    dp[j] += dp[j - coins[i]];
            }
        }
        return dp[amount];
    }

    public static void main(String[] args) {
        CoinChange2 cg2 = new CoinChange2();
        int[] coins = {1, 2, 5};
        System.out.println(cg2.change(5, coins));
    }
}
