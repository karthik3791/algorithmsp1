package com.karthik.java.backtracking;

public class LIS {

    private int[] nums;

    private int dfs(int start, int prev) {
        if (start >= nums.length)
            return 0;
        int max = 0;
        for (int j = start; j < nums.length; j++)
            if (nums[j] > prev)
                max = Math.max(max, 1 + dfs(j + 1, nums[j]));
        return max;
    }


    public int lengthOfLIS(int[] nums) {
        this.nums = nums;
        return dfs(0, Integer.MIN_VALUE);
    }
}
