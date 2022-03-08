package com.karthik.java;

import java.util.Arrays;
import java.util.List;

public class MaxPathSum {

	private static class MaxPathSumResult {
		private int leftMaxSum;
		private int rightMaxSum;
		private int maxSum;

		public MaxPathSumResult(int leftSum, int rightSum, int maxSum) {
			leftMaxSum = leftSum;
			rightMaxSum = rightSum;
			this.maxSum = maxSum;
		}
	}

	private MaxPathSumResult recursiveMaxPathSum(TreeNode root) {
		if (root == null)
			return null;
		MaxPathSumResult leftTree = recursiveMaxPathSum(root.left);
		MaxPathSumResult rightTree = recursiveMaxPathSum(root.right);
		int leftMaxSum = (leftTree == null) ? 0 : Math.max(leftTree.leftMaxSum, leftTree.rightMaxSum);
		int rightMaxSum = (rightTree == null) ? 0 : Math.max(rightTree.leftMaxSum, rightTree.rightMaxSum);

		int greedyMax = Arrays
				.asList(leftTree == null ? Integer.MIN_VALUE : leftTree.maxSum,
						rightTree == null ? Integer.MIN_VALUE : rightTree.maxSum, leftMaxSum + root.val,
						rightMaxSum + root.val, leftMaxSum + root.val + rightMaxSum, root.val)
				.stream().max((a, b) -> Integer.compare(a, b)).get();

		return new MaxPathSumResult(Math.max(root.val, root.val + leftMaxSum),
				Math.max(root.val, root.val + rightMaxSum), greedyMax);

	}

	public int maxPathSum(TreeNode root) {
		return recursiveMaxPathSum(root).maxSum;
	}

	public static void main(String[] args) {
		Integer[] input1 = { 1, 2, 3 };
		TreeNode root = TreeUtils.convertLevelOrderedInputToTree(input1);
		System.out.println(new MaxPathSum().maxPathSum(root));

		Integer[] input2 = { -10, 9, 20, null, null, 15, 7 };
		root = TreeUtils.convertLevelOrderedInputToTree(input2);
		System.out.println(new MaxPathSum().maxPathSum(root));

		Integer[] input3 = { 2, -1, -2 };
		root = TreeUtils.convertLevelOrderedInputToTree(input3);
		System.out.println(new MaxPathSum().maxPathSum(root));

		Integer[] input4 = { 9, 6, -3, null, null, -6, 2, null, null, 2, null, -6, -6, -6 };
		root = TreeUtils.convertLevelOrderedInputToTree(input4);
		System.out.println(new MaxPathSum().maxPathSum(root));
	}

}
