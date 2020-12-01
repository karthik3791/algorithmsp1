package com.karthik.java;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PercolationTest {
	private static final Logger logger = LoggerFactory.getLogger(PercolationTest.class);

	@Test
	public void verifyTopRowAfterInitialization() {
		int gridSize = 4;
		Percolation p = new Percolation(gridSize);
		assertTrue(p.quw.find(0) == 0);
		for (int i = 1; i <= gridSize; i++) {
			assertTrue(p.isFull(1, i));
		}
	}

	@Test
	public void verifyBottomRowAfterInitialization() {
		int gridSize = 4;
		int quwStoreSize = (gridSize * gridSize) + 2;
		Percolation p = new Percolation(gridSize);
		assertTrue(p.quw.find(quwStoreSize - 1) == quwStoreSize - 1);
		for (int i = 1; i <= gridSize; i++) {
			assertTrue(p.quw.find(quwStoreSize - 1) == p.quw.find(p.getQUWFromSiteRowCol(gridSize, i)));
		}
	}

	@Test
	public void verifyOpenSiteLogic() {
		int gridSize = 4;
		int quwStoreSize = (gridSize * gridSize) + 2;
		Percolation p = new Percolation(gridSize);
		logger.info("QUW No. of Connected Components : " + p.quw.count());
		int originalCount = p.quw.count();
		p.open(1, 2);
		p.open(2, 1);
		p.open(3, 2);
		p.open(2, 3);
		assertTrue(p.quw.count() == originalCount);
		p.open(2, 2);
		assertTrue(p.quw.count() == originalCount - 4);
	}
}
