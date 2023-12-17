package com.karthik.java;

import java.util.*;

public class GraphDeepCopy {
    class Node {
        public int val;
        public List<Node> neighbors;

        public Node() {
            val = 0;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val) {
            val = _val;
            neighbors = new ArrayList<Node>();
        }

        public Node(int _val, ArrayList<Node> _neighbors) {
            val = _val;
            neighbors = _neighbors;
        }
    }

    private Map<Node, Node> cloneMap;

    private Node deepClone(Node node) {
        if (!cloneMap.containsKey(node)) {
            Node clone = new Node(node.val, new ArrayList<>(node.neighbors));
            cloneMap.put(node, clone);
            for (int i = 0; i < clone.neighbors.size(); i++) {
                clone.neighbors.set(i, deepClone(node.neighbors.get(i)));
            }
        }
        return cloneMap.get(node);
    }

    public Node cloneGraph(Node node) {
        cloneMap = new HashMap<>();
        return deepClone(node);
    }
}
