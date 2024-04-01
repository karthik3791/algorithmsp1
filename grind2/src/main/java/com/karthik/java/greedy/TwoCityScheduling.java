package com.karthik.java.greedy;

import java.util.Arrays;

public class TwoCityScheduling {

    public int twoCitySchedCost(int[][] costs) {
        Arrays.sort(costs, (intArray1, intArray2) -> (intArray1[0] - intArray1[1]) - (intArray2[0] - intArray2[1]));
        int cost = 0;
        int N = costs.length / 2;
        for (int i = 0; i < N; i++) {
            cost += costs[i][0] + costs[i + N][1];
        }
        return cost;
    }

    public int twoCitySchedCostDP(int[][] costs) {
        /*
          State Variables
           1. No. of people left to arrive in City A -> i
           2. No. of people left to arrive in City B -> j
           3. No. of people left to travel -> k
           State Reduction
           Can we write k from i and j ? k = 2N -i -j ?
           dp[i][j] = min { if(i>0) cost[2N -i -j][0] + dp[i-1][j] else Integer.MAX_VALUE, if(j>0) cost[2N-i-j][1]+dp[i][j-1] else Integer.MAX_VALUE}
         */
        int N = costs.length / 2;
        int[][] dp = new int[N + 1][N + 1];
        for (int i = 0; i <= N; i++)
            for (int j = 0; j <= N; j++) {
                if (i == 0 && j == 0) continue;
                int costA = Integer.MAX_VALUE;
                int costB = Integer.MAX_VALUE;
                if (i > 0)
                    costA = costs[2 * N - i - j][0] + dp[i - 1][j];
                if (j > 0)
                    costB = costs[2 * N - i - j][1] + dp[i][j - 1];
                dp[i][j] = Math.min(costA, costB);
            }
        return dp[N][N];
    }
}
