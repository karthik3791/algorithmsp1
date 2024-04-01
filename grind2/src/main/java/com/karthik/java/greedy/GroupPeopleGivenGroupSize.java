package com.karthik.java.greedy;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class GroupPeopleGivenGroupSize {

    public List<List<Integer>> groupThePeople(int[] groupSizes) {
        PriorityQueue<Integer> minPQ = new PriorityQueue<>((i, j) -> groupSizes[i] - groupSizes[j]);
        for (int i = 0; i < groupSizes.length; i++)
            minPQ.offer(i);
        List<List<Integer>> res = new ArrayList<>();
        if (minPQ.isEmpty())
            return res;
        List<Integer> curGroup = new ArrayList<>();
        int a = minPQ.poll();
        curGroup.add(a);
        int curGroupSize = groupSizes[a];
        while (!minPQ.isEmpty()) {
            while (!minPQ.isEmpty()) {
                if (groupSizes[minPQ.peek()] == curGroupSize && curGroup.size() < curGroupSize)
                    curGroup.add(minPQ.poll());
                else {
                    res.add(curGroup);
                    curGroup = new ArrayList<>();
                    curGroup.add(minPQ.peek());
                    curGroupSize = groupSizes[minPQ.poll()];
                }
            }
        }
        if (!curGroup.isEmpty())
            res.add(curGroup);
        return res;
    }
}
