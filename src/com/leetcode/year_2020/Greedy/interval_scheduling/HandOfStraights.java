package com.leetcode.year_2020.Greedy.interval_scheduling;

import java.util.TreeMap;

/**
 * https://leetcode.com/problems/hand-of-straights/description/
 * <p>
 * Similar to {@link  com.leetcode.year_2020.Greedy.DivideArrayInKConsecitiveIntegers}
 */
public class HandOfStraights {

    public static void main(String[] args) {
        System.out.println(isNStraightHand(new int[]{1, 2, 3, 6, 2, 3, 4, 7, 8}, 3));
        System.out.println(isNStraightHand(new int[]{1, 2, 3, 4, 5}, 4));
        System.out.println(isNStraightHand(new int[]{1, 2, 3, 3, 4, 4, 5, 6}, 4));
        System.out.println(isNStraightHand(new int[]{3, 2, 1, 2, 3, 4, 3, 4, 5, 9, 10, 11}, 3));
        System.out.println(isNStraightHand(new int[]{1, 2, 3, 4}, 3));
    }

    public static boolean isNStraightHand(int[] hand, int groupSize) {
        TreeMap<Integer, Integer> handFrequency = new TreeMap<>();
        // populate the Map with hand frequency
        for (int i : hand) {
            handFrequency.put(i, handFrequency.getOrDefault(i, 0) + 1);
        }

        while (!handFrequency.isEmpty()) {
            if (!isStraightHand(handFrequency, groupSize)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isStraightHand(TreeMap<Integer, Integer> handFrequency, int groupSize) {

        Integer baseInterval = handFrequency.firstKey();
        reduceFrequencyAndRemoveIfNecessary(handFrequency, baseInterval);

        for (int i = 0; i < groupSize - 1; i++) { // Why groupSize - 1 because we already fetched base interval before
            Integer higherItem = handFrequency.higherKey(baseInterval);
            if (higherItem == null || higherItem - baseInterval != 1) {
                return false;
            }
            // Reduce frequency
            reduceFrequencyAndRemoveIfNecessary(handFrequency, higherItem);
            baseInterval = higherItem;
        }

        return true;
    }

    private static void reduceFrequencyAndRemoveIfNecessary(TreeMap<Integer, Integer> handFrequency, Integer baseInterval) {
        Integer baseIntervalFreq = handFrequency.get(baseInterval);
        if (baseIntervalFreq == 1) {
            handFrequency.remove(baseInterval); // Why because only 1 frequency was that
        } else {
            handFrequency.put(baseInterval, baseIntervalFreq - 1);
        }
    }
}
