package com.karthik.java.greedy;

public class JumpGameII {
    public int jumpDP(int[] nums) {
        int[] dp = new int[nums.length];
        for (int i = nums.length - 2; i >= 0; i--) {
            int noJumps = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[i]; j++) {
                if (i + j >= nums.length - 1)
                    noJumps = 1;
                else if (dp[i + j] != Integer.MAX_VALUE)
                    noJumps = Math.min(noJumps, 1 + dp[i + j]);
            }
            dp[i] = noJumps;
        }
        return dp[0];
    }

    public int jump(int[] nums) {
        int curMax = 0;
        int i = 0;
        int level = 0;
        //Similar to while(!q.isEmpty())
        while (i <= curMax) {
            int farthest = curMax;
            //Similar to taking everything from queue right now.
            for (; i <= curMax; i++) {
                farthest = Math.max(farthest, nums[i] + i);
                if (farthest >= nums.length - 1) return level + 1;
            }
            level++;
            curMax = farthest;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 3, 1, 1, 4};
        JumpGameII j = new JumpGameII();
        System.out.println(j.jumpDP(nums));
    }
}
