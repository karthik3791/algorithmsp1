package com.karthik.java.graph;

import java.util.*;

public class FindIfPathExists {

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int[] edge : edges) {
            adjMap.computeIfAbsent(edge[0], j -> new ArrayList<>()).add(edge[1]);
            adjMap.computeIfAbsent(edge[1], j -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(source);
        /*
         When we use a stack to do DFS, we can only mark a node as visited when we take the value out of the stack.
         Contrast this with the traditional recursive way of writing DFS where we can mark the node as visited even before calling the dfs.
         Essentially, adding something to the stack is NOT the same as immediately calling the function
         */
        while (!stack.isEmpty()) {
            int s = stack.pop();
            if (visited[s]) continue;
            visited[s] = true;
            if (s == destination)
                return true;
            for (int v : adjMap.get(s))
                stack.push(v);
        }
        return false;
    }

    public boolean validPath2(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int[] edge : edges) {
            adjMap.computeIfAbsent(edge[0], j -> new ArrayList<>()).add(edge[1]);
            adjMap.computeIfAbsent(edge[1], j -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        visited[source] = true;
        return dfs(adjMap, visited, source, destination);
    }

    private boolean dfs(Map<Integer, List<Integer>> graph, boolean[] visited, int source, int destination) {
        if (source == destination) return true;
        for (int v : graph.get(source)) {
            if (!visited[v]) {
                visited[v] = true;
                if (dfs(graph, visited, v, destination))
                    return true;
            }
        }
        return false;
    }

    public boolean validPath3(int n, int[][] edges, int source, int destination) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int[] edge : edges) {
            adjMap.computeIfAbsent(edge[0], u -> new ArrayList<>()).add(edge[1]);
            adjMap.computeIfAbsent(edge[1], u -> new ArrayList<>()).add(edge[0]);
        }
        boolean[] visited = new boolean[n];
        visited[source] = true;

        Queue<Integer> q = new LinkedList<>();
        q.offer(source);

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int u = q.poll();
                if (u == destination)
                    return true;
                for (int v : adjMap.getOrDefault(u, new ArrayList<>())) {
                    if (!visited[v]) {
                        visited[v] = true;
                        q.offer(v);
                    }
                }
            }
        }

        return false;
    }
}
