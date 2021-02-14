package com.karthik.java;

import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

	public int majorityElement(int[] nums) {
		Map<Integer, Integer> frequencyMap = new HashMap<>();
		int majorityElement = 0;
		for (int i = 0; i < nums.length; i++) {
			Integer existingCounter = frequencyMap.getOrDefault(nums[i], 0);
			frequencyMap.put(nums[i], ++existingCounter);
			if (existingCounter > nums.length / 2) {
				majorityElement = nums[i];
				break;
			}
		}
		return majorityElement;
	}

	public int majorityElementBST(int[] nums) {

		return 0;

	}

	public static void main(String[] args) {
		MajorityElement me = new MajorityElement();
		int[] a = { 2, 2, 1, 1, 1, 2, 2 };
		int[] b = { 3, 2, 3 };
		System.out.println(me.majorityElement(a));
		System.out.println(me.majorityElement(b));
	}
}
