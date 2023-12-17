package com.karthik.java;

import java.util.ArrayList;
import java.util.List;

public class ClosestDessert {

    public int closestCost(int[] baseCosts, int[] toppingCosts, int target) {
        int n = baseCosts.length;
        int m = toppingCosts.length;
        int[][][] dp = new int[n][m + 1][target + 1];

        for (int i = 0; i < n; i++)
            for (int k = target; k >= 0; k--)
                dp[i][0][k] = baseCosts[i];

        for (int i = 0; i < n; i++)
            for (int j = 0; j <= m; j++)
                dp[i][j][0] = baseCosts[i];

        for (int i = 0; i < n; i++)
            for (int j = 1; j <= m; j++)
                for (int k = 1; k <= target; k++) {
                    int toppingCost = toppingCosts[j - 1];
                    List<Integer> possibleCandidates = new ArrayList<>();
                    if (i > 0)
                        possibleCandidates.add(dp[i - 1][j][k]);
                    possibleCandidates.add(dp[i][j - 1][k]); // Dont add any topping
                    possibleCandidates.add(toppingCost + dp[i][j - 1][Math.max(0, k - (toppingCost))]);
                    possibleCandidates.add(2 * toppingCost + dp[i][j - 1][Math.max(0, k - (toppingCost * 2))]);
                    dp[i][j][k] = computeNearestToTarget(possibleCandidates, k);
                }
        return dp[n - 1][m][target];
    }

    private int computeNearestToTarget(List<Integer> possibleCandidates, int target) {
        if (possibleCandidates.isEmpty()) throw new IllegalArgumentException("Can't pass empty List");
        int closest = possibleCandidates.get(0);
        int difference = Math.abs(target - closest);
        for (int i = 1; i < possibleCandidates.size(); i++) {
            if (Math.abs(possibleCandidates.get(i) - target) < difference) {
                closest = possibleCandidates.get(i);
                difference = Math.abs(possibleCandidates.get(i) - target);
            } else if (Math.abs(possibleCandidates.get(i) - target) == difference && possibleCandidates.get(i) < closest) {
                closest = possibleCandidates.get(i);
            }
        }
        return closest;
    }

    public static void main(String[] args) {
        /*
        [3738,5646,197,7652]
[5056]
9853
         */
        int[] baseCosts = {3738, 5646, 197, 7652};
        int[] toppingCosts = {5056};
        int target = 9853;
        System.out.println(new ClosestDessert().closestCost(baseCosts, toppingCosts, target));
    }


    private int result;

    private void backtrack(int[] toppingCosts, int toppingIndex, int target, int current) {
        if (Math.abs(result - target) > Math.abs(target - current))
            result = current;
        else if (Math.abs(result - target) == Math.abs(target - current) && current < result)
            result = current;
        else if (current >= target)
            return;
        if (toppingIndex >= toppingCosts.length) return;
        backtrack(toppingCosts, toppingIndex + 1, target, current); //Dont take the topping
        backtrack(toppingCosts, toppingIndex + 1, target, current + toppingCosts[toppingIndex]);
        backtrack(toppingCosts, toppingIndex + 1, target, current + 2 * toppingCosts[toppingIndex]);
    }


    public int closestCost2(int[] baseCosts, int[] toppingCosts, int target) {
        int result = Integer.MAX_VALUE;
        int n = baseCosts.length;
        int m = toppingCosts.length;
        for (int base : baseCosts) {
            backtrack(toppingCosts, 0, target, base);
        }
        return result;
    }
}
