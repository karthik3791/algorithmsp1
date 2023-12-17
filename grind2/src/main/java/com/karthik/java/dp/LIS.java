package com.karthik.java.dp;

public class LIS {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length + 1];
        dp[nums.length] = 0;
        int res = -1;
        for (int i = nums.length - 1; i >= 0; i--) {
            dp[i] = 1;
            for (int j = i + 1; j < nums.length; j++)
                if (nums[j] > nums[i]) {
                    dp[i] = Math.max(dp[i], 1 + dp[j]);
                }
            res = Math.max(res, dp[i]); // Remember that the LIS might be from any index. So must keep computing max
        }
        return res;
    }

    public static void main(String[] args) {
        LIS lis = new LIS();
        int[] nums = {10, 9, 2, 5, 3, 7, 101, 18};
        lis.lengthOfLIS(nums);
    }
}
