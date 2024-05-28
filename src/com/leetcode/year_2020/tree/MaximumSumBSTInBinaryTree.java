package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

/**
 * https://leetcode.com/problems/maximum-sum-bst-in-binary-tree/description/
 * <p>
 * Looks similar to {@link LargestBSTBinaryTreeNew} problem, In this problem apart from keeping
 * MAX, MIN, SIZE we can also include the  sum
 * <p>
 * If the  Root of any subtree satisfies the BST property then sum will be
 * leftPair.sum + root.val + rightPair.sum
 * if Root finds BST not satisfied then it can simply take the largest of either leftSum or rightSUm
 */
public class MaximumSumBSTInBinaryTree {

    private static class Pair {
        int min;
        int max;
        int sum;

        public Pair(int min, int max, int sum) {
            this.min = min;
            this.max = max;
            this.sum = sum;
        }

        public Pair() {
        }
    }

    private static Integer MAX_SUM = Integer.MIN_VALUE;

    public static int maxSumBST(TreeNode root) {
        MAX_SUM = Integer.MIN_VALUE;
        Pair maxSumPair = maxSumBSTHelper(root);
        return MAX_SUM < 0 ? 0 : MAX_SUM;
    }


    private static Pair maxSumBSTHelper(TreeNode root) {
        if (root == null) {
            return new Pair(Integer.MAX_VALUE, Integer.MIN_VALUE, 0); // Empty node for easy comparison
        }
        Pair leftPair = maxSumBSTHelper(root.left);
        Pair rightPair = maxSumBSTHelper(root.right);

        // Crux
        if (leftPair.max < root.val && rightPair.min > root.val) {
            // Valid BST feature
            // But we should also check if the sum on either size is negative since that would hurt us
            MAX_SUM = Math.max(root.val + leftPair.sum + rightPair.sum, MAX_SUM);
            return new Pair(Math.min(Math.min(leftPair.min, rightPair.min), root.val), Math.max(Math.max(leftPair.max, rightPair.max), root.val), (root.val + leftPair.sum + rightPair.sum));
        } else {
            return new Pair(Integer.MIN_VALUE, Integer.MAX_VALUE, Math.max(leftPair.sum, rightPair.sum));
        }
    }

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(4);
        treeNode.left.left = new TreeNode(2);
        treeNode.left.right = new TreeNode(4);

        treeNode.right = new TreeNode(3);
        treeNode.right.left = new TreeNode(2);
        treeNode.right.right = new TreeNode(5);
        treeNode.right.right.left = new TreeNode(4);
        treeNode.right.right.right = new TreeNode(6);

        LogUtil.logIt("Maximum SUM BST in Binary tree is " + maxSumBST(treeNode));

        treeNode = new TreeNode(-4);
        treeNode.left = new TreeNode(-2);
        treeNode.right = new TreeNode(-5);

        LogUtil.logIt("Maximum SUM BST in Binary tree is " + maxSumBST(treeNode));

        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(8);
        root.left.left = new TreeNode(6);
        root.left.left.left = new TreeNode(9);
        root.left.right = new TreeNode(1);
        root.left.right.left = new TreeNode(-5);
        root.left.right.left.right = new TreeNode(-3);

        root.left.right.right = new TreeNode(4);
        root.left.right.right.right = new TreeNode(10);

//        inorder(root);

        LogUtil.logIt("Maximum SUM BST in Binary tree is " + maxSumBST(root));

        root = new TreeNode(1);
        root.right = new TreeNode(10);
        root.right.left = new TreeNode(-5);
        root.right.right = new TreeNode(20);

        LogUtil.logIt("Maximum SUM BST in Binary tree is " + maxSumBST(root));
    }

    public static void inorder(TreeNode root) {
        if (root == null) {
            return;
        }
        inorder(root.left);
        System.out.println(root.val);
        inorder(root.right);
    }
}
