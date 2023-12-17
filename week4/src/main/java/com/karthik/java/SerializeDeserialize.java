package com.karthik.java;

import java.util.*;

public class SerializeDeserialize {

    private class Node {
        public int val;
        public List<Node> children;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, List<Node> _children) {
            val = _val;
            children = _children;
        }
    }

    ;

    // Encodes a tree to a single string.
    public String serialize(TreeNode root) {
        if (root == null) return "null";
        Queue<TreeNode> bfsQ = new LinkedList<>();
        bfsQ.offer(root);
        List<String> sb = new ArrayList();

        while (!bfsQ.isEmpty()) {
            TreeNode cur = bfsQ.poll();
            if (cur != null) {
                sb.add(Integer.toString(cur.val));
                bfsQ.offer(cur.left);
                bfsQ.offer(cur.right);
            } else
                sb.add("null");
        }
        int lastNumeric = sb.size() - 1;
        while (lastNumeric >= 0 && sb.get(lastNumeric).equals("null"))
            lastNumeric--;
        return String.join(" ", new ArrayList<>(sb.subList(0, lastNumeric + 1)));
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialize(String data) {
        List<String> strList = Arrays.asList(data.split(" "));
        if (strList.size() == 1 && strList.get(0) == "null")
            return null;
        Queue<TreeNode> bfsQ = new LinkedList<>();
        TreeNode root = new TreeNode(Integer.parseInt(strList.get(0)));
        bfsQ.offer(root);
        int curIndex = 1;
        while (!bfsQ.isEmpty()) {
            TreeNode cur = bfsQ.poll();
            cur.left = (curIndex >= strList.size() || Objects.equals(strList.get(curIndex), "null")) ? null : new TreeNode(Integer.parseInt(strList.get(curIndex)));
            curIndex++;
            cur.right = (curIndex >= strList.size() || Objects.equals(strList.get(curIndex), "null")) ? null : new TreeNode(Integer.parseInt(strList.get(curIndex)));
            curIndex++;
            if (cur.left != null)
                bfsQ.offer(cur.left);
            if (cur.right != null)
                bfsQ.offer(cur.right);
        }
        return root;
    }

    public Node deserializeNary(String data) {
        if (data == "null") return null;
        String[] strList = data.split(" ");
        String[] rootVal = strList[0].split("@");
        Node root = new Node(Integer.parseInt(rootVal[0]));
        Queue<Map.Entry<Node, Integer>> bfsQ = new LinkedList<>();
        bfsQ.offer(new java.util.AbstractMap.SimpleEntry<>(root, Integer.parseInt(rootVal[1])));
        int curIndex = 1;
        while (!bfsQ.isEmpty()) {
            Map.Entry<Node, Integer> cur = bfsQ.poll();
            int childCount = cur.getValue();
            if (childCount == 0) continue;
            cur.getKey().children = new ArrayList<>();
            while (childCount > 0 && curIndex < strList.length) {
                String[] childNodeList = strList[curIndex].split("@");
                Node childNode = new Node(Integer.parseInt(childNodeList[0]));
                cur.getKey().children.add(childNode);
                bfsQ.offer(new java.util.AbstractMap.SimpleEntry<Node, Integer>(childNode, Integer.parseInt(childNodeList[1])));
                childCount--;
                curIndex++;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        Integer[] input1 = {1, 2, 3, null, null, 4, 5};
        TreeNode root = TreeUtils.convertLevelOrderedInputToTree(input1);
        System.out.println(new SerializeDeserialize().serialize(root));
        TreeNode root2 = new SerializeDeserialize().deserialize(new SerializeDeserialize().serialize(root));
        System.out.println(new SerializeDeserialize().deserializeNary("1@4 2@0 3@2 4@1 5@2 6@0 7@1 8@1 9@1 10@0 11@1 12@0 13@0 14@0"));
    }

}
