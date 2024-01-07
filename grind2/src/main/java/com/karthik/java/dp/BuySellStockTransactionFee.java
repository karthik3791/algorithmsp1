package com.karthik.java.dp;

public class BuySellStockTransactionFee {

    public int maxProfit2(int[] prices, int fee) {
        int hold = 0;
        int dontHold = 0;
        for (int i = prices.length - 1; i >= 0; i--) {
            for (int holding = 0; holding < 2; holding++) {
                int doNothing = (holding == 0) ? dontHold : hold;
                int doSomething = (holding == 0) ? -prices[i] + hold : prices[i] - fee + dontHold;
                int maxOfTwo = Math.max(doNothing, doSomething);
                if (holding == 0)
                    dontHold = maxOfTwo;
                else
                    hold = maxOfTwo;
            }
        }
        return dontHold;
    }

    public int maxProfit(int[] prices, int fee) {
        int[][] dp = new int[prices.length + 1][2];
        for (int i = prices.length - 1; i >= 0; i--) {
            for (int holding = 0; holding < 2; holding++) {
                int doNothing = dp[i + 1][holding];
                int doSomething = (holding == 0) ? -prices[i] + dp[i + 1][1] : prices[i] - fee + dp[i + 1][0];
                dp[i][holding] = Math.max(doNothing, doSomething);
            }
        }
        return dp[0][0];
    }
}
