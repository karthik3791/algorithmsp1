package com.karthik.java.graph;

import java.util.LinkedList;
import java.util.Queue;

public class PopulatingNextRightPointer {

    // Definition for a Node.
    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {
        }

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    }

    public Node connect(Node root) {
        if (root != null) {
            Queue<Node> q = new LinkedList<>();
            q.add(root);
            while (!q.isEmpty()) {
                int levelSize = q.size();
                Node dummyNode = new Node();
                Node head = dummyNode;
                for (int i = 0; i < levelSize; i++) {
                    Node v = q.poll();
                    if (v.left != null)
                        q.offer(v.left);
                    if (v.right != null)
                        q.offer(v.right);
                    head.next = v;
                    head = v;
                }
            }
        }
        return root;
    }
}
