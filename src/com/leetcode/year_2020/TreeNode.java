package com.leetcode.year_2020;

/**
 * @author neeraj on 25/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class TreeNode {

    int val;
    TreeNode left;
    TreeNode right;
    TreeNode next;

    TreeNode(int x) {
        val = x;
    }

    public static void inorder(TreeNode root) {
        if(root == null) return;;
        inorder(root.left);
        System.out.print(root.val + "\t");
        inorder(root.right);
    }
}
