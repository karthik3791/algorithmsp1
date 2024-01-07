package com.karthik.java.dp;

import scala.Int;

public class PaintHouseIII {
    public int minCost(int[] houses, int[][] cost, int m, int n, int target) {
        //3 State Variables
        //1. Index of house
        //2. Target Remaining
        //3. Prev Color (1 to N)
        // Recurrence Relation
     /*
        dp[i][target][prevcolor] =
        if(house[i]==0){
         Find minimum from
          for each color from 1 to N
            if color==prevColor
              -cost[i][color-1] +dp[i+1][target][prevColor]
            else if (target>0 && dp[i+1][target-1][color] != Integer.MAX_VALUE)
             -cost[i][color-1] + dp[i+1][target-1][color];
        }
        else {
           if(house[i] == prevColor)
             dp[i+1][target][prevColor]
           else if(target > 0 && dp[i+1][target-1][house[i] ~= Integer.MAX_VALUE)
             dp[i+1][target-1][house[i]]
        }
        Base cases : dp[m][n >0] [_] = Integer.MAX_VALUE as we did not hit our target
        despite painting all the houses
        dp[m][0][_] = 0
      */
        int[][][] dp = new int[m + 1][target + 1][n + 1];

        for (int t = 1; t <= target; t++)
            for (int prevColor = 0; prevColor <= n; prevColor++)
                dp[m][t][prevColor] = Integer.MAX_VALUE;

        for (int i = m - 1; i >= 0; i--) {
            for (int t = 0; t <= target; t++) {
                for (int prevColor = 0; prevColor <= n; prevColor++) {
                    dp[i][t][prevColor] = Integer.MAX_VALUE;
                    if (houses[i] == 0) {
                        //Paint the house
                        int minCost = Integer.MAX_VALUE;
                        for (int color = 1; color <= n; color++) {
                            if (color != prevColor && t == 0) continue;
                            int costToPaint = cost[i][color - 1];
                            int costForOtherHouses = (color == prevColor) ? dp[i + 1][t][color] : dp[i + 1][t - 1][color];
                            if (costForOtherHouses != Integer.MAX_VALUE)
                                minCost = Math.min(minCost, costToPaint + costForOtherHouses);
                        }
                        dp[i][t][prevColor] = minCost;
                    } else {
                        // House is already painted
                        if (houses[i] == prevColor) dp[i][t][prevColor] = dp[i + 1][t][prevColor];
                        else if (t > 0) dp[i][t][prevColor] = dp[i + 1][t - 1][houses[i]];
                    }
                }
            }
        }
        return dp[0][target][0] == Integer.MAX_VALUE ? -1 : dp[0][target][0];
    }

    public static void main(String[] args) {
        PaintHouseIII p = new PaintHouseIII();
        int[] houses = {0, 0, 0, 0, 0};
        //int[] houses = {0, 0};
        int[][] costs = {{1, 10}, {10, 1}, {10, 1}, {1, 10}, {5, 1}};
        //int[][] costs = {{1, 10}, {5, 1}};
        int m = 5;
        int n = 2;
        int target = 3;
        System.out.println(p.minCost(houses, costs, m, n, target));
    }
}
