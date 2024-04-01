package com.karthik.java.graph;

import java.util.*;

public class MinimumEdgeReversalsSoEveryNodeReachable {
    class IntPair {
        Integer k;
        Integer v;

        IntPair(int a, int b) {
            this.k = a;
            this.v = b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, v);
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) return true;
            if (!(o instanceof IntPair)) return false;
            IntPair oth = (IntPair) o;
            return oth.k.equals(k) && oth.v.equals(v);
        }
    }

    Map<Integer, List<Integer>> graph;
    Map<Integer, List<Integer>> revGraph;
    Map<IntPair, Integer> memo;

    /*
     Because it says that the graph would form a tree if all the edges were bi-directional, what this means is
     1. Each node only has 1 incoming link to it. It simply can't have more than 1 incoming link.
     2. There are no cycles in the graph.
     */

    private int dfs(int node, int parent) {
        int ans = 0;
        IntPair ip = new IntPair(node, parent);
        if (memo.getOrDefault(ip, -1) != -1)
            return memo.get(ip);
        for (Integer v : graph.getOrDefault(node, new ArrayList<>())) {
            if (v != parent)
                ans += dfs(v, node);
        }
        for (Integer v : revGraph.getOrDefault(node, new ArrayList<>())) {
            if (v != parent)
                ans += (dfs(v, node) + 1);
        }
        memo.put(ip, ans);
        return memo.get(ip);
    }

    public int[] minEdgeReversals(int n, int[][] edges) {
        graph = new HashMap<>();
        revGraph = new HashMap<>();
        memo = new HashMap<>();
        for (int[] edge : edges) {
            graph.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(edge[1]);
            revGraph.computeIfAbsent(edge[1], v -> new ArrayList<>()).add(edge[0]);
        }
        int[] ans = new int[n];
        for (int i = 0; i < n; i++)
            ans[i] = dfs(i, -1);
        return ans;
    }

}
