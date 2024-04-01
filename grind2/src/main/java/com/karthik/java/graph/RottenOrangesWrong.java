package com.karthik.java.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class RottenOrangesWrong {

    /*
      1. We may have several connected components.
      2. For each connected component that has atleast 1 rotten orange, we need to reset the timer back to 1 and start over BFS again.
      3. We may have several rotten oranges inside 1 connected component
     */
    private List<int[]> getNeighbours(int[] u, int[][] grid) {
        List<int[]> v = new ArrayList<>();
        v.add(new int[]{u[0] - 1, u[1]});
        v.add(new int[]{u[0] + 1, u[1]});
        v.add(new int[]{u[0], u[1] - 1});
        v.add(new int[]{u[0], u[1] + 1});
        return v.stream().filter(p -> p[0] >= 0 && p[0] < grid.length && p[1] >= 0 && p[1] < grid[0].length).collect(Collectors.toList());
    }

    private int bfs(int[] origin, int[][] grid, boolean[][] visited, int minute) {
        int m = grid.length;
        int n = grid[0].length;
        Queue<int[]> q = new LinkedList<>();
        q.offer(origin);
        visited[origin[0]][origin[1]] = true;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] u = q.poll();
                for (int[] v : getNeighbours(u, grid)) {
                    if (grid[v[0]][v[1]] == 0) continue;
                    if (!visited[v[0]][v[1]]) {
                        visited[v[0]][v[1]] = true;
                        q.offer(v);
                    }
                }
            }
            if (!q.isEmpty())
                minute++;
        }
        return minute;
    }


    /*
     What is wrong with this approach is that when we just start with a single rotten orange cell,
     we hit into a problem when we face another rotten orange.
     In that case, choosing our neighbour becomes a problem.
     Essentially, when we have multiple rotten oranges in a single connected component, we need to count the clock for each one from 1.
     And for each non-rotten orange, we have to choose the minimum of time.
     All this clearly points to BFS from multiple sources.
     */

    public int orangesRotting(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxTime = 0;
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 2 && !visited[i][j])
                    maxTime = Math.max(maxTime, bfs(new int[]{i, j}, grid, visited, 0));
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1 && !visited[i][j])
                    return -1;

        return maxTime;
    }

    public static void main(String[] args) {
        int[][] grid = {{2, 1, 1}, {1, 1, 0}, {0, 1, 1}};
        RottenOrangesWrong r = new RottenOrangesWrong();
        r.orangesRotting(grid);
    }
}


/*
1. There might be several connected components in the graph.
2. Each connected component might have multiple rotten oranges present.
   Each of the rotten orange is a "source" that can start decaying all the other neighbouring oranges.
   (BFS from multiple source)
3. This is a BFS with multiple sources problem.
 */

class RottenOranges2 {

    private List<int[]> getNeighbours(int[] u, int[][] grid) {
        List<int[]> v = new ArrayList<>();
        v.add(new int[]{u[0] - 1, u[1]});
        v.add(new int[]{u[0] + 1, u[1]});
        v.add(new int[]{u[0], u[1] - 1});
        v.add(new int[]{u[0], u[1] + 1});
        return v.stream().filter(p -> p[0] >= 0 && p[0] < grid.length && p[1] >= 0 && p[1] < grid[0].length).collect(Collectors.toList());
    }


    public int orangesRotting(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        Queue<int[]> q = new LinkedList<>();
        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 2) {
                    visited[i][j] = true;
                    q.offer(new int[]{i, j});
                }

        int minute = 0;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int k = 0; k < size; k++) {
                int[] u = q.poll();
                for (int[] v : getNeighbours(u, grid)) {
                    if (grid[v[0]][v[1]] == 0) continue;
                    if (!visited[v[0]][v[1]]) {
                        visited[v[0]][v[1]] = true;
                        q.offer(v);
                    }
                }
            }
            if (!q.isEmpty())
                minute++;
        }

        for (int i = 0; i < grid.length; i++)
            for (int j = 0; j < grid[0].length; j++)
                if (grid[i][j] == 1 && !visited[i][j])
                    return -1;

        return minute;
    }
}
