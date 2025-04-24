package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.HashMap;
import java.util.Map;

public class MinimumNumberOfOperationsToMakeArrayEmpty {

    public static void main(String[] args) {
        MinimumNumberOfOperationsToMakeArrayEmpty obj = new MinimumNumberOfOperationsToMakeArrayEmpty();
        System.out.println(obj.minOperations(new int[]{2, 3, 3, 2, 2, 4, 2, 3, 4}));
        System.out.println(obj.minOperations(new int[]{14, 12, 14, 14, 12, 14, 14, 12, 12, 12, 12, 14, 14, 12, 14, 14, 14, 12, 12}));
    }

    public int minOperations(int[] nums) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int num : nums) {
            freq.put(num, freq.getOrDefault(num, 0) + 1);
        }

        int operations = 0;
        for (int polledFreq : freq.values()) {
            if (polledFreq == 1) {
                return -1;
            } else {
//                operations += ((polledFreq / 3) + ((polledFreq % 3 != 0) ? 1 : 0));
                // OR instead of adding this 1 we can simply do the ceiling
                operations += (int) Math.ceil((double) polledFreq / 3);
            }
        }
        return operations;

    }
}
