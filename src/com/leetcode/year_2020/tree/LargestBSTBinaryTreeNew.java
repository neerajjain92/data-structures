package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

/**
 * https://www.youtube.com/watch?v=X0oXMdtUDwo
 */
public class LargestBSTBinaryTreeNew {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(50);
        root.left = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(20);

        root.right = new TreeNode(60);
        root.right.left = new TreeNode(45);
        root.right.right = new TreeNode(70);
        root.right.right.left = new TreeNode(65);
        root.right.right.right = new TreeNode(80);

        System.out.println(findMaxBSTInBinaryTree(root));
    }

    private static class Pair {
        int maxValue;
        int minValue;
        int size;

        public Pair(int maxValue, int minValue, int size) {
            this.maxValue = maxValue;
            this.minValue = minValue;
            this.size = size;
        }

        public Pair() {
        }
    }

    private static int findMaxBSTInBinaryTree(TreeNode root) {
        Pair largestSize = maxBST(root);
        return largestSize.size;
    }

    private static Pair maxBST(TreeNode root) {
        if (root == null) return new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE, 0);

        // Do Post order
        Pair largestPairInLeft = maxBST(root.left);
        Pair largestPairInRight = maxBST(root.right);

        Pair curr = new Pair();

        if (largestPairInLeft.maxValue < root.val && largestPairInRight.minValue > root.val) {
            // Valid BST order
            curr.maxValue = Math.max(Math.max(largestPairInLeft.maxValue, largestPairInRight.maxValue), root.val);
            curr.minValue = Math.min(Math.min(largestPairInLeft.minValue, largestPairInRight.minValue), root.val);
            curr.size = largestPairInLeft.size + largestPairInRight.size + 1;
        } else {
            // BST not possible
            curr.maxValue = Integer.MAX_VALUE;
            curr.minValue = Integer.MIN_VALUE;
            curr.size = Math.max(largestPairInLeft.size, largestPairInRight.size); // Not including itself
        }
        return curr;
    }
}
