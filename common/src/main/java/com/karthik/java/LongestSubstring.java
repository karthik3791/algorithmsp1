package com.karthik.java;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class LongestSubstring {

	public int lengthOfLongestSubstring(String s) {
		int maxSubStringLength = 0;
		char[] chars = s.toCharArray();
		for (int i = 0; i < chars.length; i++) {
			Set<String> subStringChars = new HashSet<>();
			subStringChars.add(String.valueOf(chars[i]));
			int k = 1;
			for (int j = i + 1; j < chars.length && subStringChars.contains(String.valueOf(chars[j])) == false; j++) {
				subStringChars.add(String.valueOf(chars[j]));
				k++;
			}
			if (k > maxSubStringLength) {
				System.out.println("Present Max String : " + String.valueOf(subStringChars));
				maxSubStringLength = k;
			}
		}
		return maxSubStringLength;
	}

	public int lengthOfLongestSubstring2(String s) {
		int maxSubStringLength = 0;
		char[] chars = s.toCharArray();
		int[] lastSeenIndex = new int[256];
		Arrays.fill(lastSeenIndex, -1);
		int startIndex = 0;
		for (int endIndex = 0; endIndex < chars.length; endIndex++) {
			startIndex = Math.max(startIndex, lastSeenIndex[chars[endIndex]] + 1);
			int count = endIndex - startIndex + 1;
			if (count > maxSubStringLength)
				maxSubStringLength = count;
			lastSeenIndex[chars[endIndex]] = endIndex;
		}
		return maxSubStringLength;

	}

	public static void main(String[] args) {
		String s1 = "abcabcbb";
		System.out.println("Length of longest substring : " + new LongestSubstring().lengthOfLongestSubstring2(s1));
		String s2 = "bbbbb";
		System.out.println("Length of longest substring : " + new LongestSubstring().lengthOfLongestSubstring2(s2));
		String s3 = "pwwkew";
		System.out.println("Length of longest substring : " + new LongestSubstring().lengthOfLongestSubstring2(s3));

	}
}
