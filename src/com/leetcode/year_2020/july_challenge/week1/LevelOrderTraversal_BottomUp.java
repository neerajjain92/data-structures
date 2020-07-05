package com.leetcode.year_2020.july_challenge.week1;

import com.leetcode.year_2020.SerializeAndDeserializeBinaryTree;
import com.leetcode.year_2020.TreeNode;

import java.util.*;

/**
 * @author neeraj on 02/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class LevelOrderTraversal_BottomUp {

    public static void main(String[] args) {
        TreeNode root = SerializeAndDeserializeBinaryTree.deserialize("3,9,X,X,20,15,X,X,7,X,X");
        System.out.println(levelOrderBottom(root));
    }

    public static List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        queue.add(null);
        List<List<Integer>> result = new ArrayList<>();
        List<Integer> level = new ArrayList<>();

        while (!queue.isEmpty()) {
            TreeNode polled = queue.poll();

            if (polled == null) {
                if (!queue.isEmpty()) {
                    queue.add(null);
                }
                result.add(0, level);
                level = new ArrayList<>();
                continue;
            }
            level.add(polled.val);
            if (polled.left != null) {
                queue.add(polled.left);
            }
            if (polled.right != null) {
                queue.add(polled.right);
            }
        }
        return result;
    }
}
