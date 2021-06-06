package com.karthik.java;

public class InOrderSuccessorBST {

	public int findMinimum(TreeNode root) {
		if (root.left == null)
			return root.val;
		else
			return findMinimum(root.left);
	}

	public Integer inOrderSuccessorBST(TreeNode root, int p) {
		if (root == null)
			return null;
		else if (root.val == p) {
			if (root.right == null) {
				return null;
			} else
				return findMinimum(root.right);
		} else if (root.val < p)
			return inOrderSuccessorBST(root.right, p);
		else {
			Integer possibleSol = inOrderSuccessorBST(root.left, p);
			return (possibleSol == null) ? root.val : possibleSol;
		}

	}

	public static void main(String[] args) {
		// [3,1,4,null,2]

		Integer[] nodeArray = { 10, 5, 12, 3, 8, 11, 13, 2, 4, 6, 9 };
		TreeNode root = TreeUtils.convertLevelOrderedInputToTree(nodeArray);

		System.out.println(new InOrderSuccessorBST().inOrderSuccessorBST(root, 5) == 6);

		Integer[] nodeArray2 = { 5, 3, 6, 2, 4, null, null, 1 };
		root = TreeUtils.convertLevelOrderedInputToTree(nodeArray2);
		System.out.println(new InOrderSuccessorBST().inOrderSuccessorBST(root, 6) == null);

		Integer[] nodeArray3 = { 2, 1, 3 };
		root = TreeUtils.convertLevelOrderedInputToTree(nodeArray3);
		System.out.println(new InOrderSuccessorBST().inOrderSuccessorBST(root, 1) == 2);
	}

}
