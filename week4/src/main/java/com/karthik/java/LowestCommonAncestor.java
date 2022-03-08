package com.karthik.java;


class LowestCommonAncestor {

    private TreeNode ans;

    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        doesTreeContainAtleastOne(root, p, q);
        return ans;
    }

    private boolean doesTreeContainAtleastOne(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || ans != null)
            return false;

        int matchCount = 0;
        if (root.val == p.val || root.val == q.val)
            matchCount++;

        matchCount += doesTreeContainAtleastOne(root.left, p, q) ? 1 : 0;
        if (matchCount == 2)
            ans = root;
        else {
            matchCount += doesTreeContainAtleastOne(root.right, p, q) ? 1 : 0;
            if (matchCount == 2)
                ans = root;
        }
        return matchCount > 0;
    }

/*
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root.val == p.val || root.val == q.val) return root;
        TreeNode left = lowestCommonAncestor(root.left, p, q);
        TreeNode right = lowestCommonAncestor(root.right, p, q);
        return (left != null && right != null) ? root : (left != null) ? left : right;
    }
 */

    public static void main(String[] args) {
        Integer[] tree = {3, 5, 1, 6, 2, 0, 8, null, null, 7, 4};
        TreeNode root = TreeUtils.convertLevelOrderedInputToTree(tree);
        new LowestCommonAncestor().lowestCommonAncestor(root, new TreeNode(5), new TreeNode(1));

    }
}