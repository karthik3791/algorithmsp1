package com.karthik.java.greedy;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class ReduceArraySizeToHalf {
    public int minSetSize(int[] arr) {
        Map<Integer, Integer> freqMap = new HashMap<>();
        for (int i = 0; i < arr.length; i++)
            freqMap.put(arr[i], freqMap.getOrDefault(arr[i], 0) + 1);
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((a, b) -> freqMap.get(b) - freqMap.get(a));
        maxPQ.addAll(freqMap.keySet());

        int removedCount = 0;
        int setSize = 0;
        while (!maxPQ.isEmpty()) {
            removedCount += freqMap.get(maxPQ.poll());
            setSize += 1;
            if (removedCount > arr.length / 2)
                break;
        }
        return setSize;
    }
}
