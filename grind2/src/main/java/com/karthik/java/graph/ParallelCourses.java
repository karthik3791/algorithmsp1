package com.karthik.java.graph;

import java.util.*;

public class ParallelCourses {
    public int minimumSemesters(int n, int[][] relations) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[n];

        for (int[] relation : relations) {
            int u = relation[0] - 1;
            int v = relation[1] - 1;
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            inDegree[v]++;
        }
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++)
            if (inDegree[i] == 0)
                q.offer(i);
        int semester = 0;
        int topoCount = 0;
        while (!q.isEmpty()) {
            semester++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Integer u = q.poll();
                for (Integer v : graph.getOrDefault(u, new ArrayList<>())) {
                    inDegree[v]--;
                    if (inDegree[v] == 0)
                        q.offer(v);
                }
                topoCount++;
            }
        }
        return topoCount == n ? semester : -1;
    }
}
