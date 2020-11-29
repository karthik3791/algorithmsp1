package com.karthik.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUtilsTest {
	private static final Logger logger = LoggerFactory.getLogger(ListUtilsTest.class);

	@Test
	public void binarySearchTreeAscending() {
		int[] sortedInput = { 0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14 };
		int resultIndex = ListUtils.findElementIndexViaBinarySearch(sortedInput, 19, 0, 14, true);
		assertEquals("Binary Search Result ", -1, resultIndex);
		resultIndex = ListUtils.findElementIndexViaBinarySearch(sortedInput, 12, 0, 14, true);
		assertEquals("Binary Search Result ", 12, resultIndex);
	}

	@Test
	public void binarySearchTreeDescending() {
		int[] sortedInput = { 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1, 0 };
		int resultIndex = ListUtils.findElementIndexViaBinarySearch(sortedInput, 12, 0, 14, false);
		logger.info("Result Index : " + resultIndex);
		assertEquals("Binary Search Result ", 2, resultIndex);
		resultIndex = ListUtils.findElementIndexViaBinarySearch(sortedInput, 19, 0, 14, false);
		assertEquals("Binary Search Result ", -1, resultIndex);
	}

	@Test
	public void bitonicMaxIndexCheck() {
		int[] bitonicArray = { -3, 9, 18, 20, 17, 5, 1 };
		int resultIndex = ListUtils.findIndexWithMaxOnBitonicArray(bitonicArray, 0, 6);
		logger.info("Result Index : " + resultIndex);
		assertEquals("Bitonic Max Index Check", 3, resultIndex);
	}
}
