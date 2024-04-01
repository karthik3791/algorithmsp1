package com.karthik.java.graph;

import java.util.*;

public class NetworkDelayTime {

    class Pair<K, V> {
        K key;
        V value;

        Pair(K a, V b) {
            this.key = a;
            this.value = b;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<Pair<Integer, Integer>>> graph = new HashMap<>();
        for (int[] time : times)
            graph.computeIfAbsent(time[0], v -> new ArrayList<>()).add(new Pair<Integer, Integer>(time[1], time[2]));

        PriorityQueue<Pair<Integer, Integer>> pq = new PriorityQueue<>((v1, v2) -> v1.value - v2.value);
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        pq.add(new Pair<Integer, Integer>(k, 0));

        while (!pq.isEmpty()) {
            Pair<Integer, Integer> vertexWithDistanceFromSource = pq.poll();
            if (vertexWithDistanceFromSource.value >= dist[vertexWithDistanceFromSource.key - 1])
                continue;
            dist[vertexWithDistanceFromSource.key - 1] = vertexWithDistanceFromSource.value;
            for (Pair<Integer, Integer> v : graph.getOrDefault(vertexWithDistanceFromSource.key, new ArrayList<>()))
                pq.offer(new Pair<>(v.key, vertexWithDistanceFromSource.value + v.value));
        }

        int res = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            if (dist[i] == Integer.MAX_VALUE)
                return -1;
            res = Math.max(res, dist[i]);
        }
        return res;
    }
}
