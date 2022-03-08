package com.karthik.java;

import java.util.*;

public class WordLadder2 {

    public static class Edge {
        int v;
        int w;
        int weight;

        public Edge(int v, int w, int weight) {
            this.v = v;
            this.w = w;
            this.weight = weight;
        }

        public int weight() {
            return weight;
        }

        public int other(int vertex) {
            if (vertex == v) return w;
            else if (vertex == w) return v;
            else throw new IllegalArgumentException("Illegal endpoint");
        }
    }

    public static class EdgeWeightedGraph {
        private int V;
        private int E;
        private Set<Edge>[] adj;

        public EdgeWeightedGraph(int v) {
            this.V = v;
            adj = (Set<Edge>[]) new HashSet[V];
            for (int i = 0; i < V; i++)
                adj[i] = new HashSet<>();
        }

        public int V() {
            return this.V;
        }

        public void addEdge(Edge e) {
            int v = e.v, w = e.w;
            adj[v].add(e);
            adj[w].add(e);
            this.E++;
        }

        public Iterable<Edge> adj(int v) {
            return adj[v];
        }

        public int degree(int v) {
            return adj[v].size();
        }
    }

    public static class DijkstraUndirectedSP {

        private Edge[] edgeTo;
        private int[] distTo;
        private IndexMinPQ<Integer> pq;
        private int s;

        public DijkstraUndirectedSP(EdgeWeightedGraph G, int source) {
            distTo = new int[G.V()];
            edgeTo = new Edge[G.V()];
            for (int i = 0; i < G.V(); i++)
                distTo[i] = Integer.MAX_VALUE;
            distTo[0] = 0;
            this.s = source;
            this.distTo[source] = 0;
            this.pq = new IndexMinPQ<Integer>(G.V());
            this.pq.insert(source, this.distTo[source]);
            int v;
            while (!this.pq.isEmpty()) {
                v = this.pq.delMin();
                Iterator var7 = G.adj(v).iterator();

                while (var7.hasNext()) {
                    Edge e = (Edge) var7.next();
                    this.relax(e, v);
                }
            }
        }

        private void relax(Edge e, int v) {
            int w = e.other(v);
            if (this.distTo[w] > this.distTo[v] + e.weight()) {
                this.distTo[w] = this.distTo[v] + e.weight();
                this.edgeTo[w] = e;
                if (this.pq.contains(w)) {
                    this.pq.decreaseKey(w, this.distTo[w]);
                } else {
                    this.pq.insert(w, this.distTo[w]);
                }
            }
        }

        public boolean hasPathTo(int w) {
            return this.distTo[w] != Integer.MAX_VALUE;
        }

        public Iterable<Integer> pathTo(int w) {
            if (hasPathTo(w)) {
                Stack<Integer> path = new Stack<>();
                for (Edge e = edgeTo[w]; e != null; e = edgeTo[e.v]) {
                    path.push(e.w);
                }
                path.add(this.s);
                return path;
            } else
                return null;
        }

        public int distanct(int w) {
            return distTo[w];
        }
    }

    private boolean isConnected(String str1, String str2) {
        boolean isConnected = false;
        if (str1.length() == str2.length()) {
            int differentCharCount = 0;
            char[] charArrayOne = str1.toCharArray();
            char[] charArrayTwo = str2.toCharArray();
            for (int i = 0; i < str1.length(); i++) {
                if (charArrayOne[i] != charArrayTwo[i])
                    differentCharCount++;
            }
            isConnected = differentCharCount == 1;
        }
        return isConnected;
    }

    public static class IndexMinPQ<Key extends Comparable<Key>> {
        private int maxN;        // maximum number of elements on PQ
        private int n;           // number of elements on PQ
        private int[] pq;        // binary heap using 1-based indexing
        private int[] qp;        // inverse of pq - qp[pq[i]] = pq[qp[i]] = i
        private Key[] keys;      // keys[i] = priority of i

        public IndexMinPQ(int maxN) {
            if (maxN < 0) throw new IllegalArgumentException();
            this.maxN = maxN;
            n = 0;
            keys = (Key[]) new Comparable[maxN + 1];    // make this of length maxN??
            pq = new int[maxN + 1];
            qp = new int[maxN + 1];                   // make this of length maxN??
            for (int i = 0; i <= maxN; i++)
                qp[i] = -1;
        }

        public void insert(int i, Key key) {
            n++;
            qp[i] = n;
            pq[n] = i;
            keys[i] = key;
            swim(n);
        }

        public void decreaseKey(int i, Key key) {
            if (keys[i].compareTo(key) == 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key equal to the key in the priority queue");
            if (keys[i].compareTo(key) < 0)
                throw new IllegalArgumentException("Calling decreaseKey() with a key strictly greater than the key in the priority queue");
            keys[i] = key;
            swim(qp[i]);
        }

        private void swim(int k) {
            while (k > 1 && greater(k / 2, k)) {
                exch(k, k / 2);
                k = k / 2;
            }
        }

        private boolean greater(int i, int j) {
            return keys[pq[i]].compareTo(keys[pq[j]]) > 0;
        }

        private void sink(int k) {
            while (2 * k <= n) {
                int j = 2 * k;
                if (j < n && greater(j, j + 1)) j++;
                if (!greater(k, j)) break;
                exch(k, j);
                k = j;
            }
        }

        public boolean contains(int i) {
            return qp[i] != -1;
        }

        private void exch(int i, int j) {
            int swap = pq[i];
            pq[i] = pq[j];
            pq[j] = swap;
            qp[pq[i]] = i;
            qp[pq[j]] = j;
        }

        public boolean isEmpty() {
            return n == 0;
        }

        public int delMin() {
            int min = pq[1];
            exch(1, n--);
            sink(1);
            qp[min] = -1;        // delete
            keys[min] = null;    // to help with garbage collection
            pq[n + 1] = -1;        // not needed
            return min;
        }

    }


    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return null;
        int endWordVertexId = wordList.indexOf(endWord);
        String[] wordArray = new String[wordList.size() + 1];
        EdgeWeightedGraph G = new EdgeWeightedGraph(wordList.size() + 1);
        int vertexId = 0;
        wordArray[vertexId++] = beginWord;
        for (String word : wordList) {
            wordArray[vertexId++] = word;
        }
        endWordVertexId++;
        for (int i = 0; i < wordArray.length; i++) {
            String v = wordArray[i];
            for (int j = i + 1; j < wordArray.length; j++) {
                String w = wordArray[j];
                if (isConnected(v, w)) {
                    G.addEdge(new Edge(i, j, 1));
                }
            }
        }
        DijkstraUndirectedSP djSP = new DijkstraUndirectedSP(G, 0);
        return null;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        WordLadder2 wl = new WordLadder2();
        System.out.println(wl.findLadders(beginWord, endWord, wordList));
    }

}
