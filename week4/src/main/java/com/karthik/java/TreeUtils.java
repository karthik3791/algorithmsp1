package com.karthik.java;

import java.util.LinkedList;
import java.util.Queue;

public class TreeUtils {

	public static TreeNode convertLevelOrderedInputToTree(Integer[] nodeArray) {
		Integer rootVal = nodeArray[0];
		if (rootVal == null) {
			return null;
		}
		TreeNode root = new TreeNode(rootVal);
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		int i = 1;
		while (!q.isEmpty() && i < nodeArray.length) {
			TreeNode node = q.poll();
			if (node != null) {
				TreeNode left = null;
				if (i < nodeArray.length && nodeArray[i] != null) {
					left = new TreeNode(nodeArray[i]);
				}
				node.left = left;
				q.offer(left);
				i++;
				TreeNode right = null;
				if (i < nodeArray.length && nodeArray[i] != null) {
					right = new TreeNode(nodeArray[i]);
				}
				node.right = right;
				q.offer(right);
				i++;
			}
		}
		return root;
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

	public static void postOrderPrint(TreeNode root) {
		if (root != null) {
			postOrderPrint(root.left);
			postOrderPrint(root.right);
			System.out.println(root.val);
		}
	}

}
