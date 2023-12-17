package com.karthik.java;

import java.util.Arrays;
import java.util.PriorityQueue;

public class PathWithMinimumEffort {

    public int minimumEffortPath(int[][] heights) {
        int[][] dist = new int[heights.length][heights[0].length];
        for (int i = 0; i < dist.length; i++)
            Arrays.fill(dist[i], Integer.MAX_VALUE);
        int[][] dirs = {{0, 1}, {0, -1}, {-1, 0}, {1, 0}};
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        dist[0][0] = 0;
        minPQ.offer(new int[]{0, 0, 0});
        while (!minPQ.isEmpty()) {
            int[] cur = minPQ.poll();
            int curDistance = cur[0];
            if (cur[1] == heights.length - 1 && cur[2] == heights[0].length - 1)
                break;
            if (curDistance > dist[cur[1]][cur[2]])
                continue;
            for (int[] dir : dirs) {
                int newr = cur[1] + dir[0];
                int newc = cur[2] + dir[1];
                if (newr >= 0 && newr < heights.length && newc >= 0 && newc < heights[0].length) {
                    int distFromCur = Math.max(curDistance, Math.abs(heights[newr][newc] - heights[cur[1]][cur[2]]));
                    if (dist[newr][newc] > distFromCur) {
                        dist[newr][newc] = distFromCur;
                        minPQ.offer(new int[]{dist[newr][newc], newr, newc});
                    }
                }
            }
        }
        return dist[heights.length - 1][heights[0].length - 1];
    }

    public int minimumEffortPathOldWrong(int[][] heights) {
        boolean[][] visited = new boolean[heights.length][heights[0].length];
        PriorityQueue<int[]> minPQ = new PriorityQueue<>((a, b) -> a[2] - b[2]);
        minPQ.offer(new int[]{0, 0, 0});
        visited[0][0] = true;
        int[][] dirs = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};
        int maxValue = Integer.MIN_VALUE;
        while (!minPQ.isEmpty()) {
            int[] cur = minPQ.poll();
            System.out.println("Coordinate : " + Arrays.toString(cur));
            maxValue = Math.max(maxValue, cur[2]);
            if (cur[0] == heights.length - 1 && cur[1] == heights[0].length - 1)
                break;
            for (int[] dir : dirs) {
                int newX = cur[0] + dir[0];
                int newY = cur[1] + dir[1];
                if (newX >= 0 && newX < heights.length && newY >= 0 && newY < heights[0].length && !visited[newX][newY]) {
                    visited[newX][newY] = true;
                    minPQ.offer(new int[]{newX, newY, Math.abs(heights[cur[0]][cur[1]] - heights[newX][newY])});
                }
            }
        }
        return maxValue;
    }
}
