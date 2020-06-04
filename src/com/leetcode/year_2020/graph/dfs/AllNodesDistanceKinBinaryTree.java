package com.leetcode.year_2020.graph.dfs;

import com.leetcode.year_2020.TreeNode;
import com.util.LogUtil;

import java.util.*;

/**
 * @author neeraj on 04/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class AllNodesDistanceKinBinaryTree {

    public static void main(String[] args) {
        TreeNode root = new TreeNode(3);

        root.left = new TreeNode(5);
        root.left.left = new TreeNode(6);
        root.left.right = new TreeNode(2);
        root.left.right.left = new TreeNode(7);
        root.left.right.right = new TreeNode(4);

        root.right = new TreeNode(1);
        root.right.left = new TreeNode(0);
        root.right.right = new TreeNode(8);

        LogUtil.logIt("Nodes at kth distance " + distanceK(root, root.left, 2), true);
    }

    static class Pair {
        int level;
        TreeNode node;

        public Pair(int level, TreeNode node) {
            this.level = level;
            this.node = node;
        }
    }

    public static List<Integer> distanceK(TreeNode root, TreeNode target, int K) {
        /**
         * We can think of this problem in terms of graph since all trees are DAG.
         * So now we need a adjacency list which we can prepare by doing inorder traversal from
         * Root node. Once we have that we can quickly do a BFS from target node
         * and increment the kth value from 0 upto k once we reach k we can store those values
         * as our result.
         */
        Map<TreeNode, List<TreeNode>> adjacencyList = new HashMap<>();
        doInorderAndPrepareAdjacencyList(root, adjacencyList);

        // Now let's do the BFS from target Node
        Queue<Pair> queue = new LinkedList<>();
        queue.add(new Pair(0, target));
        Set<TreeNode> seen = new HashSet<>();
        seen.add(target);
        List<Integer> nodesAtKDistance = new ArrayList<>();

        while (!queue.isEmpty()) {
            Pair polledPair = queue.poll();

            // Now adjacent
            for (TreeNode adjacentToPolledNode : adjacencyList.get(polledPair.node)) {
                if (!seen.contains(adjacentToPolledNode)) {
                    seen.add(adjacentToPolledNode);
                    queue.add(new Pair(polledPair.level + 1, adjacentToPolledNode));
                }
            }

            if (polledPair.level == K) {
                nodesAtKDistance.add(polledPair.node.val);
            }
        }
        return nodesAtKDistance;
    }

    private static void doInorderAndPrepareAdjacencyList(TreeNode root, Map<TreeNode, List<TreeNode>> adjacencyList) {
        if (root == null) return;
        adjacencyList.putIfAbsent(root, new ArrayList<>());

        if (root.left != null) { // Adding undirected edge both ways.
            adjacencyList.get(root).add(root.left);

            adjacencyList.putIfAbsent(root.left, new ArrayList<>());
            adjacencyList.get(root.left).add(root);
        }

        if (root.right != null) { // Adding undirected edge both ways.
            adjacencyList.get(root).add(root.right);

            adjacencyList.putIfAbsent(root.right, new ArrayList<>());
            adjacencyList.get(root.right).add(root);
        }
        doInorderAndPrepareAdjacencyList(root.left, adjacencyList);
        doInorderAndPrepareAdjacencyList(root.right, adjacencyList);
    }
}
