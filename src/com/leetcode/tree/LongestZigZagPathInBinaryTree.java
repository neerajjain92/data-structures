package com.leetcode.tree;

import com.leetcode.year_2020.TreeNode;

/**
 * https://leetcode.com/problems/longest-zigzag-path-in-a-binary-tree/description/
 * <p>
 * Inspiration from https://www.youtube.com/watch?v=7aqHhENUbkQ
 */
public class LongestZigZagPathInBinaryTree {
    public static int longestZigZag(TreeNode root) {
        Pair answer = longestZigZagHelper(root);
        return answer.maxLen;
    }

    private static Pair longestZigZagHelper(TreeNode root) {
        if (root == null) {
            return new Pair();
        }

        Pair leftPair = longestZigZagHelper(root.left);
        Pair rightPair = longestZigZagHelper(root.right);

        Pair myAnswer = new Pair();
        myAnswer.maxLen = Math.max(Math.max(leftPair.maxLen, rightPair.maxLen), Math.max(leftPair.backwardSlope, rightPair.forwardSlope) + 1);
        myAnswer.forwardSlope = leftPair.backwardSlope + 1;
        myAnswer.backwardSlope = rightPair.forwardSlope + 1;

        return myAnswer;
    }

    private static class Pair {

        // Why -1, for a single node, there is no edges
        int forwardSlope = -1; // Node left child [Max ZigZag including this particular node]
        int backwardSlope = -1; // Node, right child
        int maxLen = 0; // Max ZigZag length for this current Node[This could be including yourself, or anything from left or right
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.right = new TreeNode(1);
        treeNode.right.left = new TreeNode(1);
        treeNode.right.right = new TreeNode(1);
        treeNode.right.right.left = new TreeNode(1);
        treeNode.right.right.left.right = new TreeNode(1);
        treeNode.right.right.left.right.right = new TreeNode(1);
        treeNode.right.right.right = new TreeNode(1);

        System.out.println("Longest ZigZag is "+ longestZigZag(treeNode));
    }
}
