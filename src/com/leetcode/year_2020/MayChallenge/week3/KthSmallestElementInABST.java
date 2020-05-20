package com.leetcode.year_2020.MayChallenge.week3;

import com.leetcode.year_2020.TreeNode;

/**
 * https://leetcode.com/problems/kth-smallest-element-in-a-bst/
 *
 * @author neeraj on 20/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class KthSmallestElementInABST {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(1);
        treeNode.left.right = new TreeNode(2);
        treeNode.right = new TreeNode(4);

        System.out.println(kthSmallest(treeNode, 1));
        System.out.println(kthSmallest(treeNode, 4));
        System.out.println(kthSmallest(treeNode, 3));
        System.out.println(kthSmallest(treeNode, 4));
    }

    public static int kthSmallest(TreeNode root, int k) {
        // First index will store the index
        // Second item will store the actual kth Smallest item
        int[] temp = new int[2];
        inorder(root, k, temp);
        return temp[1];
    }

    static int i = 0;

    public static void inorder(TreeNode root, int k, int[] temp) {
        if (root == null) return;
        inorder(root.left, k, temp);
        if (++temp[i] == k) {
            temp[1] = root.val;
        }
        inorder(root.right, k, temp);
    }
}
