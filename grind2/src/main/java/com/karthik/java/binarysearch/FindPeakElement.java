package com.karthik.java.binarysearch;

public class FindPeakElement {
    public int findPeakElement(int[] nums) {
        int left = 0, right = nums.length - 1;
        /*
         By choosing left<right, we guarantee that left now goes between 0 to n-1 (0 to n-2 inside the loop and finally to n-1 when exiting loop).
         Similarly, right goes from n-1 to 0 (n-1 to 1 inside loop and finally to 0 when exiting loop)
         Mid is computed within the while loop and when mid is computed, left is between 0 to n-2
         Similarly, right is between 1 to n-1
         Mid always follows left, so is also between 0 and n-2. Therefore, we can use nums[mid] and nums[mid+1]
         */
        while (left < right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[mid + 1])
                // In problems where mid COULD be the answer but we do not know ywt, If we go to mid-1, We must ensure we will be able to move left back to mid.
                // For left to finally point to solution, we must allow left<=right
                right = mid;
            else
                left = mid + 1;
        }
        return left;
    }
}
