package com.karthik.java;

public class AddOneRow {

	private void doSomething(TreeNode root, int v, int d, int currentLevel) {
		if (root == null)
			return;
		if (currentLevel == d - 1) {
			TreeNode leftNode = new TreeNode(v, root.left, null);
			TreeNode rightNode = new TreeNode(v, null, root.right);
			root.left = leftNode;
			root.right = rightNode;
		} else if (currentLevel < d) {
			doSomething(root.left, v, d, currentLevel + 1);
			doSomething(root.right, v, d, currentLevel + 1);
		}
	}

	public TreeNode addOneRow(TreeNode root, int v, int d) {
		if (d == 1) {
			TreeNode node = new TreeNode(v);
			node.left = root;
			root = node;
		} else {
			doSomething(root, v, d, 1);
		}
		return root;
	}
}
