package com.karthik.java.graph;

import java.util.*;

/*
 The question needs to be interpreted as ...which of these edges can be removed( only one will be removed)
 and this will result in the graph no longer being connected.

 Every edge that belongs to a cycle WILL not be a critical link. Because, you can always remove any ONE of them and the graph will still remain connected.
 */

public class CriticalConnections {

    class Pair<K, V> {
        private K k;
        private V v;

        public Pair(K k1, V v1) {
            k = k1;
            v = v1;
        }

        public K getKey() {
            return k;
        }

        public V getValue() {
            return v;
        }

        @Override
        public int hashCode() {
            return Objects.hash(k, v);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o)
                return true;
            if (!(o instanceof Pair)) return false;
            Pair<?, ?> other = (Pair<?, ?>) o;
            return (this.k.equals(other.k) && this.v.equals(other.v));
        }

    }

    private Map<Integer, List<Integer>> graph;
    private Map<Integer, Integer> rank;
    private Set<Pair<Integer, Integer>> edgeSet;

    private int dfs(int node, int rank) {
        if (this.rank.containsKey(node))
            return this.rank.get(node);
        this.rank.put(node, rank);
        int minRank = Integer.MAX_VALUE;
        for (Integer neighbor : this.graph.getOrDefault(node, new ArrayList<>())) {
            //Skip immediate parent
            if (this.rank.containsKey(neighbor) && this.rank.get(neighbor) == rank - 1)
                continue;
            int neighbourRank = this.dfs(neighbor, rank + 1);
            // If our node is linked to a neighbour with lower rank, it means there is a cycle. Any edge belonging to a cycle cannot be a critical connection
            if (neighbourRank <= rank)
                this.edgeSet.remove(new Pair<Integer, Integer>(Math.min(node, neighbor), Math.max(node, neighbor)));
            minRank = Math.min(minRank, neighbourRank);
        }
        return minRank;
    }

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.graph = new HashMap<Integer, List<Integer>>();
        this.rank = new HashMap<Integer, Integer>();
        this.edgeSet = new HashSet<Pair<Integer, Integer>>();

        for (List<Integer> edge : connections) {
            int u = edge.get(0);
            int v = edge.get(1);
            graph.computeIfAbsent(edge.get(0), l -> new ArrayList<>()).add(edge.get(1));
            graph.computeIfAbsent(edge.get(1), l -> new ArrayList<>()).add(edge.get(0));
            //Edges must be stored in a fixed order so that they can be identified from the Set for removal later on
            edgeSet.add(new Pair<Integer, Integer>(Math.min(u, v), Math.max(u, v)));
        }
        this.dfs(0, 0);

        List<List<Integer>> result = new ArrayList<List<Integer>>();
        for (Pair<Integer, Integer> criticalConnection : this.edgeSet) {
            result.add(new ArrayList<Integer>(Arrays.asList(criticalConnection.getKey(), criticalConnection.getValue())));
        }
        return result;
    }


}
