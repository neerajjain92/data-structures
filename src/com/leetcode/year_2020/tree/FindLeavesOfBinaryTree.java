package com.leetcode.year_2020.tree;

import com.leetcode.year_2020.TreeNode;

import java.util.*;

/**
 * https://www.lintcode.com/problem/650/
 * 650 · Find Leaves of Binary Tree
 */
public class FindLeavesOfBinaryTree {

    public static void main(String[] args) {
        /**
         *     1
         *    / \
         *   2   3
         *  / \
         * 4   5
         */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        System.out.println(findLeaves(root));
        FindLeavesOfBinaryTree obj = new FindLeavesOfBinaryTree();
        System.out.println(obj.findLeavesNew(root));

        /**
         *
         *     1
         *    / \
         *   2   3
         *  /
         * 4
         */
        root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        System.out.println(findLeaves(root));
        System.out.println(obj.findLeavesNew(root));
    }

    public static List<List<Integer>> findLeaves(TreeNode root) {
        final List<List<Integer>> result = new ArrayList<>();
        final Set<TreeNode> visited = new HashSet<>();
        while (true) {
            final List<Integer> leaves = new ArrayList<>();
            findLeaves(root, leaves, visited);
            if (leaves.isEmpty()) {
                break;
            } else {
                result.add(leaves);
            }
        }
        return result;
    }

    public static boolean findLeaves(TreeNode root, final List<Integer> leaves,
                                     final Set<TreeNode> seen) {
        if (root == null || seen.contains(root)) return true;
        Boolean isLeftVisited = findLeaves(root.left, leaves, seen);
        Boolean isRightVisited = findLeaves(root.right, leaves, seen);
        if (isLeftVisited && isRightVisited) {
            leaves.add(root.val);
            seen.add(root);
        }
        return false;
    }

    public List<List<Integer>> findLeavesNew(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        distanceFromLeafNodes(root, result);
        return result;
    }

    private int distanceFromLeafNodes(TreeNode root, List<List<Integer>> result) {
        if (root == null) return 0;
        int leftDistance = distanceFromLeafNodes(root.left, result);
        int rightDistance = distanceFromLeafNodes(root.right, result);

        int distance = Math.max(leftDistance, rightDistance) + 1;
        if (result.size() < distance) {
            List<Integer> leafs = new ArrayList<>();
            result.add(leafs);
        }
        result.get(distance-1).add(root.val); // This -1 is because height we get is not 0 base dut ArrayList is 0 based
        return distance;
    }
}
