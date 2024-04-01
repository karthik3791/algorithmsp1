package com.karthik.java.graph;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class CheapestFlightsWithinKStops {

    /*
      This is naive Bellman Ford algorithm that uses DP space optimization.
      It might seem like we are relaxing all edges K+1 times (similar to Bellman Ford) where we relax all E edges V times.
      However, a point to note. In Bellman Ford, each time when we relax, we use the previous iteration and relax edges
      ensuring that the atmost edges criteria is not violated.

      For the normal Bellman Ford, we would actually not care about the atmost edges criteria and instead simply start relaxing all edges each time.
     */
    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        int[] prev = new int[n];
        Arrays.fill(prev, Integer.MAX_VALUE);
        prev[src] = 0;
        for (int i = 1; i <= k + 1; i++) {
            int[] cur = Arrays.copyOf(prev, prev.length);
            for (int[] flight : flights) {
                int u = flight[0];
                int v = flight[1];
                int cost = flight[2];
                if (prev[u] != Integer.MAX_VALUE)
                    cur[v] = Math.min(cur[v], prev[u] + cost);
            }
            prev = cur;
        }
        return prev[dst] == Integer.MAX_VALUE ? -1 : prev[dst];
    }


    /*
    What's obvious from below is that for the current iteration, we only care about
    1. Vertices in previous iteration which are reachable from src and
    2. From those vertices reachable from src in previous iteration, we should have an edge to the current vertex for current iteration.
    This can be simplified.
     */
    public int findCheapestPriceOld(int n, int[][] flights, int src, int dst, int k) {
        Map<Integer, Map<Integer, Integer>> graph = new HashMap<>();
        for (int[] flight : flights)
            graph.computeIfAbsent(flight[0], v -> new HashMap<Integer, Integer>()).put(flight[1], flight[2]);

        int[] prev = new int[n];
        Arrays.fill(prev, Integer.MAX_VALUE);
        prev[src] = 0;

        for (int i = 1; i <= k + 1; i++) {
            int[] cur = Arrays.copyOf(prev, prev.length);
            for (int j = 0; j < n; j++)
                for (int l = 0; l < n; l++) {
                    if (prev[l] != Integer.MAX_VALUE && graph.getOrDefault(l, new HashMap<>()).containsKey(j))
                        cur[j] = Math.min(cur[j], prev[l] + graph.get(l).get(j));
                }
            prev = cur;
        }
        return prev[dst] == Integer.MAX_VALUE ? -1 : prev[dst];
    }

    public static void main(String[] args) {
        int[][] flights = {{0, 1, 100}, {1, 2, 100}, {2, 0, 100}, {1, 3, 600}, {2, 3, 200}};
        CheapestFlightsWithinKStops c = new CheapestFlightsWithinKStops();
        c.findCheapestPriceOld(4, flights, 0, 3, 1);
    }
}
