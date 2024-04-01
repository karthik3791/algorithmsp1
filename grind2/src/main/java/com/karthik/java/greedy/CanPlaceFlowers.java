package com.karthik.java.greedy;

public class CanPlaceFlowers {
    public boolean canPlaceFlowers(int[] flowerbed, int n) {
        boolean[][] dp = new boolean[flowerbed.length + 2][n + 1];
        for (int i = 0; i <= flowerbed.length + 1; i++)
            dp[i][0] = true;

        for (int i = flowerbed.length - 1; i >= 0; i--) {
            for (int j = 1; j <= n; j++) {
                if (flowerbed[i] == 1)
                    dp[i][j] = dp[i + 2][j];
                else {
                    if (i == flowerbed.length - 1 || flowerbed[i + 1] != 1)
                        dp[i][j] = dp[i + 2][j - 1];
                    dp[i][j] |= dp[i + 1][j];
                }
            }
        }
        return dp[0][n];
    }


    public boolean canPlaceFlowers2(int[] flowerbed, int n) {
        int count = 0;
        for (int i = 0; i < flowerbed.length; i++) {
            // Check if the current plot is empty.
            if (flowerbed[i] == 0) {
                // Check if the left and right plots are empty.
                boolean emptyLeftPlot = (i == 0) || (flowerbed[i - 1] == 0);
                boolean emptyRightPlot = (i == flowerbed.length - 1) || (flowerbed[i + 1] == 0);

                // If both plots are empty, we can plant a flower here.
                if (emptyLeftPlot && emptyRightPlot) {
                    flowerbed[i] = 1;
                    count++;
                    if (count >= n) {
                        return true;
                    }
                }
            }
        }
        return count >= n;
    }

}
