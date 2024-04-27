package com.leetcode.tree;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

/**
 * https://leetcode.com/problems/recover-binary-search-tree/
 *
 * @author neeraj on 13/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RecoverBinarySearchTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(3);
        root.left.right = new TreeNode(2);

        LogUtil.logIt("\n Before Recovering BST");
        root.inorder(root);
        LogUtil.newLine();
//        recoverTreeDuringInorder(root);
        LogUtil.logIt("\n After Recovering BST");
        root.inorder(root);
        LogUtil.newLine();

//
//        recoverTree(root);
//
//        LogUtil.logIt("\n After Recovering BST");
//        root.inorder(root);

        root = new TreeNode(3);
        root.left = new TreeNode(1);
        root.right = new TreeNode(4);
        root.right.left = new TreeNode(2);
        LogUtil.logIt("Before Recovering BST");
        root.inorder(root);
        LogUtil.newLine();
//        recoverTree(root);
        recoverTreeDuringInorder(root);
        LogUtil.logIt("\n After Recovering BST");
        root.inorder(root);
    }


    public static void recoverTree(TreeNode root) {
        /*
         * we'll recover this tree with Morris traversal
         * In Morris traversal we do the printing in 2 places
         * 1 when there is nothing in the left
         *
         * 2nd when we are fixing the tree(after destroying it :p =D)
         *
         *
         * Also as we know only 2 nodes are displaced, so we'll fix it via constant space
         *
         * */
        TreeNode first = null;
        TreeNode second = null;
        TreeNode prev = null;

        while (root != null) {
            if (root.left == null) {
                // This is the business logic, where we'll set the values
                if (prev != null && prev.val > root.val) {
                    // Means, the bst property is not maintained in inorder traversal
                    if (first == null) {
                        first = prev;
                    }
                    /**
                     *  Why first = prev where as second = root;
                     *  TAKE example inorder of destroyed BST [3,2,1]
                     *  Here the very first destroyed encounter will be
                     *  3(which is prev) > 2(which is root);
                     *  Now we know 3 is not in the right position, hence firstElement is 3.
                     *  we also know that to maintain BST inorder, the only element which
                     *  can replace 3 is (something) < 2.
                     *  hence after setting first, when we see again pre > root.val
                     *  we know for sure root element is the culprit this time and not
                     *  pre, since only 2 elements are non wrong position
                     */
                    second = root;
                }
                prev = root;
                root = root.right;
            } else {
                // when root.left != null
                TreeNode leftOfRoot = root.left;
                while (leftOfRoot.right != null && leftOfRoot.right != root) {
                    leftOfRoot = leftOfRoot.right;
                }

                if (leftOfRoot.right == null) {
                    // Make that thread
                    leftOfRoot.right = root;
                    root = root.left;
                } else {
                    // There was existing thread so let's just fix it
                    // this is where we check for invalid node again
                    leftOfRoot.right = null;

                    if (prev != null && prev.val > root.val) {
                        if (first == null) {
                            first = prev;
                        }
                        second = root;
                    }
                    prev = root;
                    root = root.right;
                }
            }
        }
        if (first != null && second != null) {
            int secondVal = second.val;
            second.val = first.val;
            first.val = secondVal;
        }
    }

    private static TreeNode firstConflictingNode = null;
    private static TreeNode secondConflictingNode = null;
    private static TreeNode nodeAdjacentToFirstConflictingNode = null;
    private static TreeNode lastSeen = null;

    /**
     * 27th April 2024, Latest which should be used and solved
     * Inspiration https://www.youtube.com/watch?v=ZWGW7FminDM&t=547s
     * @param root
     */
    public static void recoverTreeDuringInorder(TreeNode root) {
        firstConflictingNode = null;
        secondConflictingNode = null;
        nodeAdjacentToFirstConflictingNode = null;
        lastSeen = null;
        inorder(root);
        if (firstConflictingNode != null) {
            System.out.println("First " + firstConflictingNode.val);
        }
        if (secondConflictingNode != null) {
            System.out.println("Second " + secondConflictingNode.val);
        }
        if (nodeAdjacentToFirstConflictingNode != null) {
            System.out.println("NodeAdjacent " + nodeAdjacentToFirstConflictingNode.val);
        }
        if (secondConflictingNode == null) {
            swap(firstConflictingNode, nodeAdjacentToFirstConflictingNode);
        } else {
            swap(firstConflictingNode, secondConflictingNode);
        }

        if (firstConflictingNode != null) {
            System.out.println("First " + firstConflictingNode.val);
        }
        if (secondConflictingNode != null) {
            System.out.println("Second " + secondConflictingNode.val);
        }
        if (nodeAdjacentToFirstConflictingNode != null) {
            System.out.println("NodeAdjacent " + nodeAdjacentToFirstConflictingNode.val);
        }

    }

    private static void swap(TreeNode first, TreeNode second) {
        int temp = first.val;
        first.val = second.val;
        second.val = temp;
    }

    private static void inorder(TreeNode root) {
        if (root == null) return;

        inorder(root.left);

        // Visit The Node
        if (lastSeen != null) {
            if (root.val < lastSeen.val) {
                if (firstConflictingNode == null) {
                    firstConflictingNode = lastSeen;
                    nodeAdjacentToFirstConflictingNode = root;
                } else {
                    secondConflictingNode = root;
                }
            }
        }
        lastSeen = root;
        inorder(root.right);
    }

}
