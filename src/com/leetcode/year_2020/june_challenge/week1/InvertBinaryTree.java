package com.leetcode.year_2020.june_challenge.week1;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

/**
 * https://leetcode.com/problems/invert-binary-tree/
 * @author neeraj on 02/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class InvertBinaryTree {

    public static void main(String[] args) {
        TreeNode treeNode = new TreeNode(1);
        treeNode.left = new TreeNode(2);
        treeNode.right = new TreeNode(3);
        treeNode.left.left = new TreeNode(4);
        treeNode.left.right = new TreeNode(5);
        treeNode.right.left = new TreeNode(6);
        treeNode.right.right = new TreeNode(7);

        LogUtil.logIt("Before Inverting Inorder Traversal is", true);
        treeNode.inorder(treeNode);

        invertTree(treeNode);
        LogUtil.logIt("\nAfter Inverting Inorder Traversal is", true);
        treeNode.inorder(treeNode);
    }

    public static TreeNode invertTree(TreeNode root) {
        if (root == null) return null;
        TreeNode left = root.left;
        root.left = invertTree(root.right);
        root.right = invertTree(left);
        return root;
    }
}
