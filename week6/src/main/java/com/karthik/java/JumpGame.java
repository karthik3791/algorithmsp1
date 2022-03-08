package com.karthik.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

public class JumpGame {

	public static class Digraph {
		private int V;
		private int E;
		private List<Integer>[] adj;

		public Digraph(int V) {
			this.V = V;
			this.E = 0;
			adj = (List<Integer>[]) new ArrayList[V];
			for (int i = 0; i < V; i++)
				adj[i] = new ArrayList<>();
		}

		public int V() {
			return this.V;
		}

		public void addEdge(int v, int w) {
			adj[v].add(w);
			E++;
		}

		public Iterable<Integer> adj(int v) {
			return adj[v];
		}
	}

	public static class DepthFirstDirectedPaths {
		private boolean[] marked;
		private int[] edgeTo;
		private int s;

		public DepthFirstDirectedPaths(Digraph G, int s) {
			marked = new boolean[G.V];
			edgeTo = new int[G.V];
			this.s = s;
			dfs(G, s);
		}

		public void dfs(Digraph G, int v) {
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

		public BreadthFirstDirectedPaths(Digraph G, int s) {
			this.marked = new boolean[G.V];
			this.edgeTo = new int[G.V];
			this.distTo = new int[G.V];
			for (int v = 0; v < G.V(); v++)
				distTo[v] = Integer.MAX_VALUE;
			bfs(G, s);
		}

		public void bfs(Digraph G, int s) {
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

	public boolean canJump(int[] nums) {
		Digraph G = new Digraph(nums.length);
		for (int i = 0; i < nums.length; i++) {
			int currentVal = nums[i];
			if (i + currentVal >= nums.length - 1) {
				G.addEdge(i, nums.length - 1);
				break;
			}
			for (int j = 1; j <= currentVal && i + j < nums.length; j++) {
				G.addEdge(i, i + j);
			}
		}
		BreadthFirstDirectedPaths bfs = new BreadthFirstDirectedPaths(G, 0);
		return bfs.hasPathTo(nums.length - 1);
	}

	public static void main(String[] args) {
		JumpGame jumpGame = new JumpGame();
		int[] nums1 = { 2, 3, 1, 1, 4 };
		int[] nums2 = { 3, 2, 1, 0, 4 };
		int[] nums3 = { 2, 0, 0 };
		int[] nums4 = { 4, 2, 0, 0, 1, 1, 4, 4, 4, 0, 4, 0 };
		System.out.println("Nums Array 1 can Jump Result : " + jumpGame.canJump(nums1));
		System.out.println("Nums Array 2 can Jump Result : " + jumpGame.canJump(nums2));
		System.out.println("Nums Array 4 can Jump Result : " + jumpGame.canJump(nums4));

	}
}
