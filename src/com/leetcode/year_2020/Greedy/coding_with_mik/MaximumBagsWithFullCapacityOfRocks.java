package com.leetcode.year_2020.Greedy.coding_with_mik;

import java.util.Arrays;

/**
 * 2279. Maximum Bags With Full Capacity of Rocks
 * https://leetcode.com/problems/maximum-bags-with-full-capacity-of-rocks/description/
 */
public class MaximumBagsWithFullCapacityOfRocks {
    public static void main(String[] args) {
        System.out.println(maximumBags(new int[]{2, 3, 4, 5}, new int[]{1, 2, 4, 4}, 2));
        System.out.println(maximumBags(new int[]{10, 2, 2}, new int[]{2, 2, 0}, 100));
    }

    public static int maximumBags(int[] capacity, int[] rocks, int additionalRocks) {
        int[] remainder = new int[capacity.length];
        for (int i = 0; i < capacity.length; i++) {
            remainder[i] = capacity[i] - rocks[i];
        }
        Arrays.sort(remainder);

        int count = 0;
        for (int j : remainder) {
            if (j == 0) {
                count++;
            } else if (j <= additionalRocks) {
                additionalRocks -= j;
                count++;
            }
        }
        return count;
    }
}
