package com.karthik.java.graph;

public class NumberOfConnectedComponents {
    public int countComponents(int n, int[][] edges) {
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < edges.length; i++) {
            uf.union(edges[i][0], edges[i][1]);
        }
        return uf.getNoOfConnectedComponents();
    }
}
