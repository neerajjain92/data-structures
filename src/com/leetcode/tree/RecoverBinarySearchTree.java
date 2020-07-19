package com.leetcode.tree;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

/**
 * @author neeraj on 13/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        root.inorder(root);

        recoverTree(root);

        LogUtil.logIt("\n After Recovering BST");
        root.inorder(root);
    }

    static TreeNode firstElement = null; // The Element which is not in the correct order
    static TreeNode secondElement = null; // This is the pair for firstElement since these 2 are swapped incorrectly
    static TreeNode previousElement = null;

    public static void recoverTree(TreeNode root) {
        firstElement = null;
        secondElement = null;
        previousElement = new TreeNode(Integer.MIN_VALUE); // This is set to minimum so that first node doesn't looks like out of order, since you cannot compare just 1 node we have to start with the second node only

        // Do Inorder traversal and set these items.
        inorder(root);

        int temp = firstElement.val;
        firstElement.val = secondElement.val;
        secondElement.val = temp;
    }

    public static void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        // Here we will do our business
        // i.e to find out first and second elements which were not in correct order

        if (firstElement == null && previousElement.val > root.val) {
            firstElement = previousElement;
        }

        // Immediately after setting the first element we will assign root as the
        // second element since root is also not in the correct order
        if (firstElement != null && previousElement.val > root.val) {
            secondElement = root;
        }


        // Now Since we are moving forward root will become the previous Element
        previousElement = root;


        inorder(root.right);

    }
}
