package com.karthik.java;

import java.util.Arrays;

public class NumberOfProvinces {

    private int noOfProvinces;

    private void union(int x, int y, int[] root, int[] rank) {
        System.out.println("Union between x " + x + " and y " + y);
        int rootx = findParent(x, root);
        int rooty = findParent(y, root);
        if (rootx == rooty)
            return;
        if (rank[rootx] > rank[rooty])
            root[rooty] = rootx;
        else if (rank[rooty] > rank[rootx])
            root[rootx] = rooty;
        else {
            root[rooty] = rootx;
            rank[rootx]++;
        }
        noOfProvinces--;
    }

    private int findParent(int x, int[] root) {
        if (root[x] == x)
            return x;
        return root[x] = findParent(root[x], root);
    }


    public int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        this.noOfProvinces = n;
        int[] root = new int[n];
        int[] rank = new int[n];
        for (int i = 0; i < n; i++)
            root[i] = i;
        Arrays.fill(rank, 1);

        for (int i = 0; i < n; i++)
            for (int j = i + 1; j < n; j++)
                if (isConnected[i][j] == 1)
                    union(i, j, root, rank);

        return this.noOfProvinces;
    }

    public static void main(String[] args) {
        NumberOfProvinces sol = new NumberOfProvinces();
        int[][] isConnected = {{1, 1, 0}, {1, 1, 0}, {0, 0, 1}};
        System.out.println(sol.findCircleNum(isConnected));
    }
}
