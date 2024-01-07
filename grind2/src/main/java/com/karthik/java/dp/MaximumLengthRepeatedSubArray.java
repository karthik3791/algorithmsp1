package com.karthik.java.dp;

public class MaximumLengthRepeatedSubArray {

    public int findLength(int[] nums1, int[] nums2) {
        int[][] dp = new int[nums1.length + 1][nums2.length + 1];
        for (int i = nums1.length - 1; i >= 0; i--)
            for (int j = nums2.length - 1; j >= 0; j--) {
                if (nums1[i] == nums2[j])
                    dp[i][j] = 1 + dp[i + 1][j + 1];
                else dp[i][j] = 0;
            }
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums1.length; i++)
            for (int j = 0; j < nums2.length; j++)
                max = Math.max(max, dp[i][j]);
        return max;
    }
}
