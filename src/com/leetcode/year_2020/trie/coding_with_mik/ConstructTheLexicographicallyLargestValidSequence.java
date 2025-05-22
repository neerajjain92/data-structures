package com.leetcode.year_2020.trie.coding_with_mik;

import com.util.LogUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * https://leetcode.com/problems/construct-the-lexicographically-largest-valid-sequence/description/
 */
public class ConstructTheLexicographicallyLargestValidSequence {

    public static void main(String[] args) {
        ConstructTheLexicographicallyLargestValidSequence obj = new ConstructTheLexicographicallyLargestValidSequence();
        LogUtil.printArray(obj.constructDistancedSequence(1));
        LogUtil.printArray(obj.constructDistancedSequence(2));
        LogUtil.printArray(obj.constructDistancedSequence(3));
        LogUtil.printArray(obj.constructDistancedSequence(4));
        LogUtil.printArray(obj.constructDistancedSequence(5));
    }

    /**
     * Got Intuition from here
     * https://www.youtube.com/watch?v=wNOZM1Ki4DY&list=PLpIkg8OmuX-KJPC18SGiRUzJQAYo839ox&index=18&ab_channel=codestorywithMIK
     * <p>
     * Questions says use [1] only once and all other items from 2-n twice
     * so we know the total items in sequence will be {2*n-1}
     * <p>
     * and if anyone ask you to make something lexographically largest number obviously you will pick the largest number first right
     * <p>
     * So for n=3
     * total size = 2 * 3 - 1 = 5
     * <p>
     * [ __, __, __, __, __ ]
     * 0   1   2   3   4
     * <p>
     * we will put the largest number in 1st slot, so let's put 3
     * <p>
     * [ _3_, __, __, __, __ ]
     * 0    1   2   3   4
     * Now since we have to put 2-n number twice so and difference between
     * ith item and it's second instance should be i
     * so we will put next item at i + nums[i], if its empty
     * <p>
     * so 0 + nums[0] ==> 0 + 3
     * [ _3_, __, __, _3_, __ ]
     * <p>
     * Now move to next and try placing 2
     * [ _3_, _2_, __, _3_, __ ]
     * <p>
     * but second instance of 2 should be placed at 1 + nums[1] = 1 + 2 ==> index 3
     * but index 3 is not empty so we skip this and move to lower value, always remember
     * that we can put 1 only once
     * <p>
     * [ _3_, _1_, _2_, _3_, _2_ ] ==> this is valid and largest
     */
    public int[] constructDistancedSequence(int n) {
        if (n == 1) return new int[]{1};
        Set<Integer> used = new HashSet<>();
        int indexBeingPlaced = 0;
        int[] result = new int[2 * n - 1];
        solve(indexBeingPlaced, n, result, used);
        return result;
    }

    private boolean solve(int indexBeingPlaced, int n, int[] result, Set<Integer> used) {
        // Filled everything
        if (indexBeingPlaced >= result.length) {
            return true;
        }

        // If the position is already filled, move to next
        if (result[indexBeingPlaced] != 0) {
            return solve(indexBeingPlaced + 1, n, result, used);
        }

        for (int i = n; i >= 1; i--) {
            if (!used.contains(i)) {
                if (i != 1 && (indexBeingPlaced + i >= result.length || result[indexBeingPlaced + i] != 0)) {
                    continue;
                }
                // Choose
                used.add(i);
                result[indexBeingPlaced] = i;
                if (i != 1) {
                    result[indexBeingPlaced + i] = i;
                }
                // Explore
                if (solve(indexBeingPlaced + 1, n, result, used)) {
                    return true;
                }

                // UnChoose
                used.remove(i);
                result[indexBeingPlaced] = 0;
                if (i != 1) {
                    result[indexBeingPlaced + i] = 0;
                }
            }
        }
        return false;
    }
}
