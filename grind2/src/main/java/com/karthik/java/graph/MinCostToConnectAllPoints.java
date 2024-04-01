package com.karthik.java.graph;

import java.util.PriorityQueue;

public class MinCostToConnectAllPoints {

    class UnionFind {
        int[] root;
        int[] rank;

        public UnionFind(int n) {
            root = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                root[i] = i;
                rank[i] = 1;
            }
        }

        /*
         Find with Path compression
         */
        private int find(int u) {
            if (u == root[u])
                return u;
            else
                return root[u] = find(root[u]);
        }

        /*
         Union By Rank
         */
        public void union(int u, int v) {
            int rootU = find(u);
            int rootV = find(v);
            if (rootU != rootV) {
                int rankU = rank[u];
                int rankV = rank[v];
                if (rankU < rankV)
                    root[rootU] = rootV;
                else if (rankV < rankU)
                    root[rootV] = rootU;
                else {
                    root[rootV] = rootU;
                    rank[rootU]++;
                }
            }
        }

        public boolean isConnected(int u, int v) {
            return find(u) == find(v);
        }
    }

    class Edge {
        int start;
        int end;
        int cost;

        Edge(int s, int e, int c) {
            this.start = s;
            this.end = e;
            this.cost = c;
        }
    }


    public int minCostConnectPointsKruskal(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        for (int i = 0; i < points.length; i++)
            for (int j = i + 1; j < points.length; j++) {
                int[] u = points[i];
                int[] v = points[j];
                int d = Math.abs(u[0] - v[0]) + Math.abs(u[1] - v[1]);
                //Though its a connected undirected graph, we only add 1 edge as the 2nd one makes no difference
                pq.offer(new Edge(i, j, d));
            }
        int res = 0;
        UnionFind uf = new UnionFind(points.length);
        for (int i = 0; i < points.length - 1 && !pq.isEmpty(); ) {
            Edge e = pq.poll();
            if (!uf.isConnected(e.start, e.end)) {
                uf.union(e.start, e.end);
                res += e.cost;
                i++; // Can only increase i if we actually added the edge
            }
        }
        return res;
    }

    public int minCostConnectPointsPrim(int[][] points) {
        if (points == null || points.length == 0) {
            return 0;
        }
        PriorityQueue<Edge> pq = new PriorityQueue<>((e1, e2) -> e1.cost - e2.cost);
        for (int j = 1; j < points.length; j++) {
            pq.offer(new Edge(0, j, Math.abs(points[0][0] - points[j][0]) + Math.abs(points[0][1] - points[j][1])));
        }
        boolean[] visited = new boolean[points.length];
        visited[0] = true;
        int count = points.length - 1;
        int res = 0;
        while (!pq.isEmpty() && count > 0) {
            Edge e = pq.poll();
            int u = e.end;
            if (!visited[u]) {
                count--;
                res += e.cost;
                visited[u] = true;
                for (int i = 0; i < points.length; i++) {
                    //We cant mark the vertex i as visited here when we add the edges. This is because the vertex itself is visited ONLY when an edge whose destination that is not visited is taken off PQ
                    if (!visited[i])
                        pq.offer(new Edge(u, i, Math.abs(points[u][0] - points[i][0]) + Math.abs(points[u][1] - points[i][1])));
                }
            }
        }
        return res;
    }
}
