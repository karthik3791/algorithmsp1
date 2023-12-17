package com.karthik.java;

import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class CheapestFlightsWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> (a[1] == b[1]) ? a[0] - b[0] : a[1] - b[1]);
        minPQ.offer(new int[]{0, 0, src,});

        while (!minPQ.isEmpty()) {
            int[] cur = minPQ.poll();
            int curDist = cur[0];
            int curHop = cur[1];
            int curIndex = cur[2];
            if (curHop > k) continue;
            for (int[] flightFromV : Arrays.stream(flights).filter(f -> f[0] == curIndex).collect(Collectors.toList())) {
                int w = flightFromV[1];
                int cost = flightFromV[2];
                if (dist[w] > curDist + cost) {
                    dist[w] = curDist + cost;
                    minPQ.offer(new int[]{dist[w], curHop + 1, w});
                }
            }
        }
        return dist[dst] == Integer.MAX_VALUE ? -1 : dist[dst];
    }

    public static void main(String[] args) {
        System.out.println(new CheapestFlightsWithinKStops().findCheapestPrice(4, new int[][]{{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}}, 0, 3, 1));
    }
}
