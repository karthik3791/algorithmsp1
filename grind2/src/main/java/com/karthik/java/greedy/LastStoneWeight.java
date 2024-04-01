package com.karthik.java.greedy;

import java.util.PriorityQueue;

public class LastStoneWeight {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
        for (int stone : stones)
            pq.offer(stone);
        while (pq.size() >= 2) {
            int first = pq.poll();
            int second = pq.poll();
            if (second < first)
                pq.offer(first - second);
        }
        return pq.isEmpty() ? 0 : pq.poll();
    }
}
