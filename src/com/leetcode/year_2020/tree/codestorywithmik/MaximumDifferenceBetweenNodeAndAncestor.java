package com.leetcode.year_2020.tree.codestorywithmik;

import com.leetcode.year_2020.TreeNode;

/**
 * https://leetcode.com/problems/maximum-difference-between-node-and-ancestor/description/
 */
public class MaximumDifferenceBetweenNodeAndAncestor {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(8);
        root.left = new TreeNode(3);
        root.left.left = new TreeNode(1);
        MaximumDifferenceBetweenNodeAndAncestor solution = new MaximumDifferenceBetweenNodeAndAncestor();
        System.out.println(solution.maxAncestorDiff(root));

        root = new TreeNode(1);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(8);
        System.out.println(solution.maxAncestorDiff(root));
    }

    public int maxAncestorDiff(TreeNode root) {
        int[] result = new int[1];
        int maxTillNow = root.val;
        int minTillNow = root.val;
        maxAncestorDiff(root, maxTillNow, minTillNow, result);
        return result[0];
    }

    /*
     * If we just keep maxTillNow and ignore minTillNow what we loose on is this
     *
     *      8
     *     /
     *    3
     *   /
     *  1
     *
     * So this is covered since, maxTillNow when we reach to 1 is (8) and Math.abs(8-1)=7
     *
     * but you will loose on
     *
     *  1
     *   \
     *    3
     *     \
     *      8
     *
     * Imagine what will you loose on this when you reach 8, your maxTillNow is 8 and Math.abs(8-8) = 0
     */
    private void maxAncestorDiff(TreeNode root, int maxTillNow, int minTillNow, int[] result) {
        if (root == null)
            return;
        int diff_1 = Math.abs(root.val - maxTillNow);
        int diff_2 = Math.abs(root.val - minTillNow);
        result[0] = Math.max(result[0], Math.max(diff_1, diff_2));
        maxTillNow = Math.max(root.val, maxTillNow);
        minTillNow = Math.min(root.val, minTillNow);
        maxAncestorDiff(root.left, maxTillNow, minTillNow, result);
        maxAncestorDiff(root.right, maxTillNow, minTillNow, result);
    }


}
