package com.karthik.java;

import java.util.*;

public class TopKFrequent {

    public int[] topKFrequentOld(int[] nums, int k) {
        final Map<Integer, Integer> numFreqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numFreqMap.put(nums[i], numFreqMap.getOrDefault(nums[i], 0) + 1);
        }
        PriorityQueue<Integer> maxPQ = new PriorityQueue<>((e1, e2) -> numFreqMap.get(e2) - numFreqMap.get(e1));
        for (Integer i : numFreqMap.keySet())
            maxPQ.offer(i);

        int[] res = new int[k];
        int l = 0;
        while (k > 0) {
            res[l++] = maxPQ.poll();
            k--;
        }
        return res;
    }


    public int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> numFreqMap = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            numFreqMap.put(nums[i], numFreqMap.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer> uniqueNumList = new ArrayList<>(numFreqMap.keySet());
        Collections.shuffle(uniqueNumList);
        System.out.println("Unique Number List" + uniqueNumList.toString());
        int[] uniqueNums = uniqueNumList.stream().mapToInt(Integer::intValue).toArray();
        quickSelect(uniqueNums, numFreqMap, 0, uniqueNums.length - 1, uniqueNums.length - k);
        return Arrays.copyOfRange(uniqueNums, uniqueNums.length - k, uniqueNums.length);
    }


    private void exch(int[] nums, int from, int to) {
        int tmp = nums[from];
        nums[from] = nums[to];
        nums[to] = tmp;
    }

    private int partition(int[] nums, Map<Integer, Integer> numFreqMap, int lo, int hi) {
        int i = lo;
        int j = hi + 1;
        int v = nums[lo];
        while (true) {
            while (numFreqMap.get(nums[++i]) < numFreqMap.get(v)) {
                if (i == hi)
                    break;
            }
            while (numFreqMap.get(nums[--j]) > numFreqMap.get(v)) {
                if (j == lo)
                    break;
            }
            if (i >= j)
                break;
            exch(nums, i, j);
        }
        exch(nums, lo, j);
        return j;
    }


    private int quickSelect(int[] nums, Map<Integer, Integer> numFreqMap, int lo, int hi, int k) {
        while (lo < hi) {
            int j = partition(nums, numFreqMap, lo, hi);
            if (j > k)
                hi = j - 1;
            else if (j < k)
                lo = j + 1;
            else
                return nums[j];
        }
        return nums[lo];
    }

    public static void main(String[] args) {
        TopKFrequent topK = new TopKFrequent();
        int[] nums = {1, 1, 1, 2, 2, 3};
        System.out.println("TopK Frequent Nums : " + Arrays.toString(topK.topKFrequent(nums, 2)));
        int[] nums2 = {1, 2};
        System.out.println("TopK Frequent Nums : " + Arrays.toString(topK.topKFrequent(nums2, 2)));
    }


    public int longestConsecutive(int[] nums) {
        Map<Integer, Integer> sequenceMap = new HashMap<>();
        for (int i : nums) {
            sequenceMap.put(i, 1);
            sequenceMap.put(i, sequenceMap.get(i) + sequenceMap.getOrDefault(i - 1, 0));
            sequenceMap.put(i, sequenceMap.get(i) + sequenceMap.getOrDefault(i + 1, 0));
        }
        return sequenceMap.values().stream().mapToInt(Integer::intValue).max().orElse(0);
    }


}
