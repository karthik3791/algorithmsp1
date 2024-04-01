package com.karthik.java.binarysearch;

public class BinarySearch {
    //nums is already ordered
    public int search(int[] nums, int target) {
        if (nums == null || nums.length == 0)
            return -1;
        int left = 0;
        int right = nums.length - 1;
        //In this case we allow left and right to point to same index (when we are down to the last index of array we are searching)
        while (left <= right) {
            //We do this instead of (left+right)/2 to avoid Integer Overflow which might happen when left and right might be HUGE values closer to Integer.MAX_VALUE.
            // By doing a subtraction and division first, we ensure that the value being added to left will actually not cause it to overflow.
            int mid = (left + (right - left) / 2);
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                //Search the left half
                right = mid - 1;
            else
                //Search the right half
                left = mid + 1;
        }
        /*
        A key point to remember is that once Binary search completes. left > right
        left can go from 0 to initial right +1 (if right = n-1 initially, then left can go upto n)
        right can go from (left -1) to -1 (if left was 0 initially, it can go upto -1)
        Therefore, we need to be careful when just picking up nums[left] / nums[right] POST the loop.
         */

        return -1;
    }
}
