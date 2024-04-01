package com.karthik.java.graph;

public class ValidateBinaryTreeNode {
    class UnionFind {
        int N;
        int[] parent;
        int count;

        UnionFind(int n) {
            this.N = n;
            parent = new int[n];
            for (int i = 0; i < n; i++)
                parent[i] = i;
            count = n;
        }

        public int find(int u) {
            if (!(parent[u] == u)) {
                parent[u] = find(parent[u]);
            }
            return parent[u];
        }

        /*
         No Union By Rank as a child node with a pre-existing parent will fail condition for a valid tree.
         This is because in a tree, each node will have AT-MOST one parent.
         */
        public boolean union(int par, int child) {
            int ppar = find(par);
            int pchild = find(child);
            if (pchild != child || ppar == pchild)
                return false;
            parent[pchild] = ppar;
            count--;
            return true;
        }

        public int getCount() {
            return count;
        }
    }

    /*
     The key point to remember is what is a tree.
     A tree is : -
     1. A connected graph - No. of connected components = 1
     2. There are no cycles in the graph.
     3. Each node has AT-MOST 1 parent.
     For an undirected graph, 2 and 3 can be checked simply by checking if the node is already visited during union.
     However, for a di-graph, points 2 and 3 are different.
     Remember that a cycle in a di-graph takes into account the direction. It is perfectly possible for having no cycles in di-graph YET the graph is not a valid tree because 2 nodes link IN to the node.
     Which is what we test in this particular problem
     */

    public boolean validateBinaryTreeNodes(int n, int[] leftChild, int[] rightChild) {
        UnionFind uf = new UnionFind(n);
        for (int i = 0; i < n; i++) {
            if (leftChild[i] != -1 && !uf.union(i, leftChild[i]))
                return false;
            if (rightChild[i] != -1 && !uf.union(i, rightChild[i]))
                return false;
        }
        return uf.count == 1;
    }

    public static void main(String[] args) {
        int n = 4;
        int[] leftChild = {1, -1, 3, -1};
        int[] rightChild = {2, -1, -1, -1};
        ValidateBinaryTreeNode v = new ValidateBinaryTreeNode();
        v.validateBinaryTreeNodes(n, leftChild, rightChild);
    }
}
