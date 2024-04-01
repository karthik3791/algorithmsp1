package com.karthik.java.graph;

import java.util.*;
import java.util.stream.Collectors;

public class PathWithMinimumEffort {

    class Point {
        int r;
        int c;

        Point(int r, int c) {
            this.r = r;
            this.c = c;
        }

        @Override
        public int hashCode() {
            return Objects.hash(this.r, this.c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Point))
                return false;
            Point other = (Point) o;
            return other.r == this.r && other.c == this.c;
        }
    }

    class Pair<K, V> {
        K key;
        V value;

        Pair(K k1, V v1) {
            this.key = k1;
            this.value = v1;
        }
    }

    private List<Point> getNeighbours(Point p, int[][] heights) {
        List<Point> res = new ArrayList<>();
        for (int i = -1; i <= 1; i += 2) {
            Point p1 = new Point(p.r + i, p.c);
            Point p2 = new Point(p.r, p.c + i);
            res.add(p1);
            res.add(p2);
        }
        return res.stream().filter(point -> point.r >= 0 && point.r < heights.length && point.c >= 0 && point.c < heights[0].length).collect(Collectors.toList());
    }

    public int minimumEffortPath(int[][] heights) {
        Map<Point, Integer> maximumEffort = new HashMap<>();
        for (int i = 0; i < heights.length; i++)
            for (int j = 0; j < heights[0].length; j++)
                maximumEffort.put(new Point(i, j), Integer.MAX_VALUE);

        PriorityQueue<Pair<Point, Integer>> pq = new PriorityQueue<>((p1, p2) -> p1.value - p2.value);
        pq.offer(new Pair(new Point(0, 0), 0));

        while (!pq.isEmpty()) {
            Pair<Point, Integer> pointWithMaximumEffortToReach = pq.poll();
            Point u = pointWithMaximumEffortToReach.key;
            if (pointWithMaximumEffortToReach.value >= maximumEffort.get(u))
                continue;
            maximumEffort.put(u, pointWithMaximumEffortToReach.value);
            for (Point v : getNeighbours(u, heights)) {
                int costUV = Math.abs(heights[v.r][v.c] - heights[u.r][u.c]);
                pq.offer(new Pair(v, Math.max(pointWithMaximumEffortToReach.value, costUV)));
            }
        }
        return maximumEffort.get(new Point(heights.length - 1, heights[0].length - 1)) == Integer.MAX_VALUE ? -1 : maximumEffort.get(new Point(heights.length - 1, heights[0].length - 1));
    }

    public static void main(String[] args) {
        int[][] heights = {{1, 2, 2}, {3, 8, 2}, {5, 3, 5}};
        PathWithMinimumEffort p = new PathWithMinimumEffort();
        p.minimumEffortPath(heights);
    }

}
