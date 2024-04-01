package com.karthik.java.graph;

import java.util.Arrays;

public class EarliestMomentEveryoneFriends {

    public int earliestAcq(int[][] logs, int n) {
        Arrays.sort(logs, (r1, r2) -> r1[0] - r2[0]);
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < logs.length; i++) {
            uf.union(logs[i][1], logs[i][2]);
            if (uf.getNoOfConnectedComponents() == 1)
                return logs[i][0];
        }
        return -1;
    }
}
