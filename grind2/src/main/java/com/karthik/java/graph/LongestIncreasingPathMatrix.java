package com.karthik.java.graph;

import java.util.*;


public class LongestIncreasingPathMatrix {

    private List<int[]> getNeighbours(int i, int j, int m, int n) {
        List<int[]> res = new ArrayList<>();
        int[][] deltas = {{1, 0}, {-1, 0}, {0, -1}, {0, 1}};
        for (int[] delta : deltas) {
            int newi = i + delta[0];
            int newj = j + delta[1];
            if (newi >= 0 && newi < m && newj >= 0 && newj < n)
                res.add(new int[]{newi, newj});
        }
        return res;
    }

    private void dfs(int i, int j, int[][] memo, int[][] matrix) {
        if (memo[i][j] == 1) {
            for (int[] v : getNeighbours(i, j, matrix.length, matrix[0].length)) {
                if (matrix[v[0]][v[1]] > matrix[i][j]) {
                    dfs(v[0], v[1], memo, matrix);
                    memo[i][j] = Math.max(memo[i][j], 1 + memo[v[0]][v[1]]);
                }
            }
        }
    }

    public int longestIncreasingPath(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        int[][] memo = new int[m][n];
        for (int i = 0; i < m; i++)
            Arrays.fill(memo[i], 1);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++)
                dfs(i, j, memo, matrix);
        int maxPath = 1;
        for (int i = 0; i < m; i++)
            maxPath = Math.max(maxPath, Arrays.stream(memo[i]).max().getAsInt());
        return maxPath;
    }

    public static void main(String[] args) {

    }


}


/*
 Below approach using UnionFind will simply not work becausa
 lets say we have a cell (i,j) that has a value less than (i,j+1)
 By connecting these 2 cells, we sort of implicitly indicate a bi-directional relationship which is not true.
 In our case, it is STRICTLY directional.
 */
class LongestIncreasingPathMatrixUF {

    class UnionFind<T> {
        Map<T, T> parent;
        Map<T, Integer> rank;
        Map<T, Integer> size;
        int N;

        UnionFind(int n) {
            parent = new HashMap<>();
            rank = new HashMap<>();
            size = new HashMap<>();
            this.N = n;
        }

        /*
        Quick Find with path compression
         */
        private T find(T node) {
            T par = parent.computeIfAbsent(node, n -> n);
            if (!par.equals(node)) {
                parent.put(node, find(par));
            }
            return parent.get(node);
        }

        /*
        Quick Union By Rank
         */
        public void union(T t1, T t2) {
            T p1 = find(t1);
            T p2 = find(t2);
            if (!p1.equals(p2)) {
                int r1 = rank.computeIfAbsent(p1, p -> 1);
                int r2 = rank.computeIfAbsent(p2, p -> 1);
                int size1 = size.computeIfAbsent(p1, p -> 1);
                int size2 = size.computeIfAbsent(p2, p -> 1);
                if (r1 > r2) {
                    parent.put(p2, p1);
                    size.put(p1, size1 + size2);
                } else if (r1 < r2) {
                    parent.put(p1, p2);
                    size.put(p2, size1 + size2);
                } else {
                    parent.put(p2, p1);
                    rank.computeIfPresent(p1, (p, v) -> v + 1);
                    size.put(p1, size1 + size2);
                }
                N--;
            }
        }

        public boolean isConnected(T t1, T t2) {
            return find(t1).equals(find(t2));
        }

        public int getMaxComponentSize() {
            return size.values().stream().max((a, b) -> (a - b)).orElse(1);
        }
    }

    class Point {
        int r;
        int c;

        Point(int r1, int c1) {
            this.r = r1;
            this.c = c1;
        }

        @Override
        public int hashCode() {
            return Objects.hash(r, c);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Point))
                return false;
            Point other = (Point) o;
            return other.r == r && other.c == c;
        }

    }

    private List<Point> getNeighbours(Point u, int m, int n) {
        int r = u.r;
        int c = u.c;
        int[][] deltas = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};
        List<Point> res = new ArrayList<>();
        for (int[] delta : deltas) {
            int nr = r + delta[0];
            int nc = c + delta[1];
            if (nr >= 0 && nr < m && nc >= 0 && nc < n)
                res.add(new Point(nr, nc));
        }
        return res;
    }


    public int longestIncreasingPathWrong(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        UnionFind<Point> uf = new UnionFind<>(m * n);
        for (int i = 0; i < m; i++)
            for (int j = 0; j < n; j++) {
                Point u = new Point(i, j);
                for (Point v : getNeighbours(u, m, n)) {
                    if (matrix[i][j] < matrix[v.r][v.c])
                        uf.union(u, v);
                }
            }
        return uf.getMaxComponentSize();
    }
}
