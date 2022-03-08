package com.karthik.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.Bag;
import edu.princeton.cs.algs4.In;

public class CriticalConnections {

	public static class Graph {
		private int V;
		private int E;
		private Set<Integer>[] adj;

		public Graph(int V) {
			this.V = V;
			this.E = 0;
			adj = (Set<Integer>[]) new HashSet[V];
			for (int i = 0; i < V; i++)
				adj[i] = new HashSet<>();
		}

		public Graph(In in) {
			if (in == null)
				throw new IllegalArgumentException("argument is null");
			try {
				this.V = in.readInt();
				if (V < 0)
					throw new IllegalArgumentException("number of vertices in a Graph must be nonnegative");
				adj = (Set<Integer>[]) new HashSet[V];
				for (int v = 0; v < V; v++) {
					adj[v] = new HashSet<>();
				}
				int E = in.readInt();
				if (E < 0)
					throw new IllegalArgumentException("number of edges in a Graph must be nonnegative");
				for (int i = 0; i < E; i++) {
					int v = in.readInt();
					int w = in.readInt();
					addEdge(v, w);
				}
			} catch (NoSuchElementException e) {
				throw new IllegalArgumentException("invalid input format in Graph constructor", e);
			}
		}

		public int V() {
			return this.V;
		}

		public void addEdge(int v, int w) {
			adj[v].add(w);
			adj[w].add(v);
			E++;
		}

		public void removeEdge(int v, int w) {
			adj[v] = adj[v].stream().filter(x -> x != w).collect(Collectors.toSet());
			adj[w] = adj[w].stream().filter(x -> x != v).collect(Collectors.toSet());
			E--;
		}

		public Iterable<Integer> adj(int v) {
			return adj[v];
		}

		public int degree(int v) {
			return adj[v].size();
		}
	}

	public static class DepthFirstDirectedPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private int s;

		public DepthFirstDirectedPaths(Graph G, int s) {
			marked = new boolean[G.V];
			edgeTo = new int[G.V];
			this.s = s;
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

		public boolean hasPathTo(int v) {
			return marked[v];
		}

		public Iterable<Integer> pathTo(int v) {
			if (!hasPathTo(v))
				return null;
			Stack<Integer> path = new Stack<>();
			for (int x = v; x != s; x = edgeTo[x]) {
				path.push(x);
			}
			path.push(s);
			return path;
		}
	}

	public static class BreadthFirstDirectedPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private int[] distTo;
		private int s;

		public BreadthFirstDirectedPaths(Graph G, int s) {
			this.marked = new boolean[G.V];
			this.edgeTo = new int[G.V];
			this.distTo = new int[G.V];
			for (int v = 0; v < G.V(); v++)
				distTo[v] = Integer.MAX_VALUE;
			bfs(G, s);
		}

		public void bfs(Graph G, int s) {
			this.marked[s] = true;
			this.distTo[s] = 0;
			this.s = s;
			Queue<Integer> bfsQueue = new LinkedList<>();
			bfsQueue.add(s);
			while (!bfsQueue.isEmpty()) {
				Integer v = bfsQueue.poll();
				for (int w : G.adj(v)) {
					if (!marked[w]) {
						bfsQueue.add(w);
						this.distTo[w] = this.distTo[v] + 1;
						this.edgeTo[w] = v;
						this.marked[w] = true;
					}
				}
			}
		}

		public boolean hasPathTo(int v) {
			return marked[v];
		}

		public Iterable<Integer> pathTo(int v) {
			if (!hasPathTo(v))
				return null;
			Stack<Integer> path = new Stack<>();
			for (int x = v; x != s; x = edgeTo[x]) {
				path.push(x);
			}
			path.push(s);
			return path;
		}
	}

	private List<List<Integer>> criticalConnections(Graph G) {
		DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, 0);
		BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, 0);
		List<List<Integer>> possibleConnections = new ArrayList<>();
		for (int v = 1; v < G.V(); v++) {
			if (dfs.edgeTo[v] == bfs.edgeTo[v]) {
				possibleConnections.add(Arrays.asList(v, dfs.edgeTo[v]));
			}
		}
		System.out.println("Possible Connections Size : " + possibleConnections.size());
		System.out.println("Possible Connections : " + possibleConnections);
		List<List<Integer>> finalResult = new ArrayList<>();
		for (List<Integer> edgePair : possibleConnections) {
			int v = edgePair.get(1);
			int w = edgePair.get(0);
			System.out.println("Currently Checking V : " + v + " and W : " + w);
			Set<Integer> otherNodesLinkedToV = new HashSet<>();
			for (int x : dfs.pathTo(v)) {
				for (int y : G.adj[x]) {
					if (y != w && y != v)
						otherNodesLinkedToV.add(y);
				}
			}
			if (otherNodesLinkedToV.size() == 0)
				finalResult.add(edgePair);
			else {
				boolean wConnectedToNodeLinkedToV = false;
				for (int y : G.adj(w)) {
					if (otherNodesLinkedToV.contains(y)) {
						System.out.println("Found Direct Link from w :" + w + " to " + y);
						System.out.println("Eliminating Edge [" + v + "," + w + "]");
						wConnectedToNodeLinkedToV = true;
						break;
					}
				}
				for (int x : otherNodesLinkedToV) {
					if (wConnectedToNodeLinkedToV)
						break;
					System.out.println("Checking if there is a path from w :" + w + " to X : " + x);

					for (int j = x; j != v; j = dfs.edgeTo[j]) {
						if (j == w) {
							System.out.println("Found DFS Path from W :" + w + " to X : " + x);
							System.out.println("Eliminating Edge [" + v + "," + w + "]");
							wConnectedToNodeLinkedToV = true;
							break;
						}
						if (j == 0)
							break;
					}
				}
				if (!wConnectedToNodeLinkedToV) {
					System.out.println("Detected Critical Edge [" + v + "," + w + "]");
					finalResult.add(edgePair);
				}
			}
		}
		return finalResult;
	}

	public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
		int V = n;
		Graph G = new Graph(V);
		for (List<Integer> edgePair : connections) {
			G.addEdge(edgePair.get(0), edgePair.get(1));
		}
		DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, 0);
		return criticalConnections(G);
	}

	private static void otherTest() {
		In criticalConnectionFile = new In("criticalConnections.txt");
		Graph G = new Graph(criticalConnectionFile);
		// G.removeEdge(31, 239);
//		DepthFirstDirectedPaths dfs = new DepthFirstDirectedPaths(G, 0);
//		System.out.println("Path to 4 " + dfs.pathTo(4));
//		System.out.println("Path to 5 " + dfs.pathTo(5));
//		System.out.println("Path to 6 " + dfs.pathTo(6));
		System.out.println(new CriticalConnections().criticalConnections(G));
	}

	public static void main(String[] args) {

//		int n = 4;
//		List<List<Integer>> connections = new ArrayList<>();
//		connections.add(Arrays.asList(0, 1));
//		connections.add(Arrays.asList(1, 2));
//		connections.add(Arrays.asList(2, 0));
//		connections.add(Arrays.asList(1, 3));
//		System.out.println(new CriticalConnections().criticalConnections(n, connections));
//
//		n = 6;
//		connections = new ArrayList<>();
//		connections.add(Arrays.asList(0, 1));
//		connections.add(Arrays.asList(1, 2));
//		connections.add(Arrays.asList(2, 0));
//		connections.add(Arrays.asList(1, 3));
//		connections.add(Arrays.asList(3, 4));
//		connections.add(Arrays.asList(4, 5));
//		connections.add(Arrays.asList(5, 3));
//		System.out.println(new CriticalConnections().criticalConnections(n, connections));
//
//		n = 5;
//		connections = new ArrayList<>();
//		connections.add(Arrays.asList(1, 0));
//		connections.add(Arrays.asList(2, 0));
//		connections.add(Arrays.asList(3, 2));
//		connections.add(Arrays.asList(4, 2));
//		connections.add(Arrays.asList(4, 3));
//		connections.add(Arrays.asList(3, 0));
//		connections.add(Arrays.asList(4, 0));
//		System.out.println(new CriticalConnections().criticalConnections(n, connections));
//
//		n = 8;
//		connections = new ArrayList<>();
//		connections.add(Arrays.asList(0, 1));
//		connections.add(Arrays.asList(1, 2));
//		connections.add(Arrays.asList(2, 0));
//		connections.add(Arrays.asList(1, 3));
//		connections.add(Arrays.asList(3, 4));
//		connections.add(Arrays.asList(4, 5));
//		connections.add(Arrays.asList(5, 3));
//		connections.add(Arrays.asList(5, 6));
//		connections.add(Arrays.asList(6, 7));
//		connections.add(Arrays.asList(6, 2));
//		connections.add(Arrays.asList(7, 2));
//		System.out.println(new CriticalConnections().criticalConnections(n, connections));

		otherTest();
	}
}
