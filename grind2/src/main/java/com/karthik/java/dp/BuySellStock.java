package com.karthik.java.dp;

public class BuySellStock {
    public int maxProfit(int[] prices) {
        if (prices.length < 2) return 0;
        int maxProfit = 0;
        int currentMin = prices[0];
        for (int i = 1; i < prices.length; i++) {
            currentMin = Math.min(currentMin, prices[i]);
            maxProfit = Math.max(maxProfit, prices[i] - currentMin);
        }
        return maxProfit;
    }
}
