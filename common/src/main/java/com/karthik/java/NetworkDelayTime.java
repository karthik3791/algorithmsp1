package com.karthik.java;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class NetworkDelayTime {
    public int networkDelayTime(int[][] times, int n, int k) {
        int[] time = new int[n + 1];
        Arrays.fill(time, Integer.MAX_VALUE);
        time[0] = 0;
        time[k] = 0;
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minPQ.offer(new int[]{0, k});
        while (!minPQ.isEmpty()) {
            int[] cur = minPQ.poll();
            int curTime = cur[0];
            int curNode = cur[1];
            if (curTime > time[curNode]) continue;
            for (int[] w : Arrays.stream(times).filter(e -> e[0] == curNode).collect(Collectors.toList())) {
                int v = w[1];
                int t = w[2];
                if (time[v] > curTime + t) {
                    time[v] = curTime + t;
                    minPQ.offer(new int[]{time[v], v});
                }
            }
        }

        int max = 0;
        for (int i = 0; i <= n; i++) {
            max = Math.max(max, time[i]);
        }
        return max == Integer.MAX_VALUE ? -1 : max;
    }

}
