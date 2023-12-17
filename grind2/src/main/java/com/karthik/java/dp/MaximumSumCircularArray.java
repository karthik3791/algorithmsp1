package com.karthik.java.dp;

public class MaximumSumCircularArray {

    // Maximum Subarray sum in a circular array is
    // 1. Either maxium subarray sum in normal array (Kadane's Algo) OR
    // 2. Total Sum of Array - Min Subarray sum of normal array
    // Answer is Max(1 or 2)
    public int maxSubarraySumCircular(int[] nums) {
        int maxSum = nums[0]; //7
        int currentMaxSum = nums[0]; //7
        int minSum = nums[0]; //-3
        int currentMinSum = nums[0]; //2
        int totalSum = nums[0]; //7

        for (int i = 1; i < nums.length; i++) {
            currentMaxSum = Math.max(currentMaxSum + nums[i], nums[i]);
            maxSum = Math.max(currentMaxSum, maxSum);
            currentMinSum = Math.min(currentMinSum + nums[i], nums[i]);
            minSum = Math.min(currentMinSum, minSum);
            totalSum += nums[i];
        }
        return maxSum > 0 ? Math.max(maxSum, totalSum - minSum) : maxSum;
    }

    public static void main(String[] args) {
        int[] nums = {5, -3, 5};
        MaximumSumCircularArray mc = new MaximumSumCircularArray();
        System.out.println(mc.maxSubarraySumCircular(nums));
    }
}
