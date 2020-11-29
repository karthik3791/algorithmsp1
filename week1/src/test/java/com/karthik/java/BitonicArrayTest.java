package com.karthik.java;

import static org.junit.Assert.assertEquals;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitonicArrayTest {
	private static final Logger logger = LoggerFactory.getLogger(BitonicArrayTest.class);

	@Test
	public void binarySearchTreeAscending() {
		int[] arr = { -3, 9, 18, 20, 17, 5, 1 };
		int resultIndex = BitonicArray.findElement(arr, 0, 6, 18);
		assertEquals("Binary Search Result ", 2, resultIndex);
		resultIndex = BitonicArray.findElement(arr, 0, 6, 19);
		assertEquals("Binary Search Result ", -1, resultIndex);
	}

}
