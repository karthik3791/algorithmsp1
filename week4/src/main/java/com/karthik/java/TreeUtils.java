package com.karthik.java;

public class TreeUtils {

	private static TreeNode convertedLevelOrderedInputToTree(Integer[] nodeArray, int index) {
		if (index >= 0 && index < nodeArray.length && nodeArray[index] != null) {
			TreeNode tNode = new TreeNode(nodeArray[index]);
			tNode.left = convertedLevelOrderedInputToTree(nodeArray, 2 * index + 1);
			tNode.right = convertedLevelOrderedInputToTree(nodeArray, 2 * index + 2);
			return tNode;
		} else
			return null;

	}

	public static TreeNode convertLevelOrderedInputToTree(Integer[] inputArray) {
		return convertedLevelOrderedInputToTree(inputArray, 0);
	}

	public static void inOrderPrint(TreeNode root) {
		if (root != null) {
			inOrderPrint(root.left);
			System.out.println(root.val);
			inOrderPrint(root.right);
		}
	}

	public static void preOrderPrint(TreeNode root) {
		if (root != null) {
			System.out.println(root.val);
			preOrderPrint(root.left);
			preOrderPrint(root.right);
		}
	}

}
