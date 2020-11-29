package com.karthik.java;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class BitonicArray {
	private static final Logger logger = LoggerFactory.getLogger(BitonicArray.class);

	public static int findElement(int[] inputArray, int low, int high, int element) {
		int bitonicMaxIndex = ListUtils.findIndexWithMaxOnBitonicArray(inputArray, low, high);
		int bitonicMax = inputArray[bitonicMaxIndex];
		logger.info("Bitonic Max Index : " + bitonicMaxIndex + " Bitonic Max Value " + bitonicMax);
		if (element > bitonicMax) {
			return -1;
		} else if (element == bitonicMax) {
			return bitonicMaxIndex;
		} else {
			int leftTreeSearchResult = ListUtils.findElementIndexViaBinarySearch(inputArray, element, low,
					bitonicMaxIndex - 1, true);
			return leftTreeSearchResult != -1 ? leftTreeSearchResult
					: (ListUtils.findElementIndexViaBinarySearch(inputArray, element, bitonicMaxIndex + 1, high,
							false));
		}
	}

	public static void main(String[] args) {
		int[] arr = { -3, 9, 18, 20, 17, 5, 1 };
		int resultIndex = findElement(arr, 0, 6, 18);
		logger.info("Bitonic Search Result Index " + resultIndex);
	}

}
