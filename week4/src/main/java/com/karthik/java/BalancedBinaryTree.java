package com.karthik.java;

public class BalancedBinaryTree {

	private int heightOfTree(TreeNode root) {
		if (root == null || (root.right == null && root.left == null))
			return 0;
		else
			return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
	}

	public boolean isBalanced(TreeNode root) {
		if (root == null)
			return true;
		int lheight = heightOfTree(root.left);
		int rheight = heightOfTree(root.right);
		return Math.abs(lheight - rheight) <= 1 && isBalanced(root.left) && isBalanced(root.right);
	}
}
