package com.karthik.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum {

	private static List<List<Integer>> findTwoSum(int[] sortedArray, int target, int low, int high) {
		List<List<Integer>> twoSumPairs = new ArrayList<>();
		while (low < high) {
			int currentSum = sortedArray[low] + sortedArray[high];
			if (currentSum == target) {
				twoSumPairs.add(Arrays.asList(sortedArray[low], sortedArray[high]));
				low++;
				high--;
			} else if (currentSum < target)
				low++;
			else
				high--;
		}
		return twoSumPairs;
	}

	public static List<List<Integer>> fourSum(int[] nums, int target) {
		Set<List<Integer>> fourSumPairs = new HashSet<>();
		Arrays.sort(nums);
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int currentSum = nums[i] + nums[j];
				List<List<Integer>> twoSumPairs = findTwoSum(nums, target - currentSum, j + 1, nums.length - 1);
				if (twoSumPairs.isEmpty() == false) {
					for (List<Integer> twoSumPair : twoSumPairs) {
						List<Integer> fourSumPair = new ArrayList<>(twoSumPair);
						fourSumPair.add(nums[i]);
						fourSumPair.add(nums[j]);
						fourSumPairs.add(fourSumPair);
					}

				}
			}
		}
		return new ArrayList<>(fourSumPairs);
	}

	public static Integer[][] constructPairs(int[] nums) {
		Integer[][] pairs = new Integer[(nums.length * (nums.length - 1)) / 2][2];
		int k = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				Integer[] pair = new Integer[2];
				pair[0] = nums[i];
				pair[1] = nums[j];
				pairs[k++] = pair;
			}
		}
		return pairs;
	}

	public static List<List<Integer>> fourSumImprovised(int[] nums, int target) {
		Set<List<Integer>> fourSumPairs = new HashSet<>();
		Integer[][] pairs = constructPairs(nums);
		System.out.println("Pairs Length : " + pairs.length);
		for (int i = 0; i < pairs.length; i++) {
			for (int j = i + 1; j < pairs.length; j++) {
				Integer[] a = pairs[i];
				Integer[] b = pairs[j];
				if (a[0] + a[1] + b[0] + b[1] == target) {
					List<Integer> fourNumInteger = new ArrayList<>();
					fourNumInteger.add(a[0]);
					fourNumInteger.add(a[1]);
					fourNumInteger.add(b[0]);
					fourNumInteger.add(b[1]);
					fourSumPairs.add(fourNumInteger);
				}
			}
		}
		return new ArrayList<>(fourSumPairs);
	}

	public static void main(String[] args) {
		int[] nums = { 1, 0, -1, 0, -2, 2 };
		int target = 0;
		System.out.println(fourSumImprovised(nums, target));

	}

}
