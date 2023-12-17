package com.karthik.java;

import java.util.Arrays;

public class SplitArrayLargestSum {

    private Integer[][] memo;

    private int splitArraySumWithoutMemo(int[] nums, int currentIndex, int noOfCuts, int currentSum) {
        if (currentIndex == nums.length)
            if (noOfCuts > 0)
                return Integer.MIN_VALUE;
            else if (noOfCuts == 0)
                return 0;

        if (noOfCuts == 0)
            return Arrays.stream(nums, currentIndex, nums.length).sum();

        int maxSumIfCutHere = splitArraySumWithoutMemo(nums, currentIndex + 1, noOfCuts - 1, 0);
        int maxSumIfNoCutHere = splitArraySumWithoutMemo(nums, currentIndex + 1, noOfCuts, currentSum + nums[currentIndex]);

        int largestSumIfCut = Integer.MIN_VALUE;
        if (maxSumIfCutHere != Integer.MIN_VALUE)
            largestSumIfCut = Math.max(maxSumIfCutHere, currentSum + nums[currentIndex]);

        if (maxSumIfNoCutHere != Integer.MIN_VALUE)
            largestSumIfCut = Math.min(largestSumIfCut, maxSumIfNoCutHere);

        return largestSumIfCut;
    }


    private int splitArraySum(int[] nums, int currentIndex, int noOfCuts, int currentSum) {
        if (currentIndex == nums.length)
            if (noOfCuts > 0)
                return Integer.MIN_VALUE;
            else if (noOfCuts == 0)
                return 0;

        if (noOfCuts == 0)
            return Arrays.stream(nums, currentIndex, nums.length).sum();

        if (memo[currentIndex][noOfCuts] == null) {
            int maxSumIfCutHere = splitArraySum(nums, currentIndex + 1, noOfCuts - 1, 0);
            int maxSumIfNoCutHere = splitArraySum(nums, currentIndex + 1, noOfCuts, currentSum + nums[currentIndex]);

            int result = Integer.MIN_VALUE;
            if (maxSumIfCutHere != Integer.MIN_VALUE)
                result = Math.max(maxSumIfCutHere, currentSum + nums[currentIndex]);

            if (maxSumIfNoCutHere != Integer.MIN_VALUE)
                result = Math.min(result, maxSumIfNoCutHere);

            memo[currentIndex][noOfCuts] = result;
        }

        return memo[currentIndex][noOfCuts];
    }


    public int splitArray(int[] nums, int m) {
        this.memo = new Integer[nums.length][m];
        return splitArraySum(nums, 0, m - 1, 0);
    }

    public static void main(String[] args) {
        int[] input1 = {7, 2, 5, 10, 8};
        int[] input2 = {1, 2, 3, 4, 5};
        int[] input3 = {1, 4, 4};
        int[] input4 = {10, 5, 13, 4, 8, 4, 5, 11, 14, 9, 16, 10, 20, 8};

        SplitArrayLargestSum s = new SplitArrayLargestSum();

        System.out.println(s.splitArray(input1, 2));
        System.out.println(s.splitArray(input2, 2));
        System.out.println(s.splitArray(input3, 3));
        System.out.println(s.splitArray(input4, 8));
        /*
        [7,2,5,10,8]
2
[1,2,3,4,5]
2
[1,4,4]
3
         */
    }

}
