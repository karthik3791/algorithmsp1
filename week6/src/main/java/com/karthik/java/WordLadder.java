package com.karthik.java;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;

import java.util.*;

public class WordLadder {

    public static class Graph {
        private int V;
        private int E;
        private Set<Integer>[] adj;

        public Graph(int v) {
            this.V = v;
            adj = (Set<Integer>[]) new HashSet[V];
            for (int i = 0; i < V; i++)
                adj[i] = new HashSet<>();
        }

        public int V() {
            return this.V;
        }

        public void addEdge(int v, int w) {
            adj[v].add(w);
            adj[w].add(v);
            this.E++;
        }

        public Iterable<Integer> adj(int v) {
            return adj[v];
        }

        public int degree(int v) {
            return adj[v].size();
        }
    }

    public static class DepthFirstSearch {
        private int[] edgeTo;
        private boolean[] marked;
        private int s;

        public DepthFirstSearch(Graph G, int source) {
            this.s = source;
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            dfs(G, s);
        }

        public void dfs(Graph G, int v) {
            this.marked[v] = true;
            for (int w : G.adj(v)) {
                if (!marked[w]) {
                    edgeTo[w] = v;
                    dfs(G, w);
                }
            }
        }

        public boolean hasPathTo(int w) {
            return marked[w];
        }

        public Iterable<Integer> pathTo(int w) {
            Stack<Integer> path = null;
            if (this.hasPathTo(w)) {
                path = new Stack<>();
                for (int i = w; i != s; i = edgeTo[i]) {
                    path.add(i);
                }
                path.add(s);
            }
            return path;
        }

    }

    public static class BreadthFirstPaths {
        private int[] edgeTo;
        private int[] distTo;
        private boolean[] marked;

        private int s;

        public BreadthFirstPaths(Graph G, int source) {
            this.s = source;
            distTo = new int[G.V()];
            marked = new boolean[G.V()];
            edgeTo = new int[G.V()];
            for (int i = 0; i < G.V(); i++)
                distTo[i] = Integer.MAX_VALUE;
            bfs(G, s);
        }

        private void bfs(Graph G, int s) {
            Queue<Integer> q = new LinkedList<>();
            distTo[s] = 0;
            marked[s] = true;
            q.add(s);
            while (!q.isEmpty()) {
                int v = q.poll();
                for (int w : G.adj(v)) {
                    if (!marked[w]) {
                        marked[w] = true;
                        edgeTo[w] = v;
                        distTo[w] = distTo[v] + 1;
                        q.add(w);
                    }
                }
            }
        }

        public boolean hasPathTo(int w) {
            return marked[w];
        }

        public int distanceTo(int w) {
            return distTo[w];
        }

        public Iterable<Integer> pathTo(int w) {
            Stack<Integer> path = null;
            if (this.hasPathTo(w)) {
                path = new Stack<>();
                for (int i = w; i != s; i = edgeTo[i]) {
                    path.add(i);
                }
                path.add(s);
            }
            return path;
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


    private Map<String, List<String>> preProcess(List<String> completeWordList) {
        Map<String, List<String>> identity = new HashMap<>();
        return completeWordList.stream().reduce(identity,
                (accum, word) -> {
                    for (int i = 0; i < word.length(); i++) {
                        String key = word.substring(0, i) + "*" + word.substring(i + 1);
                        List<String> values = accum.getOrDefault(key, new ArrayList<>());
                        values.add(word);
                        accum.put(key, values);
                    }
                    return accum;
                },
                (map1, map2) -> {
                    map1.putAll(map2);
                    return map1;
                });
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord))
            return 0;
        int endWordVertexId = wordList.indexOf(endWord);
        String[] wordArray = new String[wordList.size() + 1];
        Graph G = new Graph(wordList.size() + 1);
        int vertexId = 0;

        wordArray[vertexId++] = beginWord;
        for (String word : wordList) {
            wordArray[vertexId++] = word;
        }
        endWordVertexId++;
        //System.out.println("End Word Vettex Id : " + endWordVertexId);
        //System.out.println("WordArray is " + Arrays.asList(wordArray).toString());
        for (int i = 0; i < wordArray.length; i++) {
            String v = wordArray[i];
            for (int j = i + 1; j < wordArray.length; j++) {
                String w = wordArray[j];
                if (isConnected(v, w)) {
                    //System.out.println("Adding Edge between " + v + " and " + w);
                    G.addEdge(i, j);
                }
            }
        }
        //System.out.println("Graph G has V : " + G.V() + " and E : " + G.E);
        BreadthFirstPaths bfs = new BreadthFirstPaths(G, 0);
        //System.out.println("Has Path to Result " + bfs.hasPathTo(endWordVertexId));
        return bfs.hasPathTo(endWordVertexId) ? bfs.distanceTo(endWordVertexId) + 1 : 0;
    }

    public static void main(String[] args) {
        String beginWord = "hit", endWord = "cog";
        List<String> wordList = Arrays.asList("hot", "dot", "dog", "lot", "log", "cog");
        WordLadder wl = new WordLadder();
        System.out.println(wl.ladderLength(beginWord, endWord, wordList));
    }

}
