package com.karthik.java;

import edu.princeton.cs.algs4.StdStats;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.StdRandom;

public class PercolationStats {
	private static final Logger logger = LoggerFactory.getLogger(PercolationStats.class);
	private double[] openSiteFractionList;
	private double mean;
	private double stddev;
	private int T;
	private int gridSize;

	// perform independent trials on an n-by-n grid
	public PercolationStats(int n, int trials) throws IllegalArgumentException {
		if (n <= 0 || trials <= 0)
			throw new IllegalArgumentException("Invalid Input for PercolationStats");
		gridSize = n;
		openSiteFractionList = new double[trials];
		T = trials;
		runSimulation();
	}

	public void runSimulation() {
		for (int i = 0; i < T; i++) {
			Percolation p = new Percolation(gridSize);
			while (!p.percolates()) {
				int randomRow = StdRandom.uniform(1, gridSize + 1);
				int randomCol = StdRandom.uniform(1, gridSize + 1);
				p.open(randomRow, randomCol);
			}
			double noOfOpenSites = p.numberOfOpenSites();
			openSiteFractionList[i] = (noOfOpenSites) / (gridSize * gridSize);
		}
		mean = StdStats.mean(openSiteFractionList);
		stddev = StdStats.stddev(openSiteFractionList);
	}

	// sample mean of percolation threshold
	public double mean() {
		return mean;
	}

	// sample standard deviation of percolation threshold
	public double stddev() {
		return stddev;
	}

	// low endpoint of 95% confidence interval
	public double confidenceLo() {
		return (mean() - (1.96 * stddev() / Math.sqrt(T)));
	}

	// high endpoint of 95% confidence interval
	public double confidenceHi() {
		return (mean() + (1.96 * stddev() / Math.sqrt(T)));
	}

	// test client (see below)
	public static void main(String[] args) {
		int n = Integer.parseInt(args[0]);
		int trials = Integer.parseInt(args[1]);
		PercolationStats pstats = new PercolationStats(n, trials);
		System.out.println("mean                    = " + pstats.mean());
		System.out.println("stddev                  = " + pstats.stddev());
		System.out.println("95% confidence interval = [" + pstats.confidenceLo() + ", " + pstats.confidenceHi() + "]");
	}

}