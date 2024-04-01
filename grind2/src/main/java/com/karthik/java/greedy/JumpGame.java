package com.karthik.java.greedy;

public class JumpGame {

    public boolean canJump(int[] nums) {
        boolean[] dp = new boolean[nums.length];
        dp[nums.length - 1] = true;
        for (int i = nums.length - 2; i >= 0; i--) {
            boolean canJump = false;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= nums.length - 1) {
                    canJump = true;
                    break;
                }
                canJump |= dp[i + j];
            }
            dp[i] = canJump;
        }
        return dp[0];
    }

    public boolean canJumpGreedy(int[] nums) {
        int curMax = 0;
        int i = 0;
        if (nums.length < 2) return true;
        while (i <= curMax) {
            int farthest = curMax;
            for (; i <= curMax; i++) {
                farthest = Math.max(farthest, i + nums[i]);
                if (farthest >= nums.length - 1) return true;
            }
            curMax = farthest;
        }
        return false;
    }
}
