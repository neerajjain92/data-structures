package com.leetcode.problems;

import com.util.LogUtil;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static com.util.LogUtil.logIt;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 * <p>
 * In a row of trees, the i-th tree produces fruit with type tree[i].
 * <p>
 * You start at any tree of your choice, then repeatedly perform the following steps:
 * <p>
 * Add one piece of fruit from this tree to your baskets.  If you cannot, stop.
 * Move to the next tree to the right of the current tree.  If there is no tree to the right, stop.
 * Note that you do not have any choice after the initial choice of starting tree: you must perform step 1, then step 2, then back to step 1, then step 2, and so on until you stop.
 * <p>
 * You have two baskets, and each basket can carry any quantity of fruit, but you want each basket to only carry one type of fruit each.
 * <p>
 * What is the total amount of fruit you can collect with this procedure?
 * Example 1:
 * <p>
 * Input: [1,2,1]
 * Output: 3
 * Explanation: We can collect [1,2,1].
 * Example 2:
 * <p>
 * Input: [0,1,2,2]
 * Output: 3
 * Explanation: We can collect [1,2,2].
 * If we started at the first tree, we would only collect [0, 1].
 * Example 3:
 * <p>
 * Input: [1,2,3,2,2]
 * Output: 4
 * Explanation: We can collect [2,3,2,2].
 * If we started at the first tree, we would only collect [1, 2].
 * Example 4:
 * <p>
 * Input: [3,3,3,1,2,1,1,2,3,3,4]
 * Output: 5
 * Explanation: We can collect [1,2,1,1,2].
 * If we started at the first tree or the eighth tree, we would only collect 4 fruits.
 *
 * @author neeraj on 2019-08-03
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 * <p>
 * This is the variation of Longest Common Substring problem.
 * We will do it via Sliding Window.
 */
public class FruitsIntoBasket_904 {

    static Map<Integer, Integer> fruitFrequencyMap = new HashMap<>();
    static int fruitA = -1;
    static int fruitB = -1;

    public static void main(String[] args) {
//        findMaxFruits(new int[]{1, 2, 1});
//        findMaxFruits(new int[]{0, 1, 2, 2});
        findMaxFruits(new int[]{1, 2, 3, 2, 2});
        findMaxFruits(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4});
        findMaxFruits(new int[]{0});
        findMaxFruits(new int[]{0, 0, 1, 1});
    }

    /**
     * https://www.youtube.com/watch?v=za2YuucS0tw
     * <p>
     * In this approach instead of keeping the frequency of those 2 fruits
     * we will keep the last index where we found that fruits, so that when we have to remove
     * the fruit, since we have more than 2 types of fruits, what we can do it just find out
     * lastPosition of that fruit and make Left[window] = minPosition + 1;
     *
     * @param tree
     * @return
     */
    public static int simpleApproachOfFindMaxFruits(int[] tree) {
        if (tree == null || tree.length == 0) {
            return 0;
        }
        Map<Integer, Integer> fruitPositionMap = new HashMap<>();
        int Left = 0;
        int right = 0;
        int MAX_FRUITS = 0;

        while (right < tree.length) {
            if (fruitPositionMap.size() <= 2) {
                fruitPositionMap.put(tree[right], right++);
            }

            if (fruitPositionMap.size() > 2) {
                int min = Collections.min(fruitPositionMap.values());
                Left = min + 1;
                fruitPositionMap.remove(tree[min]);
            }
            MAX_FRUITS = Math.max(MAX_FRUITS, right - Left);
        }
        return MAX_FRUITS;
    }

    public static void findMaxFruits(int[] tree) {
//        logIt("Maximum Number of Fruits that can be plucked from " + LogUtil.getArrayAsString(tree) + " ==> " + totalFruit(tree));
        logIt("Maximum Number of Fruits that can be plucked using Simple Approach from "
                + LogUtil.getArrayAsString(tree) + " ==> " + simpleApproachOfFindMaxFruits(tree), true);
    }

    public static int totalFruit(int[] tree) {
        fruitFrequencyMap = new HashMap<>();
        fruitA = -1;
        fruitB = -1;
        int MAX_FRUITS = 0;
        int L = 0, R = 0;

        while (R < tree.length) {
            // Only if there are fruits to pluck,
            // we can add it to our basket
            boolean isSet = setFruitAndFrequency(tree[R]);

            if (isSet) {
                MAX_FRUITS = Math.max(MAX_FRUITS, R - L + 1);
                R++;
            } else {
                while ((getFruitFrequency(fruitA) != 0 && getFruitFrequency(fruitB) != 0) && L <= R) {
                    int fruitToRemove = tree[L];
                    L++;
                    if (fruitFrequencyMap.containsKey(fruitToRemove)) {
                        fruitFrequencyMap.put(fruitToRemove, fruitFrequencyMap.get(fruitToRemove) - 1);
                        if (fruitFrequencyMap.get(fruitToRemove) <= 0) {
                            fruitA = fruitToRemove == fruitA ? -1 : fruitA;
                            fruitB = fruitToRemove == fruitB ? -1 : fruitB;
                            fruitFrequencyMap.remove(fruitToRemove);
                        }
                    }
                }
            }
        }
        return MAX_FRUITS;
    }

    public static int getFruitFrequency(int fruit) {
        return fruitFrequencyMap.get(fruit) == null ? 0 : fruitFrequencyMap.get(fruit);
    }

    /**
     * Util method which will set the fruit and it's frequency
     * appropriately only when this new fruit can be accomodated
     * within the bucket.
     *
     * @param i
     * @return
     */
    private static boolean setFruitAndFrequency(int i) {
        // First check if any of the fruit is set or not
        // Also check if the frequency of this new item isn't null
        if (fruitA < 0 || fruitB < 0 || fruitFrequencyMap.get(i) != null) {
            if (fruitA == -1 || fruitA == i) {
                fruitA = i;
            } else if (fruitB == -1 || fruitB == i) {
                fruitB = i;
            }
            if (fruitFrequencyMap.containsKey(i)) {
                fruitFrequencyMap.put(i, fruitFrequencyMap.get(i) + 1);
            } else {
                fruitFrequencyMap.put(i, 1);
            }
            return true;
        } else {
            // If both fruit are set and this fruit is different from the previous 2 set fruits.
            return false;
        }
    }
}
