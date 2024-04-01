package com.karthik.java.greedy;

public class BuySellStockII {
    public int maxProfitDP(int[] prices) {
        int[][] dp = new int[prices.length + 1][2];
        for (int i = prices.length - 1; i >= 0; i--) {
            for (int holding = 0; holding < 2; holding++) {
                int doNothing = dp[i + 1][holding];
                int doSomething = (holding == 0) ? -prices[i] + dp[i + 1][1] : prices[i] + dp[i + 1][0];
                dp[i][holding] = Math.max(doNothing, doSomething);
            }
        }
        return dp[0][0];
    }

    public int maxProfit(int[] prices) {
        int buy = 0;
        int sell = 0;
        int maxProfit = 0;
        while (buy < prices.length && sell < prices.length) {
            while (buy < prices.length - 1 && prices[buy + 1] < prices[buy]) buy++;
            sell = buy;
            while (sell < prices.length - 1 && prices[sell + 1] > prices[sell]) sell++;
            maxProfit += prices[sell] - prices[buy];
            buy = sell + 1;
        }
        return maxProfit;
    }
}
