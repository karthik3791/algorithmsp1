package com.karthik.java;

public class BSTFromPreOrder {

	private TreeNode bstFromPreorder(int[] preorder, int startIndex, int endIndex) {
		if (startIndex >= 0 && startIndex < preorder.length && endIndex >= 0 && endIndex < preorder.length
				&& startIndex <= endIndex) {
			int currentValue = preorder[startIndex];
			TreeNode tNode = new TreeNode(currentValue);
			int i = startIndex + 1;
			while (i <= endIndex && preorder[i] < currentValue) {
				i++;
			}
			tNode.left = bstFromPreorder(preorder, startIndex + 1, i - 1);
			tNode.right = bstFromPreorder(preorder, i, endIndex);
			return tNode;
		} else
			return null;
	}

	public TreeNode bstFromPreorder(int[] preorder) {
		return bstFromPreorder(preorder, 0, preorder.length - 1);
	}

	public static void main(String[] args) {
		int[] preorder = { 8, 5, 1, 7, 10, 12 };
		TreeNode root = new BSTFromPreOrder().bstFromPreorder(preorder);
		System.out.println("Constructed Tree : " + new LevelOrderedTraversal().levelOrder(root));
		TreeUtils.preOrderPrint(root);

		int[] preorder2 = { 18, 12, 7, 4, 9, 15, 20 };
		root = new BSTFromPreOrder().bstFromPreorder(preorder2);
		System.out.println("Constructed Tree : " + new LevelOrderedTraversal().levelOrder(root));
		TreeUtils.preOrderPrint(root);
	}
}
