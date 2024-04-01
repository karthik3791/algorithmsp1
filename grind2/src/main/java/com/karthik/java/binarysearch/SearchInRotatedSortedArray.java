package com.karthik.java.binarysearch;

public class SearchInRotatedSortedArray {


    /*
     When dealing with Binary Search, the variation fundamentally happens across 2 dimensions
     1. while(left <= right) vs. while(left < right)
        The key point to remember is that when we use while(left<=right), that means we do one final iteration of search with an array of size1 after which EITHER left becomes mid+1 or right becomes mid-1;
        Note, if we use while(left<=right) but left= mid and right = mid, we may end up with infinite loop. So need to be careful.
        Secondly, only when we have left<=right, the final condition when left becomes mid+1 or right becomes mid-1, the range of motion of left is 0 to n and for right is -1 to n-1
        Also, only when this is used, after the while loop exits, left will point to ceiling and right will point to floor.
        If we use while(left<right), we will NOT do the final iteration and in this case left will just go 0 to n-1 and right from n-1 to 0.
        Also, when we combine this with left=mid+1 or right = mid-1, we will NOT find the ceiling or floor.
     2. left = mid-1;  vs  left = mid;
        right= mid+1;  vs. right = mid;
     */

    /*
    Approach 1 : 2 Binary Searches.
    Find the Pivot Index i.e. the index with the smallest element in the array using Binary search.
    Once that index is found, search 0 to pivotIndex-1 and pivotIndex to n-1 using Binary search each respectively.

    To find the pivotIndex, we will apply binary search by comparing the element at mid with the last element.
    If nums[mid] > nums[n-1], then it means that the pivotIndex is to the right because the drop is going to be in the right side.
     else (i.e. nums[mid] <= nums[n-1]), this clearly means the pivotIndex is to the left.

     We will follow the variation to have left<=right because we will eventually determine that the pivotIndex is left post while loop exit.
     */

    private int binarySearch(int[] nums, int target, int start, int end) {
        int left = start;
        int right = end;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                right = mid - 1;
            else left = mid + 1;
        }
        return -1;
    }

    public int search(int[] nums, int target) {
        int n = nums.length;
        int left = 0;
        int right = n - 1;
        int pivotIndex = 0;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[n - 1])
                left = mid + 1;
            else right = mid - 1;
        }
        pivotIndex = left;
        int leftSearch = binarySearch(nums, target, 0, pivotIndex - 1);
        return leftSearch != -1 ? leftSearch : binarySearch(nums, target, pivotIndex, n - 1);
    }

    /*
     Approach 2 : Single Binary Search.
     The key idea here is that each time when we find a mid, and look at the left and right sub arrays, atleast one of them MUST be sorted.
     Special case, when mid is pivotIndex, then both left and right subArrays are sorted.

     Essentially, we will use this to discard that subarray and move to the other one.
     */

    public int search2(int[] nums, int target) {
        int n = nums.length;
        int left = 0, right = n - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] >= nums[left]) { //This means the left subarray is sorted. If we just use > instead of >= for the final iteration, we will hit into infinite loop.
                if (target >= nums[left] && target < nums[mid])
                    right = mid - 1;
                else
                    left = mid + 1;
            } else if (nums[mid] < nums[right]) {//This means right subarray is sorted. Here we do not need <= because the previous check will already handle case when left==right
                if (nums[mid] < target && target <= nums[right])
                    left = mid + 1;
                else
                    right = mid - 1;
            }
        }
        return -1;

    }
}
