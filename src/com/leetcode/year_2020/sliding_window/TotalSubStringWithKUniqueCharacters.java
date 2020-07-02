package com.leetcode.year_2020.sliding_window;

import com.util.LogUtil;

/**
 * @author neeraj on 01/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("DuplicateExpressions")
public class TotalSubStringWithKUniqueCharacters {

    public static void main(String[] args) {
        getTotalSubstringCount("pqpqs", 2);
        getTotalSubstringCount("abcabc", 3);
        getTotalSubstringCount("aaacb", 3);
        getTotalSubstringCount("abc", 3);
    }

    public static int getTotalSubstringCount(String str, int k) {
        int[] count; // Since 26 Alphabets

        int totalSubstring = 0;
        int distinctCount;
        for (int i = 0; i < str.length(); i++) {
            distinctCount = 0;
            count = new int[26];
            for (int j = i; j < str.length(); j++) {

                // Check if this item is unique.....
                if (count[str.charAt(j) - 'a'] == 0) distinctCount++;

                // Increment it's frequency
                count[str.charAt(j) - 'a']++;

                if (distinctCount == k) {
                    totalSubstring++;
                }
            }
        }
        LogUtil.logIt("Total SubString with K Unique Characters.... "+ totalSubstring);
        return totalSubstring;
    }
}
