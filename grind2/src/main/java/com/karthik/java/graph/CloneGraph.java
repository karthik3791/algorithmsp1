package com.karthik.java.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CloneGraph {
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

    private Node dfs(Node node, Map<Integer, Node> visited) {
        if (visited.containsKey(node.val))
            return visited.get(node.val);
        Node clonedNode = new Node(node.val, new ArrayList<>());
        visited.put(node.val, clonedNode);
        /*
          We can go and call dfs on every node because if that node is visited before, we will instantly return the cloned node.
          We would not further again process a visited node which prevents infinite loops
         */
        for (int i = 0; i < node.neighbors.size(); i++)
            clonedNode.neighbors.add(dfs(node.neighbors.get(i), visited));
        return clonedNode;
    }

    public Node cloneGraph(Node node) {
        Map<Integer, Node> visited = new HashMap<>();
        return node != null ? dfs(node, visited) : node;
    }

}
