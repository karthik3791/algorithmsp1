package com.karthik.java;

public class DiameterOfBTree {

	private int heightOfTree(TreeNode root) {
		if (root == null || (root.right == null && root.left == null))
			return 0;
		else
			return 1 + Math.max(heightOfTree(root.left), heightOfTree(root.right));
	}

	private int currentLevelDiameter(TreeNode root) {
		if (root == null)
			return 0;
		int leftHeight = heightOfTree(root.left);
		int rightHeight = heightOfTree(root.right);
		return (root.left == null ? leftHeight : 1 + leftHeight) + (root.right == null ? rightHeight : 1 + rightHeight);
	}

	public int diameterOfBinaryTree(TreeNode root) {
		if (root == null || (root.right == null && root.left == null))
			return 0;
		return Math.max(currentLevelDiameter(root),
				Math.max(diameterOfBinaryTree(root.left), diameterOfBinaryTree(root.right)));
	}
}
