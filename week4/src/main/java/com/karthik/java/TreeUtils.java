package com.karthik.java;

public class TreeUtils {
	private static TreeNode addTreeNodes(Integer[] nodeArray, int index) {
		if (index >= 0 && index < nodeArray.length && nodeArray[index] != null) {
			TreeNode tNode = new TreeNode(nodeArray[index]);
			tNode.left = addTreeNodes(nodeArray, 2 * index);
			tNode.right = addTreeNodes(nodeArray, 2 * index + 1);
			return tNode;
		} else
			return null;

	}

	public static void inOrderPrint(TreeNode root) {
		if (root != null) {
			inOrderPrint(root.left);
			System.out.println(root.val);
			inOrderPrint(root.right);
		}

	}

	public static TreeNode convertToTree(Integer[] inputArray) {
		Integer[] nodeArray = new Integer[inputArray.length + 1];
		for (int i = 0; i < inputArray.length; i++) {
			nodeArray[i + 1] = inputArray[i];
		}
		TreeNode root = addTreeNodes(nodeArray, 1);
		System.out.println("In order Traversal of Tree : - ");
		inOrderPrint(root);
		return root;
	}

}
