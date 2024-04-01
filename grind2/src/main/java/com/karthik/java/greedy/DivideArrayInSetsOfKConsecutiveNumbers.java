package com.karthik.java.greedy;

import java.util.TreeMap;

public class DivideArrayInSetsOfKConsecutiveNumbers {
    public boolean isPossibleDivide(int[] nums, int k) {
        TreeMap<Integer, Integer> freqMap = new TreeMap<>((k1, k2) -> k1 - k2);
        for (int i = 0; i < nums.length; i++)
            freqMap.put(nums[i], freqMap.getOrDefault(nums[i], 0) + 1);

        while (!freqMap.isEmpty()) {
            int smallest = freqMap.firstKey();
            for (int i = 0; i < k; i++) {
                if (freqMap.containsKey(smallest + i)) {
                    freqMap.put(smallest + i, freqMap.get(smallest + i) - 1);
                    if (freqMap.get(smallest + i) == 0)
                        freqMap.remove(smallest + i);
                } else
                    return false;
            }
        }
        return true;
    }
}
