package com.karthik.java.dp;

public class PaintHouseII {
    public int minCostII(int[][] costs) {
        int m = costs.length;
        int n = costs[0].length;
        int[] prevRow = new int[n];
        for (int i = 1; i <= m; i++) {
            int[] curRow = new int[n];
            for (int j = 0; j < n; j++) {
                int minFromPrevRow = Integer.MAX_VALUE;
                for (int k = 0; k < n; k++) {
                    if (k == j) continue;
                    minFromPrevRow = Math.min(minFromPrevRow, prevRow[k]);
                }
                curRow[j] = costs[i - 1][j] + minFromPrevRow;
            }
            prevRow = curRow;
        }

        int finalMin = Integer.MAX_VALUE;
        for (int j = 0; j < n; j++)
            finalMin = Math.min(finalMin, prevRow[j]);
        return finalMin;
    }
}
