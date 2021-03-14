package com.karthik.java;

public class BinaryTreeFromOutput {

	private TreeNode buildFromPreAndInOrder(int[] preOrder, int[] inOrder, int preStart, int preEnd, int inStart,
			int inEnd) {
		int preorderLength = (preEnd - preStart) + 1;
		int inorderLength = (inEnd - inStart) + 1;
		if (preorderLength > 0 && inorderLength > 0) {
			TreeNode root = new TreeNode(preOrder[preStart]);
			int leftPreStart = preStart + 1;
			int i = inStart;
			while (i <= inEnd && inOrder[i] != preOrder[preStart])
				i++;
			int leftInEnd = i - 1;
			int leftPreEnd = preStart + (i - inStart);
			root.left = buildFromPreAndInOrder(preOrder, inOrder, leftPreStart, leftPreEnd, inStart, leftInEnd);
			root.right = buildFromPreAndInOrder(preOrder, inOrder, leftPreEnd + 1, preEnd, i + 1, inEnd);
			return root;
		} else
			return null;
	}

	public TreeNode buildFromPreAndInOrder(int[] preorder, int[] inorder) {
		return buildFromPreAndInOrder(preorder, inorder, 0, preorder.length - 1, 0, inorder.length - 1);
	}

	private TreeNode buildFromInAndPostOrder(int[] postOrder, int[] inOrder, int postStart, int postEnd, int inStart,
			int inEnd) {
		int postOrderLength = (postEnd - postStart) + 1;
		int inorderLength = (inEnd - inStart) + 1;
		if (postOrderLength > 0 && inorderLength > 0) {
			TreeNode root = new TreeNode(postOrder[postEnd]);
			int i = inStart;
			while (i <= inEnd && inOrder[i] != postOrder[postEnd])
				i++;
			int leftInEnd = i - 1;
			int leftPostEnd = postStart + (i - inStart) - 1;
			root.left = buildFromInAndPostOrder(postOrder, inOrder, postStart, leftPostEnd, inStart, leftInEnd);
			root.right = buildFromInAndPostOrder(postOrder, inOrder, leftPostEnd + 1, postEnd - 1, i + 1, inEnd);
			return root;
		} else
			return null;
	}

	public TreeNode buildFromInAndPostOrder(int[] postorder, int[] inorder) {
		return buildFromInAndPostOrder(postorder, inorder, 0, postorder.length - 1, 0, inorder.length - 1);
	}

	private TreeNode buildFromPreAndPostOrder(int[] preOrder, int[] postOrder, int preStart, int preEnd, int postStart,
			int postEnd) {
		int postOrderLength = (postEnd - postStart) + 1;
		int preOrderLength = (preEnd - preStart) + 1;
		if (postOrderLength > 0 && preOrderLength > 0) {
			TreeNode root = new TreeNode(preOrder[preStart]);
			int leftPreStart = preStart + 1;
			int i = postStart;
			return null;
		} else
			return null;
	}

	public TreeNode buildFromPreAndPostOrder(int[] preorder, int[] postorder) {
		return buildFromInAndPostOrder(preorder, postorder, 0, preorder.length - 1, 0, postorder.length - 1);
	}

	public static void main(String[] args) {

		int[] preorder = { 3, 9, 20, 15, 7 }, inorder = { 9, 3, 15, 20, 7 }, postorder = { 9, 15, 7, 20, 3 };
		TreeNode root = new BinaryTreeFromOutput().buildFromPreAndInOrder(preorder, inorder);
		TreeUtils.preOrderPrint(root);
		TreeUtils.inOrderPrint(root);
		System.out.println("=======");
		root = new BinaryTreeFromOutput().buildFromInAndPostOrder(postorder, inorder);
		TreeUtils.postOrderPrint(root);
		TreeUtils.inOrderPrint(root);
	}
}
