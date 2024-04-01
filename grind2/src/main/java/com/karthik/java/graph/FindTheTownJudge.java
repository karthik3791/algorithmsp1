package com.karthik.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FindTheTownJudge {
    public int findJudge(int n, int[][] trust) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        Map<Integer, Integer> inDegreeMap = new HashMap<>();
        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
            inDegreeMap.put(i, 0);
        }
        for (int[] edge : trust) {
            graph.get(edge[0] - 1).add(edge[1] - 1);
            inDegreeMap.computeIfPresent(edge[1] - 1, (v, existing) -> existing + 1);
        }
        for (Map.Entry<Integer, List<Integer>> e : graph.entrySet()) {
            if (e.getValue().size() == 0 && inDegreeMap.get(e.getKey()) == n - 1)
                return e.getKey() + 1;
        }
        return -1;
    }
}
