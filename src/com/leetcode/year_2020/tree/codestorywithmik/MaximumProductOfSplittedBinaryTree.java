package com.leetcode.year_2020.tree.codestorywithmik;

import com.leetcode.year_2020.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MaximumProductOfSplittedBinaryTree {

    public static void main(String[] args) {

    }

    /*
     * Intuition is simple, calculate every subtree sum, basically on that node
     * SubTreeSum = sum(LeftSubTree) + sub(RightSubTree) + node
     * So basically doing a PostOrder traversal helps in this.
     *
     * Now walk over the all subTreeSum and start skipping that
     * SubTreeSum * (TotalSum - SubTreeSum)
     * So this becomes 2 part splitted binary tree
     *         1
     *        / \
     *       2   3
     *      / \
     *     4   5
     * SubTreeSum(4) --> 4
     * SubTreeSum(5) --> 5
     * SubTreeSum(2) --> 11 (4 + 5 + 2)
     * SubTreeSum(3) --> 3
     * SubTreeSum(1) --> SubTreeSum(2) + SubTreeSum(3) => 11 + 3 + 1 => 15
     *
     * Now walk over all subtreeSum
     *
     * Cut the branch above 4
     * SubTreeSum(4) * (TotalSum - SubTreeSum(4) => 4 * (15-4) = > 44
     * SubTreeSum(5) * (TotalSum - SubTreeSum(5) => 5 * (15-5) = > 50
     * SubTreeSum(2) * (TotalSum - SubTreeSum(2) => 11 * (15-11) = > 44
     * SubTreeSum(3) * (TotalSum - SubTreeSum(4) => 3 * (15-3) = > 36
     * SubTreeSum(1) * (TotalSum - SubTreeSum(1) => 15 * (15-15) = > 0
     *
     */
    public int maxProduct(TreeNode root) {
        List<Integer> subtreeSum = new ArrayList<>();
        long total = calculateSubTreeSum(root, subtreeSum);
        long max = 0;
        for (long sum : subtreeSum) {
            max = Math.max(max, sum * (total - sum));
        }
        return (int) (max % 1_000_000_007);
    }

    private int calculateSubTreeSum(TreeNode root, List<Integer> subtreeSum) {
        if (root == null) return 0;
        int leftSum = calculateSubTreeSum(root.left, subtreeSum);
        int rightSum = calculateSubTreeSum(root.right, subtreeSum);
        subtreeSum.add(leftSum + rightSum + root.val);
        return leftSum + rightSum + root.val;
    }
}
