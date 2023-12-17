package com.karthik.java;

import java.util.Arrays;

class ShortestWindowSort {

    public static int sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;

        while (left < arr.length - 1 && arr[left] <= arr[left + 1]) {
            left++;
        }

        if (left == arr.length - 1)
            return 0;

        while (right > 0 && arr[right] >= arr[right - 1]) {
            right--;
        }

        int max = Arrays.stream(arr, left, right + 1).reduce(arr[left], Math::max);
        int min = Arrays.stream(arr, left, right + 1).reduce(arr[left], Math::min);

        while (left > 0 && arr[left - 1] > min)
            left--;

        while (right < arr.length - 1 && arr[right + 1] < max)
            right++;

        return right - left + 1;
    }
}
