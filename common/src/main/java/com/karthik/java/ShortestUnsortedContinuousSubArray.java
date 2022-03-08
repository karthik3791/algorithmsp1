package com.karthik.java;

public class ShortestUnsortedContinuousSubArray {

    public int findUnsortedSubarray2(int[] nums) {
        int currentMinValue, currentMaxValue, startIndex, endIndex;
        currentMaxValue = currentMinValue = nums[nums.length / 2];
        startIndex = Integer.MAX_VALUE;
        endIndex = Integer.MIN_VALUE;
        for (int i = nums.length / 2 - 1, j = nums.length / 2 + 1; i >= 0; i--, j++) {
            if (nums[i] > currentMinValue) {
                startIndex = Math.min(i, startIndex);
                int possibleEndIndex = -1;
                for (int k = i + 1; k < j; k++) {
                    if (nums[k] < nums[i]) {
                        possibleEndIndex = k;
                    }
                }
                endIndex = Math.max(endIndex, possibleEndIndex);
            }
            currentMinValue = Math.min(nums[i], currentMinValue);
            currentMaxValue = Math.max(nums[i], currentMaxValue);
            if (j < nums.length) {
                if (nums[j] < currentMaxValue) {
                    endIndex = Math.max(j, endIndex);
                    int possibleStartIndex = -1;
                    for (int k = j - 1; k >= i; k--) {
                        if (nums[k] > nums[j]) {
                            possibleStartIndex = k;
                        }
                    }
                    startIndex = Math.min(startIndex, possibleStartIndex);
                }
                currentMinValue = Math.min(nums[j], currentMinValue);
                currentMaxValue = Math.max(nums[j], currentMaxValue);
            }
        }
        return startIndex == Integer.MAX_VALUE ? 0 : (endIndex - startIndex) + 1;
    }

    public static void main(String[] args) {
        int[] nums = {2, 6, 4, 8, 10, 9, 15};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums));
        int[] nums2 = {2, 1, 1, 1, 1};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums2));
        int[] nums3 = {2, 3, 3, 2, 4};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums3));
        int[] nums4 = {1, 2, 3, 5, 4};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums4));
        int[] nums5 = {2, 3, 3, 2, 4};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums5));
        int[] nums6 = {1, 2, 5, 4, 3};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums6));
        int[] nums7 = {1, 2, 4, 5, 3};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums7));
        int[] nums8 = {0, 3, 5, 2, 1, 4, 6, 7, 8, 9};
        System.out.println(new ShortestUnsortedContinuousSubArray().findUnsortedSubarray2(nums8));
    }
}
