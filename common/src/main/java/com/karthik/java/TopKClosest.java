package com.karthik.java;

import java.util.ArrayList;
import java.util.List;

public class TopKClosest {

    private int binarySearch(int[] arr, int x) {
        int low = 0;
        int high = arr.length - 1;
        while (low <= high) {
            int mid = (low + high) / 2;
            if (arr[mid] == x) {
                return mid;
            }
            if (arr[mid] < x)
                low = mid + 1;
            else
                high = mid - 1;
        }
        if (low > 0)
            return low - 1;
        else
            return low;
    }

    public List<Integer> findClosestElements(int[] arr, int k, int x) {
        List<Integer> res = new ArrayList<>();
        int a = binarySearch(arr, k);
        int left = a, right = a + 1;
        for (int i = 0; i < k; i++) {
            if (left >= 0 && right < arr.length) {
                if (Math.abs(arr[left] - x) <= Math.abs(arr[right] - x))
                    res.add(0, arr[left--]);
                else
                    res.add(arr[right++]);
            } else if (left >= 0)
                res.add(0, arr[left--]);
            else if (right < arr.length)
                res.add(arr[right++]);
        }
        return res;
    }

    public static void main(String[] args) {
        int[] arr = {0, 1, 1, 1, 2, 3, 6, 7, 8, 9};

        System.out.println(new TopKClosest().findClosestElements(arr, 9, 4));
    }
}