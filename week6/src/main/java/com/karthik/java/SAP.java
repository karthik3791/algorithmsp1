package com.karthik.java;

import edu.princeton.cs.algs4.BreadthFirstDirectedPaths;
import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdIn;
import edu.princeton.cs.algs4.StdOut;

public class SAP {
	private Digraph digraph;

	private void validateVertex(Integer v) {
		if (v == null)
			throw new IllegalArgumentException("Invalid null parameter.");
		if (v < 0 || v >= digraph.V())
			throw new IllegalArgumentException("Vertex argument should be within the range.");
	}

	// constructor takes a digraph (not necessarily a DAG)
	public SAP(Digraph G) {
		if (G == null)
			throw new IllegalArgumentException("Illegal Null Parameter.");
		digraph = G;

	}

	// length of shortest ancestral path between v and w; -1 if no such path
	public int length(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		int minLength = Integer.MAX_VALUE;
		int ancestor = -1;
		boolean ancestorFound = false;
		BreadthFirstDirectedPaths v_bfs = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths w_bfs = new BreadthFirstDirectedPaths(digraph, w);
		for (int i = 0; i < digraph.V(); i++) {
			if (v_bfs.hasPathTo(i) && w_bfs.hasPathTo(i)) {
				ancestorFound = true;
				int pathcount = v_bfs.distTo(i) + w_bfs.distTo(i);
				if (pathcount < minLength) {
					minLength = pathcount;
					ancestor = i;
				}
			}
		}
		return ancestorFound ? minLength : -1;
	}

	// a common ancestor of v and w that participates in a shortest ancestral path;
	// -1 if no such path
	public int ancestor(int v, int w) {
		validateVertex(v);
		validateVertex(w);
		int minLength = Integer.MAX_VALUE;
		int ancestor = -1;
		boolean ancestorFound = false;
		BreadthFirstDirectedPaths v_bfs = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths w_bfs = new BreadthFirstDirectedPaths(digraph, w);
		for (int i = 0; i < digraph.V(); i++) {
			if (v_bfs.hasPathTo(i) && w_bfs.hasPathTo(i)) {
				ancestorFound = true;
				int pathcount = v_bfs.distTo(i) + w_bfs.distTo(i);
				if (pathcount < minLength) {
					minLength = pathcount;
					ancestor = i;
				}
			}
		}
		return ancestorFound ? ancestor : -1;
	}

	// length of shortest ancestral path between any vertex in v and any vertex in
	// w; -1 if no such path
	public int length(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new IllegalArgumentException("Illegal Null Parameter.");
		for (Integer setAVertex : v) {
			validateVertex(setAVertex);
		}
		for (Integer setBVertex : w) {
			validateVertex(setBVertex);
		}
		int minLength = Integer.MAX_VALUE;
		int ancestor = -1;
		boolean ancestorFound = false;
		BreadthFirstDirectedPaths setABfs = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths setBBfs = new BreadthFirstDirectedPaths(digraph, w);
		for (int i = 0; i < digraph.V(); i++) {
			if (setABfs.hasPathTo(i) && setBBfs.hasPathTo(i)) {
				ancestorFound = true;
				int pathcount = setABfs.distTo(i) + setBBfs.distTo(i);
				if (pathcount < minLength) {
					minLength = pathcount;
					ancestor = i;
				}
			}
		}
		return ancestorFound ? minLength : -1;
	}

	// a common ancestor that participates in shortest ancestral path; -1 if no such
	// path
	public int ancestor(Iterable<Integer> v, Iterable<Integer> w) {
		if (v == null || w == null)
			throw new IllegalArgumentException("Illegal Null Parameter.");
		for (Integer setAVertex : v) {
			validateVertex(setAVertex);
		}
		for (Integer setBVertex : w) {
			validateVertex(setBVertex);
		}
		int minLength = Integer.MAX_VALUE;
		int ancestor = -1;
		boolean ancestorFound = false;
		BreadthFirstDirectedPaths setABfs = new BreadthFirstDirectedPaths(digraph, v);
		BreadthFirstDirectedPaths setBBfs = new BreadthFirstDirectedPaths(digraph, w);
		for (int i = 0; i < digraph.V(); i++) {
			if (setABfs.hasPathTo(i) && setBBfs.hasPathTo(i)) {
				ancestorFound = true;
				int pathcount = setABfs.distTo(i) + setBBfs.distTo(i);
				if (pathcount < minLength) {
					minLength = pathcount;
					ancestor = i;
				}
			}
		}
		return ancestorFound ? ancestor : -1;
	}

	public static void main(String[] args) {
		In in = new In(args[0]);
		Digraph G = new Digraph(in);
		SAP sap = new SAP(G);
		while (!StdIn.isEmpty()) {
			int v = StdIn.readInt();
			int w = StdIn.readInt();
			int length = sap.length(v, w);
			int ancestor = sap.ancestor(v, w);
			StdOut.printf("length = %d, ancestor = %d\n", length, ancestor);
		}
	}

}
