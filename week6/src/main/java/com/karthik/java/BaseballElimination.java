package com.karthik.java;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.stream.Collectors;

import edu.princeton.cs.algs4.FlowEdge;
import edu.princeton.cs.algs4.FlowNetwork;
import edu.princeton.cs.algs4.FordFulkerson;
import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class BaseballElimination {

	private static class EliminationResult {
		private boolean isEliminated;
		private Set<String> eliminatedBy;

		public EliminationResult(boolean eliminated, Set<String> eliminatedBy) {
			this.isEliminated = eliminated;
			this.eliminatedBy = eliminatedBy;
		}

		@Override
		public String toString() {
			return "EliminationResult : isEliminated -> " + isEliminated + " EliminatedBy -> " + eliminatedBy;
		}
	}

	private int[] w;
	private int[] l;
	private int[] r;
	private int[][] g;
	private Map<String, Integer> teamMap;
	private Map<String, EliminationResult> eliminationCache;
	private int noOfTeams;

	// create a baseball division from given filename in format specified below
	public BaseballElimination(String filename) {
		In inputFile = new In(filename);
		this.noOfTeams = inputFile.readInt();
		w = new int[noOfTeams];
		l = new int[noOfTeams];
		r = new int[noOfTeams];
		g = new int[noOfTeams][noOfTeams];
		teamMap = new TreeMap<>();
		for (int i = 0; i < noOfTeams; i++) {
			teamMap.put(inputFile.readString(), i);
			w[i] = inputFile.readInt();
			l[i] = inputFile.readInt();
			r[i] = inputFile.readInt();
			for (int j = 0; j < noOfTeams; j++) {
				g[i][j] = inputFile.readInt();
			}
		}
		eliminationCache = new HashMap<>();
	}

	// number of teams
	public int numberOfTeams() {
		return noOfTeams;
	}

	// all teams
	public Iterable<String> teams() {
		return new TreeSet<>(teamMap.keySet());
	}

	// number of wins for given team
	public int wins(String team) {
		if (!teamMap.containsKey(team))
			throw new IllegalArgumentException("Invalid Team.");
		return w[teamMap.get(team)];
	}

	// number of losses for given team
	public int losses(String team) {
		if (!teamMap.containsKey(team))
			throw new IllegalArgumentException("Invalid Team.");
		return l[teamMap.get(team)];
	}

	// number of remaining games for given team
	public int remaining(String team) {
		if (!teamMap.containsKey(team))
			throw new IllegalArgumentException("Invalid Team.");
		return r[teamMap.get(team)];
	}

	// number of remaining games between team1 and team2
	public int against(String team1, String team2) {
		if (!teamMap.containsKey(team1) || !teamMap.containsKey(team2))
			throw new IllegalArgumentException("Invalid Team.");
		return g[teamMap.get(team1)][teamMap.get(team2)];
	}

	private void computeTrivialElimination(String team) {
		if (eliminationCache.get(team) != null && eliminationCache.get(team).isEliminated)
			return;
		boolean isEliminated = false;
		Set<String> eliminatedBy = null;
		int x = teamMap.get(team);
		for (Map.Entry<String, Integer> entry : teamMap.entrySet()) {
			int i = entry.getValue();
			if (i == x)
				continue;
			if (w[x] + r[x] < w[i]) {
				isEliminated = true;
				eliminatedBy = new TreeSet<>();
				eliminatedBy.add(entry.getKey());
				break;
			}
		}
		EliminationResult res = new EliminationResult(isEliminated, eliminatedBy);
		// System.out.println("Trivial Elimination Outcome : " + res.toString());
		eliminationCache.put(team, res);
	}

	private int getVertexId(int i, int j) {
		return i * noOfTeams + j;
	}

	private void computeFlowNetworkElimination(String team) {
		if (eliminationCache.get(team) != null && eliminationCache.get(team).isEliminated)
			return;
		boolean eliminated = false;
		Set<String> eliminatedBy = null;

		int x = teamMap.get(team);
		int N = this.noOfTeams;
		int V = 2 + N * N;
		int s = V - 1, t = V - 2;
		FlowNetwork F = new FlowNetwork(V);
		for (int i = 0; i < this.noOfTeams; i++) {
			if (i == x)
				continue;
			F.addEdge(new FlowEdge(getVertexId(i, i), t, w[x] + r[x] - w[i]));
			for (int j = i + 1; j < this.noOfTeams; j++) {
				if (j == x)
					continue;
				F.addEdge(new FlowEdge(s, getVertexId(i, j), g[i][j]));
				F.addEdge(new FlowEdge(getVertexId(i, j), getVertexId(i, i), Double.POSITIVE_INFINITY));
				F.addEdge(new FlowEdge(getVertexId(i, j), getVertexId(j, j), Double.POSITIVE_INFINITY));
			}
		}
		FordFulkerson ff = new FordFulkerson(F, s, t);

		for (FlowEdge e : F.adj(s)) {
			if (e.residualCapacityTo(e.other(s)) > 0) {
				eliminated = true;
				break;
			}
		}
		if (eliminated) {
			eliminatedBy = teamMap.keySet().stream().filter(tt -> {
				int i = teamMap.get(tt);
				return (ff.inCut(getVertexId(i, i)));
			}).collect(Collectors.toSet());
		}
		EliminationResult res = new EliminationResult(eliminated, eliminatedBy);
		// System.out.println("Flow Network Elimination Outcome : " + res.toString());
		eliminationCache.put(team, res);
	}

	// is given team eliminated?
	public boolean isEliminated(String team) {
		if (!teamMap.containsKey(team))
			throw new IllegalArgumentException("Invalid Team.");
		// System.out.println("isEliminated(" + team + ")");
		if (eliminationCache.get(team) == null) {
			computeTrivialElimination(team);
			computeFlowNetworkElimination(team);
		}
		return eliminationCache.get(team).isEliminated;
	}

	// subset R of teams that eliminates given team; null if not eliminated
	public Iterable<String> certificateOfElimination(String team) {
		if (!teamMap.containsKey(team))
			throw new IllegalArgumentException("Invalid Team.");
		if (eliminationCache.get(team) == null) {
			computeTrivialElimination(team);
			computeFlowNetworkElimination(team);
		}
		Set<String> eliminatedBy = eliminationCache.get(team).eliminatedBy;
		return eliminatedBy == null ? null : new TreeSet<>(eliminatedBy);
	}

	public static void main(String[] args) {
		BaseballElimination division = new BaseballElimination(args[0]);
		for (String team : division.teams()) {
			if (division.isEliminated(team)) {
				StdOut.print(team + " is eliminated by the subset R = { ");
				for (String t : division.certificateOfElimination(team)) {
					StdOut.print(t + " ");
				}
				StdOut.println("}");
			} else {
				StdOut.println(team + " is not eliminated");
			}
		}
	}
}
