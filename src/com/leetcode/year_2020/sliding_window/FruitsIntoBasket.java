package com.leetcode.year_2020.sliding_window;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * https://leetcode.com/problems/fruit-into-baskets/
 *
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FruitsIntoBasket {

    public static void main(String[] args) {
        System.out.println(totalFruit(new int[]{1, 2, 1}));
        System.out.println(totalFruit(new int[]{0, 1, 2, 2}));
        System.out.println(totalFruit(new int[]{1, 2, 3, 2, 2}));
        System.out.println(totalFruit(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));

        LogUtil.logIt("With Sliding Window O(N) approach...");
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{1, 2, 1}));
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{0, 1, 2, 2}));
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{1, 2, 3, 2, 2}));
        System.out.println(lengthOfLongestSubstringTwoDistinct(new int[]{3, 3, 3, 1, 2, 1, 1, 2, 3, 3, 4}));
    }

    /**
     * This fruit problem is nothing but asking longest subarray with 2 distinct items.
     */
    public static int lengthOfLongestSubstringTwoDistinct(int[] arr) {
        Map<Integer, Integer> charFreq = new HashMap<>();
        /**
         * This problem is similar to {@link LongestSubstringWithoutRepeatingCharacters}
         * there also we didn't pre-populate charFreq, and used counter to disrupt the window.
         */
        int start = 0, end = 0, counter = 0;
        int longestSubStringLength = Integer.MIN_VALUE;
        while (end < arr.length) {
            Integer itemAtEndPointer = arr[end];
            charFreq.put(itemAtEndPointer, charFreq.getOrDefault(itemAtEndPointer, 0) + 1);
            if (charFreq.get(itemAtEndPointer) == 1) {
                counter++; // Found a New Character.
            }
            end++;

            while (counter > 2) { // this means we have found more than 2 distinct characters
                // so we need to remove those many characters
                Integer itemAtStartPointer = arr[start];
                charFreq.put(itemAtStartPointer, charFreq.get(itemAtStartPointer) - 1);
                if (charFreq.get(itemAtStartPointer) == 0) counter--;
                start++;
            }
            longestSubStringLength = Math.max(longestSubStringLength, end - start);
        }
        return longestSubStringLength;
    }

    public static int totalFruit(int[] trees) {
        /**
         * This problem is exactly similar to {@link LongestSubStringWithMUniqueCharacters}
         * In this case we just have M always 2
         */
        int start = 0, end = 0;
        int maxTotalFruit = 0;
        while (end < trees.length) {
            int unique = unique(trees, start, end);
            if (unique <= 2) {
                if (unique == 2) {
                    maxTotalFruit = Math.max(maxTotalFruit, end - start + 1);
                }
                end++;
            } else {
                start++;
            }
        }
        return maxTotalFruit;
    }

    private static int unique(int[] trees, int start, int end) {
        Set<Integer> set = new HashSet<>();
        for (int i = start; i <= end; i++) {
            set.add(trees[i]);
        }
        return set.size();
    }
}
