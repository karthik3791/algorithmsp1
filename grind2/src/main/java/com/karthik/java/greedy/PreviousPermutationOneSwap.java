package com.karthik.java.greedy;

import java.util.ArrayDeque;
import java.util.Deque;

public class PreviousPermutationOneSwap {

    public int[] prevPermOpt1(int[] arr) {
        Deque<Integer> st = new ArrayDeque<>();
        int j = arr.length - 1;
        int right = -1;
        while (j >= 0) {
            while (!st.isEmpty() && arr[j] > arr[st.peek()]) {
                int temp = st.pop();
                if (right != -1 && arr[right] == arr[temp])
                    continue;
                right = temp;
            }
            if (right != -1) {
                int tmp = arr[j];
                arr[j] = arr[right];
                arr[right] = tmp;
                break;
            }
            st.push(j);
            j--;
        }
        return arr;
    }

    public static void main(String[] args) {
        PreviousPermutationOneSwap p = new PreviousPermutationOneSwap();
        int[] arr = {1, 9, 4, 6, 7};
        int[] res = p.prevPermOpt1(arr);
    }
}
