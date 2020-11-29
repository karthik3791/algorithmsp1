package com.karthik.java;

import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ListUtils {

	private static final Logger logger = LoggerFactory.getLogger(ListUtils.class);

	private static int[] getArraySubstring(int[] sortedInput, int low, int high) {
		int[] subArray = new int[high - low + 1];
		for (int i = low; i <= high; i++) {
			subArray[i - low] = sortedInput[i];
		}
		return subArray;
	}

	public static int findElementIndexViaBinarySearch(int[] sortedInput, int element, int low, int high,
			boolean ascendingFlag) {
		logger.info("binarySearch stack frame - Array : " + Arrays.toString(getArraySubstring(sortedInput, low, high))
				+ " low : " + low + " high: " + high);
		if (low == high) {
			if (sortedInput[low] == element)
				return low;
			else
				return -1;
		} else {
			int mid = (low + high) / 2;
			if (element == sortedInput[mid]) {
				return mid;
			} else {
				boolean rightTreeSelect = (ascendingFlag == true) ? element > sortedInput[mid]
						: element < sortedInput[mid];
				if (rightTreeSelect) {
					return findElementIndexViaBinarySearch(sortedInput, element, mid + 1, high, ascendingFlag);
				} else {
					return findElementIndexViaBinarySearch(sortedInput, element, low, mid - 1, ascendingFlag);
				}
			}
		}
	}

	public static int findIndexWithMaxOnBitonicArray(int[] bitonicArray, int low, int high) {
		logger.info("findMaxOfBitonicArray stack frame - Array : "
				+ Arrays.toString(getArraySubstring(bitonicArray, low, high)) + " low : " + low + " high: " + high);
		if (low == high) {
			return low;
		} else {
			int mid = (low + high) / 2;
			if (bitonicArray[mid] > bitonicArray[mid - 1]) {
				return findIndexWithMaxOnBitonicArray(bitonicArray, mid, high);
			} else {
				return findIndexWithMaxOnBitonicArray(bitonicArray, low, mid - 1);
			}
		}
	}

	public static void main(String[] args) {

	}

}
