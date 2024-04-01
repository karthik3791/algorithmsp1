package com.karthik.java.graph;

import java.util.*;

public class SmallestStringWithSwaps {

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
        public void union(int x, int y) {
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
            }
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

        public int getNoOfConnectedComponents() {
            return count;
        }

        public List<List<Integer>> getConnectedComponents() {
            Map<Integer, List<Integer>> components = new HashMap<>();
            for (int i = 0; i < root.length; i++) {
                int rooti = find(root[i]);
                List<Integer> currentComponent = components.getOrDefault(rooti, new ArrayList<>());
                currentComponent.add(i);
                components.put(rooti, currentComponent);
            }
            return new ArrayList<>(components.values());
        }

    }

    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        UnionFind uf = new UnionFind(s.length());
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0), pair.get(1));
        }
        List<List<Integer>> connectedComponents = uf.getConnectedComponents();
        StringBuilder sb = new StringBuilder(s);
        for (List<Integer> component : connectedComponents) {
            char[] charsInAComponent = new char[component.size()];
            for (int j = 0; j < component.size(); j++) {
                charsInAComponent[j] = s.charAt(component.get(j));
            }
            Arrays.sort(charsInAComponent);
            for (int j = 0; j < component.size(); j++) {
                sb.setCharAt(component.get(j), charsInAComponent[j]);
            }
        }
        return sb.toString();
    }
}
