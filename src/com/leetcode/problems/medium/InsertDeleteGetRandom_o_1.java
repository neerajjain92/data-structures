package com.leetcode.problems.medium;

import java.util.*;

/**
 * @author neeraj on 12/10/19
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class InsertDeleteGetRandom_o_1 {

    public static void main(String[] args) {
        RandomizedSet obj = new RandomizedSet();
        boolean param_1 = obj.insert(1);
        boolean param_2 = obj.remove(2);
        int param_3 = obj.getRandom();
        System.out.println(param_3);
    }

    public static class RandomizedSet {
        Map<Integer, Integer> valPositionMap;
        List<Integer> allValues;

        /**
         * Initialize your data structure here.
         */
        public RandomizedSet() {
            valPositionMap = new HashMap<>();
            allValues = new ArrayList<>();
        }

        /**
         * Inserts a value to the set. Returns true if the set did not already contain the specified element.
         */
        public boolean insert(int val) {
            if (!valPositionMap.containsKey(val)) {
                allValues.add(val);
                valPositionMap.put(val, allValues.size() - 1);
                return true;
            } else {
                return false;
            }
        }

        /**
         * Removes a value from the set. Returns true if the set contained the specified element.
         */
        public boolean remove(int val) {
            if (valPositionMap.containsKey(val)) {
                int index = valPositionMap.get(val);

                // If this is not the last item
                if (index < allValues.size() - 1) {
                    // Swap the val with the last item in map
                    int lastItem = allValues.get(allValues.size() - 1);
                    allValues.set(index, lastItem);
                    valPositionMap.put(lastItem, index);
                }
                allValues.remove(allValues.size() - 1);
                valPositionMap.remove(val);
                return true;
            } else {
                return false;
            }
        }

        /**
         * Get a random element from the set.
         */
        public int getRandom() {
            return allValues.get(new Random().nextInt(allValues.size()));
        }
    }
}
