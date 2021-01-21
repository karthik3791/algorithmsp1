package com.karthik.java;

import java.util.Arrays;

public class ThreeSumClosest {

	public static int threeSumClosestBruteForce(int[] nums, int target) {
		assert nums.length >= 3;
		int closestDifference = Integer.MAX_VALUE;
		int output = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				for (int k = j + 1; k < nums.length; k++) {
					System.out.println("Currently looking at : (" + nums[i] + "," + nums[j] + "," + nums[k] + ")");
					int sum = nums[i] + nums[j] + nums[k];
					int difference = Math.abs(target - sum);
					System.out.println("Difference with Target : " + difference);
					if (difference < closestDifference) {
						System.out.println("Match");
						closestDifference = difference;
						output = sum;
					}
				}
			}
		}
		return output;
	}

	// Either finds exact number or the number that matches closest to valueToFInd
	// in nums array.
	private static int closestBinarySearch(int[] sortedArray, int valueToFind, int low, int high) {
		if (low >= high) {
			return low;
		}
		int mid = (low + high) / 2;
		int midValue = sortedArray[mid];
		if (midValue == valueToFind) {
			return mid;
		} else if (midValue > valueToFind) {
			int possibleIndex = closestBinarySearch(sortedArray, valueToFind, low, mid - 1);
			if (Math.abs(valueToFind - sortedArray[possibleIndex]) < Math.abs(valueToFind - sortedArray[mid])) {
				return possibleIndex;
			} else
				return mid;
		} else {
			int possibleIndex = closestBinarySearch(sortedArray, valueToFind, mid + 1, high);
			if (Math.abs(valueToFind - sortedArray[possibleIndex]) < Math.abs(valueToFind - sortedArray[mid])) {
				return possibleIndex;
			} else
				return mid;
		}
	}

	public static int threeSumClosestWithBrains(int[] nums, int target) {
		assert nums.length >= 3;
		Arrays.sort(nums); // NLogN
		int closestDifference = Integer.MAX_VALUE;
		int output = 0;
		for (int i = 0; i < nums.length; i++) {
			for (int j = i + 1; j < nums.length; j++) {
				int twoSum = nums[i] + nums[j];
				int indexOfThirdValue = closestBinarySearch(nums, target - twoSum, j + 1, nums.length - 1);
				if (indexOfThirdValue >= nums.length) {
					continue;
				}
				int finalSum = twoSum + nums[indexOfThirdValue];
				int difference = Math.abs(target - finalSum);
				if (difference < closestDifference) {
					closestDifference = difference;
					output = finalSum;
				}
			}
		}
		return output;
	}

	public static int threeSumClosestWithBrains2(int[] nums, int target) {
		assert nums.length >= 3;
		Arrays.sort(nums); // NLogN
		int closestDifference = Integer.MAX_VALUE;
		int output = 0;
		for (int i = 0; i < nums.length - 2; i++) {
			// System.out.println("Current i : " + i + " Nums[i]:" + nums[i]);
			int targetTwoSum = target - nums[i];
			Integer[] result = findClosestTwoSumElements(nums, i + 1, nums.length - 1, targetTwoSum);
			// System.out.println("2 Sum result " + Arrays.asList(result));
			int finalSum = nums[i] + result[0] + result[1];
			// System.out.println("3 Sum " + finalSum);
			int difference = Math.abs(target - finalSum);
			if (difference < closestDifference) {
				closestDifference = difference;
				output = finalSum;
			}
		}
		return output;
	}

	public static Integer[] findClosestTwoSumElements(int[] sortedArray, int low, int high, int expectedSumValue) {
		Integer[] result = new Integer[2];
		int difference = Integer.MAX_VALUE;
		while (low < high) {
			int sum = sortedArray[low] + sortedArray[high];
			int currentDifference = Math.abs(expectedSumValue - sum);
			if (currentDifference < difference) {
				difference = currentDifference;
				result[0] = sortedArray[low];
				result[1] = sortedArray[high];
			}
			if (sum < expectedSumValue) {
				low++;
			} else if (sum > expectedSumValue) {
				high--;
			} else {
				break;
			}
		}
		return result;
	}

	public static void main(String[] args) {
		int[] nums = { -1, 2, 1, -4 };
		int target = 1;
		System.out.println(threeSumClosestWithBrains2(nums, target));

//		int[] sortedArray = { 1, 4, 7, 10 };
//		int valueToFind = 11;
//		System.out.printf("Closest Value to " + valueToFind + " is : %s",

		// int[] nums = { 1, 4, 7, 10 };
		// System.out.println("2 Sum result " +
		// Arrays.asList(findClosestTwoSumElements(nums, 0, nums.length - 1, 6)));
	}

}
