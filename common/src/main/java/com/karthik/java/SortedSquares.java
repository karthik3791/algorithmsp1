package com.karthik.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class SortedSquares {

	private int[] merge(int[] a, int[] b) {
		int[] mergedArray = new int[a.length + b.length];
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < a.length && j < b.length) {
			if (a[i] < b[j]) {
				mergedArray[k++] = a[i++];
			} else {
				mergedArray[k++] = b[j++];
			}
		}
		while (i < a.length)
			mergedArray[k++] = a[i++];
		while (j < b.length)
			mergedArray[k++] = b[j++];
		return mergedArray;
	}

	public int[] sortedSquares(int[] nums) {
		int[] result = new int[nums.length];
		List<Integer> positiveSquares = new ArrayList<>();
		List<Integer> negativeSquares = new ArrayList<>();
		for (int i = 0; i < nums.length; i++) {
			if (nums[i] < 0)
				negativeSquares.add(nums[i] * nums[i]);
			else
				positiveSquares.add(nums[i] * nums[i]);
		}
		Collections.reverse(negativeSquares);

		return merge(positiveSquares.stream().mapToInt(i -> i).toArray(),
				negativeSquares.stream().mapToInt(i -> i).toArray());
	}

	public static void main(String[] args) {
		int[] a = { 10, 11, 12, 13, 14 };
		int[] b = { 1, 2, 4, 6, 7 };
		System.out.println("Merge Result : " + Arrays.toString(new SortedSquares().merge(a, b)));

	}
}
