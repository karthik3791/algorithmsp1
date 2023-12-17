package com.karthik.java.dp;

public class BuySellStockIV {
    public int maxProfit(int k, int[] prices) {
        /*
            State Variables : i which indicates which index of price we are looking at in prices (0<=i<=prices.length-1)
            j : which indicates how many transactions are remaining (1<=j<=k)
            holding : 0 -> not holding stock, 1 -> holding stock

            dp[i][j][holding] = dp[i+1][j][holding] => doNothing
            dp[i][j][holding] = if(holding==1) prices[i] + dp[i+1][j-1][0] => sell the stock
            dp[i][j][holding] = if(holding==0) -prices[i] + dp[i+1][j][1] => buy the stock

            dp[i][j][holding] = max(the 3 above)

            dp[prices.length][_][_] = 0
            dp[_][0][_] = 0
         */
        int[][][] dp = new int[prices.length + 1][k + 1][2];

        for (int i = prices.length - 1; i >= 0; i--) {
            for (int j = k; j >= 1; j--) {
                for (int holding = 0; holding < 2; holding++) {
                    int doNothing = dp[i + 1][j][holding];
                    int doSomething = (holding == 1) ? prices[i] + dp[i + 1][j - 1][0] : -prices[i] + dp[i + 1][j][1];
                    dp[i][j][holding] = Math.max(doNothing, doSomething);
                }
            }
        }
        return dp[0][k][0];
    }
}
