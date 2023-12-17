package com.karthik.java;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MinCostToConnectAllPoints {

    public int minCostConnectPoints(int[][] points) {
        boolean[] marked = new boolean[points.length];
        int[] costGrid = new int[points.length];
        Arrays.fill(costGrid, Integer.MAX_VALUE);
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        minPQ.offer(new int[]{0, 0});
        costGrid[0] = 0;
        int cost = 0;
        while (!minPQ.isEmpty()) {
            int[] cur = minPQ.poll();
            int curCost = cur[0];
            if (curCost > costGrid[cur[1]]) continue;
            marked[cur[1]] = true;
            cost += curCost;
            for (int i = 0; i < points.length; i++) {
                if (i == cur[1] || marked[i])
                    continue;
                int manhattan = Math.abs(points[i][0] - points[cur[1]][0]) + Math.abs(points[i][1] - points[cur[1]][1]);
                if (manhattan < costGrid[i]) {
                    costGrid[i] = manhattan;
                    minPQ.offer(new int[]{manhattan, i});
                }
            }
        }
        return cost;
    }
}
