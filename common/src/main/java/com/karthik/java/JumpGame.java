package com.karthik.java;

public class JumpGame {

	private int[] parent; // parent[i] = parent of i
	private int[] size; // size[i] = number of elements in subtree rooted at i
	private int count; // number of components

	public void initializeConnectedComponents(int n) {
		count = n;
		parent = new int[n];
		size = new int[n];
		for (int i = 0; i < n; i++) {
			parent[i] = i;
			size[i] = 1;
		}
	}

	// Find root of an index
	public int root(int index) {
		while (index != parent[index])
			index = parent[index];
		return index;
	}

	public void union(int p, int q) {
		int pRoot = root(p);
		int qRoot = root(q);
		if (pRoot == qRoot)
			return;
		else if (size[pRoot] >= size[qRoot]) {
			parent[qRoot] = pRoot;
			size[pRoot] += size[qRoot];
		} else {
			parent[pRoot] = qRoot;
			size[qRoot] += size[pRoot];
		}
		count--;
	}

	public boolean canJump(int[] nums) {
		int destinationIndex = nums.length - 1;
		initializeConnectedComponents(nums.length);
		for (int i = nums.length - 1; i >= 0; i--) {
			for (int j = i - 1; j >= 0; j--) {
				if (nums[j] >= i - j) {
					union(i, j);
				}
			}
		}
		return root(0) == root(destinationIndex);
	}

	public boolean canJump2(int[] nums) {
		if (nums[0] >= nums.length)
			return true;
		initializeConnectedComponents(nums.length);
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = 1; j <= nums[i]; j++) {
				if (i + j < nums.length) {
					System.out.println("Union  : " + i + " ->  " + (i + j));
					union(i, i + j);
				}
			}
		}
		return root(0) == root(nums.length - 1);
	}

	public static void main(String[] args) {
		int[] nums = { 3, 2, 1, 0, 4 };
		System.out.println("Output : " + new JumpGame().canJump2(nums));

		int[] nums2 = { 2, 3, 1, 1, 4 };
		System.out.println("Output : " + new JumpGame().canJump2(nums2));
	}

}
