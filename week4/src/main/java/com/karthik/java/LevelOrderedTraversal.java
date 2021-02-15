package com.karthik.java;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderedTraversal {

	private List<List<Integer>> levelOrderTraversal(Queue<TreeNode> nodeQueue, List<List<Integer>> res) {
		if (nodeQueue.isEmpty())
			return res;
		List<TreeNode> currentLevelNodes = new ArrayList<>();
		List<Integer> currentLevelVal = new ArrayList<>();
		while (!nodeQueue.isEmpty()) {
			currentLevelNodes.add(nodeQueue.remove());
		}
		for (TreeNode tNode : currentLevelNodes) {
			currentLevelVal.add(tNode.val);
			if (tNode.left != null) {
				nodeQueue.add(tNode.left);
			}
			if (tNode.right != null) {
				nodeQueue.add(tNode.right);
			}
		}
		res.add(currentLevelVal);
		return levelOrderTraversal(nodeQueue, res);
	}

	public List<List<Integer>> levelOrder(TreeNode root) {
		List<List<Integer>> res = new ArrayList<>();
		if (root == null) {
			return res;
		}
		Queue<TreeNode> nodeQueue = new LinkedList<>();
		nodeQueue.add(root);
		return levelOrderTraversal(nodeQueue, res);
	}

	public static void main(String[] args) {
		Integer[] nodeArray = { 3, 9, 20, null, null, 15, 7 };
		TreeNode root = TreeUtils.convertLevelOrderedInputToTree(nodeArray);
		TreeUtils.inOrderPrint(root);
		List<List<Integer>> res = new LevelOrderedTraversal().levelOrder(root);
		System.out.println("Result : " + res);
	}
}
