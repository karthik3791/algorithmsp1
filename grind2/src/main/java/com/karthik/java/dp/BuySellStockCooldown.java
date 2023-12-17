package com.karthik.java.dp;

public class BuySellStockCooldown {

    public int maxProfit(int[] prices) {
        /*
           State variables : i which indicates index of price 0 <=i<=prices.length-1
           holding : 0 -> nope, 1 yes
           dp[i][holding] = max of
           doNothing = dp[i+1][holding] // do nothing
           doSomething = if(holding ==1) prices[i] + dp[i+2][0] //sell
           doSomething = if(holding ==0) -prices[i] + dp[i+1][1] // buy
           dp[prices.length][_] = 0
         */
        int[][] dp = new int[prices.length + 2][2];
        for (int i = prices.length - 1; i >= 0; i--)
            for (int holding = 0; holding < 2; holding++) {
                int doNothing = dp[i + 1][holding];
                int doSomething = holding == 1 ? prices[i] + dp[i + 2][0] : -prices[i] + dp[i + 1][1];
                dp[i][holding] = Math.max(doNothing, doSomething);
            }
        return dp[0][0];
    }
}
