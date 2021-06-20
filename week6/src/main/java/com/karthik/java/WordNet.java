package com.karthik.java;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import edu.princeton.cs.algs4.Digraph;
import edu.princeton.cs.algs4.DirectedCycle;
import edu.princeton.cs.algs4.In;

public class WordNet {

	private Map<String, Set<Integer>> synsetIdSetKeyedByNoun = new HashMap<>();
	private Map<Integer, String> nounsKeyedBySynsetId = new HashMap<>();
	private Digraph synsetGraph;
	private SAP sap;

	private void loadSynsets(String synsets) {
		In synsetFile = new In(synsets);
		while (synsetFile.hasNextLine()) {
			String[] currentLineTokens = synsetFile.readLine().split(",");
			Integer synsetId = Integer.parseInt(currentLineTokens[0]);
			String nouns = currentLineTokens[1];
			nounsKeyedBySynsetId.put(synsetId, nouns);
			for (String noun : nouns.split(" ")) {
				Set<Integer> synsetIdSet = synsetIdSetKeyedByNoun.get(noun);
				if (synsetIdSet == null) {
					synsetIdSet = new HashSet<>();
					synsetIdSetKeyedByNoun.put(noun, synsetIdSet);
				}
				synsetIdSet.add(synsetId);
			}
		}
	}

	private void loadHypernyms(String hypernyms) {
		In hypernymsFile = new In(hypernyms);
		String[] hypernymFileDump = hypernymsFile.readAllLines();
		Integer V = hypernymFileDump.length;
		synsetGraph = new Digraph(V);
		for (String currentLine : hypernymFileDump) {
			String[] currentLineTokens = currentLine.split(",");
			Integer v = Integer.parseInt(currentLineTokens[0]);
			for (int i = 1; i < currentLineTokens.length; i++) {
				Integer w = Integer.parseInt(currentLineTokens[i]);
				synsetGraph.addEdge(v, w);
			}
		}
		sap = new SAP(synsetGraph);
	}

	private boolean containsCycle() {
		return new DirectedCycle(synsetGraph).hasCycle();
	}

	private boolean rootedGraph() {
		List<Integer> verticesWithNoOutDegree = new ArrayList<>();
		for (int v = 0; v < synsetGraph.V(); v++) {
			int outdegreeCount = 0;
			for (Integer w : synsetGraph.adj(v)) {
				outdegreeCount++;
			}
			if (outdegreeCount == 0)
				verticesWithNoOutDegree.add(v);
		}
		return verticesWithNoOutDegree.size() == 1;
	}

	// constructor takes the name of the two input files
	public WordNet(String synsets, String hypernyms) {
		if (synsets == null || hypernyms == null)
			throw new IllegalArgumentException("Illegal Null Parameter.");
		loadSynsets(synsets);
		loadHypernyms(hypernyms);
		if (containsCycle() || !rootedGraph())
			throw new IllegalArgumentException("Digraph is not a rooted DAG. Invalid Argument.");
	}

	// returns all WordNet nouns
	public Iterable<String> nouns() {
		return synsetIdSetKeyedByNoun.keySet();
	}

	// is the word a WordNet noun?
	public boolean isNoun(String word) {
		if (word == null)
			throw new IllegalArgumentException("Illegal Null Parameter.");
		return synsetIdSetKeyedByNoun.get(word) != null;
	}

	// distance between nounA and nounB (defined below)
	public int distance(String nounA, String nounB) {
		if (nounA == null || nounB == null)
			throw new IllegalArgumentException("Illegal Null Parameter.");
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException("Illegal Noun parameter which is not a Wordnet Noun.");
		return sap.length(synsetIdSetKeyedByNoun.get(nounA), synsetIdSetKeyedByNoun.get(nounB));
	}

	// a synset (second field of synsets.txt) that is the common ancestor of nounA
	// and nounB
	// in a shortest ancestral path (defined below)
	public String sap(String nounA, String nounB) {
		if (nounA == null || nounB == null)
			throw new IllegalArgumentException("Illegal Null Parameter.");
		if (!isNoun(nounA) || !isNoun(nounB))
			throw new IllegalArgumentException("Illegal Noun parameter which is not a Wordnet Noun.");
		return nounsKeyedBySynsetId
				.get(sap.ancestor(synsetIdSetKeyedByNoun.get(nounA), synsetIdSetKeyedByNoun.get(nounB)));
	}

	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		System.out.println(wordnet.isNoun("bird"));
		String sapBirdWorm = wordnet.sap("worm", "bird");
		int birdWormDistance = wordnet.distance("bird", "worm");
		System.out.println("SAP for worm and bird is " + sapBirdWorm);
		System.out.println("Distance " + birdWormDistance);
		System.out.println("SAP for municipality and region is " + wordnet.sap("municipality", "region"));

	}

}
