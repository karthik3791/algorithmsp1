package com.karthik.java;

import java.util.Arrays;
import java.util.PriorityQueue;

public class MaximumMinimumPath {
    public int maximumMinimumPath(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        PriorityQueue<int[]> maxPQ = new PriorityQueue<>((a, b) -> grid[b[0]][b[1]] - grid[a[0]][a[1]]);
        visited[0][0] = true;
        maxPQ.offer(new int[]{0, 0});
        int[][] dirs = {{1, 0}, {0, -1}, {-1, 0}, {0, 1}};
        int minValue = Integer.MAX_VALUE;
        while (!maxPQ.isEmpty()) {
            int[] cur = maxPQ.poll();
            minValue = Math.min(minValue, grid[cur[0]][cur[1]]);
            if (Arrays.equals(cur, new int[]{grid.length - 1, grid[0].length - 1}))
                break;
            for (int[] direction : dirs) {
                int newX = cur[0] + direction[0];
                int newY = cur[1] + direction[1];
                if (newX >= 0 && newX < grid.length && newY >= 0 && newY < grid[0].length && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    maxPQ.offer(new int[]{newX, newY});
                }
            }
        }
        return minValue;
    }
}
