package com.leetcode.year_2020.tree.codestorywithmik;

import com.leetcode.year_2020.TreeNode;

import java.util.*;

public class AmountOfTimeForBinaryTreeToBeInfected {


    /*
     * This is a really smart intuition,
     * kudos to https://www.youtube.com/watch?v=Xm8jIjAK_Zs&list=PLpIkg8OmuX-K23LhcamOcDlTBisiNJy5E&index=36&ab_channel=codestorywithMIK
     */
    int distanceFromFarthestLeaf = 0;

    public static void main(String[] args) {
        AmountOfTimeForBinaryTreeToBeInfected obj = new AmountOfTimeForBinaryTreeToBeInfected();
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(5);
        root.right = new TreeNode(3);

        root.left.right = new TreeNode(4);
        root.left.right.left = new TreeNode(9);
        root.left.right.right = new TreeNode(2);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(6);

        System.out.println(obj.amountOfTime(root, 3));
    }

    public int amountOfTime(TreeNode root, int start) {
        distanceFromFarthestLeaf = 0;
        height(root, start);
        return distanceFromFarthestLeaf;
    }

    private int height(TreeNode root, int start) {
        if (root == null) return 0;
        int leftHeight = height(root.left, start);
        int rightHeight = height(root.right, start);

        if (leftHeight > 0 && rightHeight > 0 && start != root.val) {
            return 1 + Math.max(leftHeight, rightHeight);
        }

        if (start == root.val) {
            distanceFromFarthestLeaf = Math.max(distanceFromFarthestLeaf, Math.max(leftHeight, rightHeight));
            return -1;
        }

        if (leftHeight < 0 || rightHeight < 0) {
            distanceFromFarthestLeaf = Math.max(distanceFromFarthestLeaf, Math.abs(leftHeight) + Math.abs(rightHeight));
        }
        // Because at this time we got to know that recursion is being passed from start
        // so we should just inform root, how far are we from it.
        return Math.min(leftHeight, rightHeight) - 1;
    }

    /**
     * This is 2 pass algorithm
     */
    public int amountOfTimeTwoPass(TreeNode root, int start) {
        // We all know BFS are best suited for finding nearest, minimum Number of minutes needed etc.
        Map<Integer, TreeNode> nodeParentMap = new HashMap<>();
        TreeNode[] startRef = new TreeNode[1];
        populateParentMap(root, null, nodeParentMap, start, startRef);

        int minute = 0;
        Set<TreeNode> visited = new HashSet<>();

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(startRef[0]);
        visited.add(startRef[0]);
        while (!queue.isEmpty()) {
            int queueSize = queue.size();
            minute++;
            while (queueSize-- > 0) {
                TreeNode polled = queue.poll();
                if (polled.left != null && visited.add(polled.left)) {
                    queue.add(polled.left);
                }
                if (polled.right != null && visited.add(polled.right)) {
                    queue.add(polled.right);
                }
                if (nodeParentMap.get(polled.val) != null && visited.add(nodeParentMap.get(polled.val))) {
                    queue.add(nodeParentMap.get(polled.val));
                }
            }
        }
        return minute - 1;
    }

    private void populateParentMap(TreeNode root, TreeNode parent, Map<Integer, TreeNode> nodeParentMap,
                                   int start, TreeNode[] startRef) {
        if (root == null)
            return;
        nodeParentMap.put(root.val, parent);
        if (root.val == start) {
            startRef[0] = root;
        }
        populateParentMap(root.left, root, nodeParentMap, start, startRef);
        populateParentMap(root.right, root, nodeParentMap, start, startRef);
    }
}
