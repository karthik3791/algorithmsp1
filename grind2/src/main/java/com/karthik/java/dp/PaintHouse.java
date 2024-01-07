package com.karthik.java.dp;

public class PaintHouse {

    public int minCost2(int[][] costs) {
        int m = costs.length;
        int[] prevRowCost = new int[3];
        for (int i = 1; i <= m; i++) {
            int[] tmp = new int[3];
            for (int j = 0; j < 3; j++) {
                int costOfColor = costs[i - 1][j];
                int minFromPrev = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (k == j) continue;
                    minFromPrev = Math.min(minFromPrev, prevRowCost[k]);
                }
                tmp[j] = costOfColor + minFromPrev;
            }
            prevRowCost = tmp;
        }
        int finalMin = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++)
            finalMin = Math.min(finalMin, prevRowCost[j]);
        return finalMin;
    }


    public int minCost(int[][] costs) {
        int m = costs.length;
        int[][] dp = new int[m + 1][3];
        for (int i = 1; i <= m; i++) {
            for (int j = 0; j < 3; j++) {
                int costToColor = costs[i - 1][j];
                int minFromPrev = Integer.MAX_VALUE;
                for (int k = 0; k < 3; k++) {
                    if (k == j) continue;
                    minFromPrev = Math.min(minFromPrev, dp[i - 1][k]);
                }
                dp[i][j] = costToColor + minFromPrev;
            }
        }
        int finalMin = Integer.MAX_VALUE;
        for (int j = 0; j < 3; j++)
            finalMin = Math.min(finalMin, dp[m][j]);
        return finalMin;
    }

}
