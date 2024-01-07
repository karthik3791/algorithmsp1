package com.karthik.java.dp;

public class NumOfDiceRollWithTarget {
    public int numRollsToTarget(int n, int k, int target) {
        int[] prevRow = new int[target + 1];
        prevRow[0] = 1;
        for (int i = 1; i <= n; i++) {
            int[] tmp = new int[target + 1];
            for (int t = target; t >= 0; t--) {
                for (int j = 1; j <= k && j <= t; j++) {
                    tmp[t] = (tmp[t] + prevRow[t - j]) % 1000000007;
                }
            }
            prevRow = tmp;
        }
        return prevRow[target];
    }
}
