package com.karthik.java.graph;

import java.util.*;

public class CourseScheduleII {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, List<Integer>> graph = new HashMap<>();
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            graph.computeIfAbsent(prerequisite[1], v -> new ArrayList<>()).add(prerequisite[0]);
            inDegree[prerequisite[0]]++;
        }
        List<Integer> topoOrder = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < inDegree.length; i++)
            if (inDegree[i] == 0)
                q.offer(i);
        while (!q.isEmpty()) {
            int u = q.poll();
            topoOrder.add(u);
            for (int v : graph.getOrDefault(u, new ArrayList<>())) {
                inDegree[v]--;
                if (inDegree[v] == 0)
                    q.offer(v);
            }
        }
        if (topoOrder.size() != numCourses)
            return new int[]{};
        int[] res = new int[numCourses];
        for (int i = 0; i < topoOrder.size(); i++)
            res[i] = topoOrder.get(i);
        return res;
    }

    public static void main(String[] args) {
        int[][] preReq = {{1, 0}};
        CourseScheduleII c = new CourseScheduleII();
        System.out.println(Arrays.toString(c.findOrder(2, preReq)));

    }
}
