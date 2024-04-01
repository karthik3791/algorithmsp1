package com.karthik.java.graph;

import java.util.*;

public class AllPathsFromSourceToTarget {


    public List<List<Integer>> allPathsSourceTarget(int[][] graph) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                adjMap.computeIfAbsent(i, k -> new ArrayList<>()).add(graph[i][j]);
        boolean[] visited = new boolean[graph.length];
        visited[0] = true;
        List<Integer> curPath = new ArrayList<>();
        curPath.add(0);
        List<List<Integer>> res = new ArrayList<>();
        dfs(adjMap, 0, visited, curPath, res);
        return res;
    }

    private void dfs(Map<Integer, List<Integer>> adjMap, int source, boolean[] visited, List<Integer> curPath, List<List<Integer>> res) {
        if (source == visited.length - 1) {
            res.add(new ArrayList<>(curPath));
            return;
        }
        for (int v : adjMap.getOrDefault(source, new ArrayList<>())) {
            if (!visited[v]) {
                visited[v] = true;
                curPath.add(v);
                dfs(adjMap, v, visited, curPath, res);
                curPath.remove(curPath.size() - 1);
                visited[v] = false;
            }
        }
    }


    /*
      Conclusion, we simply cannot use Stack approach, the moment we need to Backtrack.
      For Backtracking, it is simply preferable to use recursion as opposed to via Stack and iteration
     */
    public List<List<Integer>> allPathsSourceTargetWRONG(int[][] graph) {
        Map<Integer, List<Integer>> adjMap = new HashMap<>();
        for (int i = 0; i < graph.length; i++)
            for (int j = 0; j < graph[i].length; j++)
                adjMap.computeIfAbsent(i, k -> new ArrayList<>()).add(graph[i][j]);

        List<List<Integer>> res = new ArrayList<>();
        boolean[] visited = new boolean[graph.length];
        Deque<List<Integer>> st = new ArrayDeque<>();
        st.push(List.of(0));
        while (!st.isEmpty()) {
            List<Integer> cur = st.pop();
            int u = cur.get(cur.size() - 1);
            if (visited[u])
                continue;
            visited[u] = true;
            if (u == graph.length - 1) {
                res.add(cur);
                visited[u] = false;
            } else {
                for (int v : adjMap.get(u)) {
                    List<Integer> newCur = new ArrayList<>(cur);
                    newCur.add(v);
                    st.push(newCur);
                }
            }
            while (!st.isEmpty() && st.peek().size() < cur.size() && st.peek().get(st.peek().size() - 1) != cur.get(cur.size() - 1)) {
                visited[cur.get(cur.size() - 1)] = false;
                cur.remove(cur.size() - 1);
            }
        }
        return res;
    }


    public static void main(String[] args) {
        //int[][] graph = {{4, 3, 1}, {3, 2, 4}, {3}, {4}, {}};
        int[][] graph = {{1, 2}, {3}, {3}, {}};
        AllPathsFromSourceToTarget a = new AllPathsFromSourceToTarget();
        a.allPathsSourceTarget(graph);
    }

}
