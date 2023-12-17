package com.karthik.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumScoreAfterNOperations {
    private int gcd(int x, int y) {
        if (x > y)
            return gcd(y, x);
        int gcd = 1;
        for (int i = x; i >= 1; i--)
            if (x % i == 0 && y % i == 0) {
                gcd = i;
                break;
            }
        return gcd;
    }


    public int maxScore(int[] nums) {
        List<Integer> inputList = new ArrayList<>();
        for (int n : nums)
            inputList.add(n);
        Map<List<Integer>, Integer> memo = new HashMap<>();
        memo.put(new ArrayList<>(), 0); // Base Condition
        int play = 0;
        int res = 0;
        for (int len = 2; len <= inputList.size(); len += 2) {
            play++;
            for (int i = 0; i <= inputList.size() - len; i++) {
                for (int j = i + 1; j < inputList.size(); j++) {
                    List<Integer> currentList = inputList.subList(i, i + len);
                    int x = nums[i];
                    int y = nums[j];
                    List<Integer> subList = new ArrayList<>(currentList);
                    subList.removeAll(List.of(x, y));
                    memo.put(currentList, play * gcd(x, y) + memo.get(subList));
                    res = Math.max(res, memo.get(currentList));
                }
            }
        }
        return res;
    }

    public static void main(String[] args) {
        System.out.println(new MaximumScoreAfterNOperations().maxScore(new int[]{3, 4, 6, 8}));
    }
}
