package com.karthik.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
	private static final Logger logger = LoggerFactory.getLogger(Percolation.class);
	private int gridSize;
	private int quwStoreSize;
	private int quw2StoreSize;
	private boolean[][] siteOpenStatus;
	protected WeightedQuickUnionUF quw;
	protected WeightedQuickUnionUF quw2;
	private int noOfOpenSites = 0;

	// creates n-by-n grid, with all sites initially blocked
	public Percolation(int n) throws IllegalArgumentException {
		if (n <= 0) {
			throw new IllegalArgumentException("Expected N size to be > 0");
		}
		gridSize = n;
		quwStoreSize = (n * n) + 2; // 2 additional for the virtual sites to connect Top and Bottom Rows.
		quw2StoreSize = (n * n) + 1;
		initializeSiteOpenStatusMap();
		initializeQUWStore();
		initializeQUW2Store();
	}

	private void initializeSiteOpenStatusMap() {
		siteOpenStatus = new boolean[gridSize][gridSize];
		for (int i = 0; i < gridSize; i++) {
			for (int j = 0; j < gridSize; j++) {
				siteOpenStatus[i][j] = false;
			}
		}
	}

	private void initializeQUWStore() {
		quw = new WeightedQuickUnionUF(quwStoreSize);
		for (int j = 0; j < gridSize; j++) {
			quw.union(0, getQUWFromSiteIndex(0, j));
			quw.union(quwStoreSize - 1, getQUWFromSiteIndex(gridSize - 1, j));
		}
	}

	private void initializeQUW2Store() {
		quw2 = new WeightedQuickUnionUF(quw2StoreSize);
		for (int j = 0; j < gridSize; j++) {
			quw2.union(0, getQUWFromSiteIndex(0, j));
		}
	}

	protected int getQUWFromSiteIndex(int siteRowIndex, int siteColIndex) {
		return (siteRowIndex * gridSize) + (siteColIndex + 1);
	}

	protected int getQUWFromSiteRowCol(int row, int col) {
		return getQUWFromSiteIndex(row - 1, col - 1);
	}

	// opens the site (row, col) if it is not open already
	public void open(int row, int col) throws IllegalArgumentException {
		if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
			throw new IllegalArgumentException("Invalid input for row and col");
		}
		if (!siteOpenStatus[row - 1][col - 1]) {
			siteOpenStatus[row - 1][col - 1] = true;
			noOfOpenSites++;
			connectWithOpenAdjacentSites(row - 1, col - 1);
		}
	}

	private void connectWithOpenAdjacentSites(int i, int j) {
		for (int k = i - 1; k <= i + 1; k++) {
			if (k >= 0 && k < gridSize && siteOpenStatus[k][j]) {
				quw.union(getQUWFromSiteIndex(i, j), getQUWFromSiteIndex(k, j));
				quw2.union(getQUWFromSiteIndex(i, j), getQUWFromSiteIndex(k, j));
			}
		}
		for (int k = j - 1; k <= j + 1; k++) {
			if (k >= 0 && k < gridSize && siteOpenStatus[i][k]) {
				quw.union(getQUWFromSiteIndex(i, j), getQUWFromSiteIndex(i, k));
				quw2.union(getQUWFromSiteIndex(i, j), getQUWFromSiteIndex(i, k));
			}
		}
	}

	// is the site (row, col) open?
	public boolean isOpen(int row, int col) throws IllegalArgumentException {
		if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
			throw new IllegalArgumentException("Invalid input for row and col");
		}
		return siteOpenStatus[row - 1][col - 1];
	}

	// is the site (row, col) full?
	public boolean isFull(int row, int col) throws IllegalArgumentException {
		if (row < 1 || row > gridSize || col < 1 || col > gridSize) {
			throw new IllegalArgumentException("Invalid input for row and col");
		}
		return isOpen(row, col) && quw2.find(0) == quw2.find(getQUWFromSiteRowCol(row, col));
	}

	// returns the number of open sites
	public int numberOfOpenSites() {
		return noOfOpenSites;
	}

	// does the system percolate?
	public boolean percolates() {
		return (((gridSize == 1 && noOfOpenSites == 1) || gridSize != 1) && quw.find(0) == quw.find(quwStoreSize - 1));

	}

}