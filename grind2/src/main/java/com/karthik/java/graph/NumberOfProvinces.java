package com.karthik.java.graph;

public class NumberOfProvinces {
    public int findCircleNum(int[][] isConnected) {
        UnionFind uf = new UnionFind(isConnected.length);
        for (int i = 0; i < isConnected.length; i++)
            for (int j = 0; j < isConnected[0].length; j++) {
                if (isConnected[i][j] == 1)
                    uf.union(i, j);
            }
        return uf.getNoOfConnectedComponents();
    }
}
