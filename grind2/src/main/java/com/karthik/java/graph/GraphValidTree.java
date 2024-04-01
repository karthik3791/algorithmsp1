package com.karthik.java.graph;

public class GraphValidTree {

    public class UnionFind {
        private int[] root;
        private int[] rank;
        private int count;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 1;
            }
            count = n;
        }

        //Find with Path Compression
        public int find(int x) {
            if (root[x] == x)
                return x;
            else
                return root[x] = find(root[x]);
        }

        //Union By Rank
        public boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY])
                    root[rootY] = rootX;
                else if (rank[rootY] > rank[rootX])
                    root[rootX] = rootY;
                else {
                    root[rootY] = rootX;
                    rank[rootX]++;
                }
                count--;
                return true;
            } else return false;
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public int getNoOfConnectedComponents() {
            return count;
        }
    }

    public boolean validTree(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < edges.length; i++) {
            if (!uf.union(edges[i][0], edges[i][1]))
                return false;
        }
        return uf.getNoOfConnectedComponents() == 1;
    }
}
