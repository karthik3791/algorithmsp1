package com.karthik.java;

public class KthLargest {

	private int kthsmallest;
	private int currentIndex;
	private boolean found = false;

	private void inOrderTraversal(TreeNode root, int k) {
		if (root != null && found == false) {
			inOrderTraversal(root.left, k);
			if (currentIndex == k - 1) {
				kthsmallest = root.val;
				found = true;
			}
			currentIndex++;
			inOrderTraversal(root.right, k);
		}

	}

	public int kthSmallest(TreeNode root, int k) {
		currentIndex = 0;
		inOrderTraversal(root, k);
		return kthsmallest;
	}

	public static void main(String[] args) {
		// [3,1,4,null,2]

		Integer[] nodeArray = { 3, 1, 4, null, 2 };
		TreeNode root = TreeUtils.convertLevelOrderedInputToTree(nodeArray);
		System.out.println(new KthLargest().kthSmallest(root, 1));
	}

}
