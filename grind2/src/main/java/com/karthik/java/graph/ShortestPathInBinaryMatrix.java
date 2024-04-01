package com.karthik.java.graph;

import java.util.*;

class ShortestPathInBinaryMatrixWrong1 {

    private List<int[]> neighbours(int[] cur, int n) {
        int r = cur[0];
        int c = cur[1];
        List<int[]> res = new ArrayList<>();
        for (int r1 = r - 1; r1 <= r + 1; r1++)
            for (int c1 = c - 1; c1 <= c + 1; c1++) {
                if (r1 == r && c1 == c) continue;
                if (r1 < 0 || r1 >= n || c1 < 0 || c1 >= n) continue;
                res.add(new int[]{r1, c1});
            }
        return res;
    }


    /*
    Very interesting that Set.contains does not work on Arrays
    https://stackoverflow.com/questions/62287624/why-does-set-contains-method-work-for-some-objects-only
     */
    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<int[]> q = new LinkedList<>();
        int[] origin = {0, 0};
        if (grid[origin[0]][origin[1]] != 0) return -1;
        if (grid.length == 1) return 0;
        q.offer(origin);
        Set<int[]> visited = new HashSet<>();
        visited.add(origin);
        int length = 0;
        while (!q.isEmpty()) {
            length++;
            int size = q.size();
            for (int i = 0; i < size; i++) {
                int[] u = q.poll();
                if (u[0] == grid.length - 1 && u[1] == grid[0].length - 1)
                    return length;
                for (int[] v : neighbours(u, grid.length)) {
                    if (grid[v[0]][v[1]] != 0) continue;
                    if (!visited.contains(v)) {
                        visited.add(v);
                        q.offer(v);
                    }
                }
            }
        }
        return -1;
    }
}


class ShortestPathInBinaryMatrixPOJO {
    private List<Pair> neighbours(Pair cur, int n) {
        int r = cur._1();
        int c = cur._2();
        List<Pair> res = new ArrayList<>();
        for (int r1 = r - 1; r1 <= r + 1; r1++)
            for (int c1 = c - 1; c1 <= c + 1; c1++) {
                if (r1 == r && c1 == c) continue;
                if (r1 < 0 || r1 >= n || c1 < 0 || c1 >= n) continue;
                res.add(new Pair(r1, c1));
            }
        return res;
    }

    class Pair {
        int a;
        int b;

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Pair pair = (Pair) o;
            return a == pair.a && b == pair.b;
        }

        @Override
        public int hashCode() {
            return Objects.hash(a, b);
        }

        Pair(int a1, int b1) {
            a = a1;
            b = b1;
        }

        int _1() {
            return a;
        }

        int _2() {
            return b;
        }


    }


    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<Pair> q = new LinkedList<>();
        Pair origin = new Pair(0, 0);
        if (grid[origin._1()][origin._2()] != 0) return -1;
        q.offer(origin);
        Set<Pair> visited = new HashSet<>();
        visited.add(origin);
        int length = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                Pair u = q.poll();
                if (u._1() == grid.length - 1 && u._2() == grid[0].length - 1)
                    return length;
                for (Pair v : neighbours(u, grid.length)) {
                    if (grid[v._1()][v._2()] != 0) continue;
                    if (!visited.contains(v)) {
                        visited.add(v);
                        q.offer(v);
                    }
                }
            }
            length++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        ShortestPathInBinaryMatrixPOJO s = new ShortestPathInBinaryMatrixPOJO();
        System.out.println(s.shortestPathBinaryMatrix(grid));
    }

}

public class ShortestPathInBinaryMatrix {
    private List<List<Integer>> neighbours(List<Integer> cur, int n) {
        int r = cur.get(0);
        int c = cur.get(1);
        List<List<Integer>> res = new ArrayList<>();
        for (int r1 = r - 1; r1 <= r + 1; r1++)
            for (int c1 = c - 1; c1 <= c + 1; c1++) {
                if (r1 == r && c1 == c) continue;
                if (r1 < 0 || r1 >= n || c1 < 0 || c1 >= n) continue;
                res.add(Arrays.asList(r1, c1));
            }
        return res;
    }


    public int shortestPathBinaryMatrix(int[][] grid) {
        Queue<List<Integer>> q = new LinkedList<>();
        List<Integer> origin = Arrays.asList(0, 0);
        if (grid[origin.get(0)][origin.get(1)] != 0) return -1;
        q.offer(origin);
        Set<List<Integer>> visited = new HashSet<>();
        visited.add(origin);
        int length = 1;
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                List<Integer> u = q.poll();
                if (u.get(0) == grid.length - 1 && u.get(1) == grid[0].length - 1)
                    return length;
                for (List<Integer> v : neighbours(u, grid.length)) {
                    if (grid[v.get(0)][v.get(1)] != 0) continue;
                    if (!visited.contains(v)) {
                        visited.add(v);
                        q.offer(v);
                    }
                }
            }
            length++;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[][] grid = {{0, 0, 0}, {1, 1, 0}, {1, 1, 0}};
        ShortestPathInBinaryMatrix s = new ShortestPathInBinaryMatrix();
        System.out.println(s.shortestPathBinaryMatrix(grid));
    }

}
