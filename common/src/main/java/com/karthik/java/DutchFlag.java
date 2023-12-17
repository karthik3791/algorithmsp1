package com.karthik.java;

import java.util.Arrays;

public class DutchFlag {

    private static void swap(int[] arr, int from, int to) {
        int tmp = arr[from];
        arr[from] = arr[to];
        arr[to] = tmp;
    }


    public static void sort(int[] arr) {
        int left = 0;
        int right = arr.length - 1;
        int cur = 0;
        while (cur <= right) {
            if (arr[cur] == 0)
                swap(arr, cur++, left++);
            else if (arr[cur] == 2)
                swap(arr, cur, right--);
            else
                cur++;
        }
    }


    public static void main(String[] args) {
        int[] input = {1, 0, 2, 1, 0};
        DutchFlag.sort(input);
        System.out.println(Arrays.toString(input));
    }

}
