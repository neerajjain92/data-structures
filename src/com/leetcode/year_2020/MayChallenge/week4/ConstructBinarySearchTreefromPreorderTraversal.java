package com.leetcode.year_2020.MayChallenge.week4;

import com.leetcode.year_2020.TreeNode;

import java.util.Arrays;

/**
 * @author neeraj on 24/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ConstructBinarySearchTreefromPreorderTraversal {

    public static void main(String[] args) {
        TreeNode root = bstFromPreorder(new int[]{8, 5, 1, 7, 10, 12});
        root.inorder(root);
    }

    public static TreeNode bstFromPreorder(int[] preorder) {
        if (preorder.length == 0) return null;

        // We know root is the first item in preorder traversal
        TreeNode root = new TreeNode(preorder[0]);

        int i = 1;
        while (i < preorder.length && preorder[i] < root.val) i++;

        root.left = bstFromPreorder(Arrays.copyOfRange(preorder, 1, i));
        root.right = bstFromPreorder(Arrays.copyOfRange(preorder, i, preorder.length));
        return root;
    }
}
