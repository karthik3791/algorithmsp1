package com.karthik.java.dp;

import java.util.HashMap;
import java.util.Map;

public class DeleteAndEarn {

    public int deleteAndEarn2(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxNum = 0;
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + num);
            maxNum = Math.max(maxNum, num);
        }
        int[] dp = new int[maxNum + 1];
        dp[0] = 0;
        dp[1] = freqMap.getOrDefault(1, 0);
        for (int num = 2; num <= maxNum; num++)
            dp[num] = Math.max(dp[num - 1], freqMap.getOrDefault(num, 0) + dp[num - 2]);
        return dp[maxNum];
    }

    public int deleteAndEarn(int[] nums) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        int maxNum = 0;
        for (int num : nums) {
            freqMap.put(num, freqMap.getOrDefault(num, 0) + num);
            maxNum = Math.max(maxNum, num);
        }
        int prev, cur;
        prev = 0;
        cur = freqMap.getOrDefault(1, 0);
        for (int num = 2; num <= maxNum; num++) {
            int tmp = cur;
            cur = Math.max(cur, freqMap.getOrDefault(num, 0) + prev);
            prev = tmp;
        }
        return cur;
    }
}
