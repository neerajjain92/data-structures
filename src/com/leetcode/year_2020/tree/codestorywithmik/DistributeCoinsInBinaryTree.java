package com.leetcode.year_2020.tree.codestorywithmik;

import com.leetcode.year_2020.TreeNode;

/**
 * https://leetcode.com/problems/distribute-coins-in-binary-tree/
 */
public class DistributeCoinsInBinaryTree {

    public static void main(String[] args) {

    }

    /**
     * Intuition
     * <p>
     * A very sexy intuition, every node has to just answer a basic question, how many coins they receive from their children
     * (left and right), the amount of coins they receive basically gets added to our global moves.
     * and the duty of this node is to send the exta coins it had or collected from it's children - 1 to the parent.
     * Why minus 1, because it need one for itself right
     */
    public int distributeCoins(TreeNode root) {
        int[] minMoves = new int[1];
        distributeCoins(root, minMoves);
        return minMoves[0];
    }

    private int distributeCoins(TreeNode root, int[] minMoves) {
        if (root == null)
            return 0;

        int extraInLeft = distributeCoins(root.left, minMoves);
        int extraInRight = distributeCoins(root.right, minMoves);

        // Now 2 possibilities
        // If extraInLeft was positive that means leftChild had more coins
        // if it's in negative that means it's asking for that much coin from parent
        // in both cases coins will move
        minMoves[0] += Math.abs(extraInLeft) + Math.abs(extraInRight);

        // Net balance the node has, subtract 1 for self and return remaining extras.
        return extraInLeft + extraInRight + root.val - 1;
    }
}
