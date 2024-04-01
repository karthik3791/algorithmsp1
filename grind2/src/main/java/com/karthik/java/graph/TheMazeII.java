package com.karthik.java.graph;

import java.util.*;

public class TheMazeII {

    class Point {
        int r;
        int c;

        Point(int a, int b) {
            this.r = a;
            this.c = b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object other) {
            if (this == other) return true;
            if (!(other instanceof Point)) return false;
            Point o = (Point) other;
            return this.r == o.r && this.c == o.c;
        }
    }

    class Pair<K, V> {
        K k;
        V v;

        Pair(K k1, V v1) {
            this.k = k1;
            this.v = v1;
        }
    }


    private List<Pair<Point, Integer>> getNeighbours(Point u, int[][] maze) {
        List<Pair<Point, Integer>> res = new ArrayList<>();
        Point left = new Point(u.r, 0);
        for (int c = u.c; c > 0; c--)
            if (maze[u.r][c - 1] == 1) {
                left.c = c;
                break;
            }
        Point right = new Point(u.r, maze[0].length - 1);
        for (int c = u.c; c < maze[0].length - 1; c++)
            if (maze[u.r][c + 1] == 1) {
                right.c = c;
                break;
            }
        Point up = new Point(0, u.c);
        for (int r = u.r; r > 0; r--)
            if (maze[r - 1][u.c] == 1) {
                up.r = r;
                break;
            }
        Point down = new Point(maze.length - 1, u.c);
        for (int r = u.r; r < maze.length - 1; r++)
            if (maze[r + 1][u.c] == 1) {
                down.r = r;
                break;
            }
        if (!left.equals(u))
            res.add(new Pair(left, u.c - left.c));
        if (!right.equals(u))
            res.add(new Pair(right, right.c - u.c));
        if (!up.equals(u))
            res.add(new Pair(up, u.r - up.r));
        if (!down.equals(u))
            res.add(new Pair(down, down.r - u.r));
        return res;
    }


    public int shortestDistance(int[][] maze, int[] start, int[] destination) {
        Map<Point, Integer> dist = new HashMap<>();
        for (int i = 0; i < maze.length; i++)
            for (int j = 0; j < maze[0].length; j++)
                dist.put(new Point(i, j), Integer.MAX_VALUE);

        PriorityQueue<Pair<Point, Integer>> q = new PriorityQueue<>((pair1, pair2) -> pair1.v - pair2.v);
        q.offer(new Pair<Point, Integer>(new Point(start[0], start[1]), 0));
        while (!q.isEmpty()) {
            Pair<Point, Integer> u = q.poll();
            if (dist.get(u.k) <= u.v)
                continue;
            dist.put(u.k, u.v);
            for (Pair<Point, Integer> v : getNeighbours(u.k, maze)) {
                q.offer(new Pair(v.k, v.v + u.v));
            }
        }
        int finalDist = dist.get(new Point(destination[0], destination[1]));
        return finalDist == Integer.MAX_VALUE ? -1 : finalDist;
    }
}
