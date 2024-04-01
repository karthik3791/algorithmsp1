package com.karthik.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AllPathsLeadsToDestination {

    Map<Integer, List<Integer>> graph;
    Map<Integer, Boolean> cache;

    private boolean dfs(int source, int destination, boolean[] visited) {
        if (cache.containsKey(source))
            return cache.get(source);
        List<Integer> neighbours = graph.getOrDefault(source, new ArrayList<>());
        if (neighbours.isEmpty())
            cache.put(source, source == destination);
        else {
            boolean res = true;
            for (int v : neighbours) {
                if (visited[v]) return false;
                visited[v] = true;
                res &= dfs(v, destination, visited);
                visited[v] = false;
            }
            cache.put(source, res);
        }
        return cache.get(source);
    }

    public boolean leadsToDestination(int n, int[][] edges, int source, int destination) {
        this.graph = new HashMap<>();
        this.cache = new HashMap<>();
        cache.put(destination, true);
        for (int[] edge : edges)
            graph.computeIfAbsent(edge[0], v -> new ArrayList<>()).add(edge[1]);
        boolean[] visited = new boolean[n];
        visited[source] = true;
        return dfs(source, destination, visited);
    }

    public static void main(String[] args) {
        int[][] edges = {{0, 1}, {0, 2}, {0, 3}, {0, 3}, {1, 2}, {1, 3}, {1, 4}, {2, 3}, {2, 4}, {3, 4}};
        AllPathsLeadsToDestination a = new AllPathsLeadsToDestination();
        System.out.println(a.leadsToDestination(5, edges, 0, 4));
    }

}
