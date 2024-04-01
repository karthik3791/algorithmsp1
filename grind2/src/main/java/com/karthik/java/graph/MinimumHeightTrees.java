package com.karthik.java.graph;

import java.util.*;

public class MinimumHeightTrees {
    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
        if (n < 2) {
            ArrayList<Integer> res = new ArrayList<>();
            for (int i = 0; i < n; i++)
                res.add(i);
            return res;
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(edge[1]);
            graph.computeIfAbsent(edge[1], v -> new ArrayList<>()).add(edge[0]);
        }
        Queue<Integer> q = new LinkedList<>();
        for (Map.Entry<Integer, List<Integer>> e : graph.entrySet())
            if (e.getValue().size() == 1)
                q.offer(e.getKey());

        int remainingNodes = n;

        while (remainingNodes > 2) {
            int noOfLeaves = q.size();
            remainingNodes -= noOfLeaves;
            for (int i = 0; i < noOfLeaves; i++) {
                Integer u = q.poll();
                for (int v : graph.getOrDefault(u, new ArrayList<>())) {
                    List<Integer> vNodes = graph.get(v);
                    vNodes.remove(u);
                    if (vNodes.size() == 1)
                        q.offer(v);
                }
            }
        }
        return new ArrayList<>(q);
    }

    public static void main(String[] args) {
        int[][] edges = {{1, 0}, {1, 2}, {1, 3}};
        MinimumHeightTrees mh = new MinimumHeightTrees();
        mh.findMinHeightTrees(4, edges);
    }
}
