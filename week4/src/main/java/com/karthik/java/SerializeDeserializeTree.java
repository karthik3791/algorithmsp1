package com.karthik.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

public class SerializeDeserializeTree {

	// Encodes a tree to a single string.
	public String serialize(TreeNode root) {
		List<String> levelOrderedValues = new ArrayList<>();
		Queue<TreeNode> q = new LinkedList<>();
		q.add(root);
		while (!q.isEmpty()) {
			TreeNode currNode = q.remove();
			if (currNode != null) {
				levelOrderedValues.add(new Integer(currNode.val).toString());
				q.add(currNode.left);
				q.add(currNode.right);
			} else
				levelOrderedValues.add(null);
		}
		return String.join(",", levelOrderedValues);
	}

	// Decodes your encoded data to tree.
	public TreeNode deserialize(String data) {
		String[] v = data.split(",");
		System.out.println("String Array v : " + Arrays.asList(v));
		Integer[] levelOrderedArray = Arrays.asList(v).stream().map(str -> {
			if (str.trim().equals("null"))
				return null;
			else
				return Integer.parseInt(str.trim());
		}).collect(Collectors.toList()).toArray(new Integer[0]);
		return convertLevelOrderedInputToTree(levelOrderedArray);
	}

	private TreeNode convertLevelOrderedInputToTree(Integer[] nodeArray) {
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
				if (nodeArray[i] != null) {
					left = new TreeNode(nodeArray[i]);
				}
				node.left = left;
				q.offer(left);
				i++;
				TreeNode right = null;
				if (nodeArray[i] != null) {
					right = new TreeNode(nodeArray[i]);
				}
				node.right = right;
				q.offer(right);
				i++;
			}
		}
		return root;
	}

	public static void main(String[] args) {
		Integer[] nodeArray = { 1, 2, 3, null, null, 4, 5, 6, 7 };
		TreeNode root = new SerializeDeserializeTree().convertLevelOrderedInputToTree(nodeArray);
		String serializedString = new SerializeDeserializeTree().serialize(root);
		System.out.println(serializedString);
		// System.out.println(new
		// SerializeDeserializeTree().deserialize(serializedString));
	}
}
