package com.karthik.java;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

public class Outcast {

	private WordNet wordnet;

	// constructor takes a WordNet object
	public Outcast(WordNet wordnet) {
		this.wordnet = wordnet;
	}

	// given an array of WordNet nouns, return an outcast
	public String outcast(String[] nouns) {
		String outcastNoun = null;
		int maxDistance = Integer.MIN_VALUE;
		for (int i = 0; i < nouns.length; i++) {
			int distanceI = 0;
			for (int j = 0; j < nouns.length; j++) {
				if (j == i)
					continue;
				int distanceIJ = wordnet.distance(nouns[i], nouns[j]);
				//System.out.println("Distance of " + nouns[i] + " with " + nouns[j] + " : " + distanceIJ);
				if (distanceIJ == -1) {
					return nouns[i];
				}
				distanceI += distanceIJ;
			}
			//System.out.println("Total Distance of " + nouns[i] + " : " + distanceI);

			if (distanceI > maxDistance) {
				//System.out.println("Found New Max : " + nouns[i]);
				maxDistance = distanceI;
				outcastNoun = nouns[i];
			}
		}
		return outcastNoun;
	}

	public static void main(String[] args) {
		WordNet wordnet = new WordNet(args[0], args[1]);
		Outcast outcast = new Outcast(wordnet);
		for (int t = 2; t < args.length; t++) {
			In in = new In(args[t]);
			String[] nouns = in.readAllStrings();
			StdOut.println(args[t] + ": " + outcast.outcast(nouns));
		}
	}

}
