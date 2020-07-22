package com.leetcode.year_2020.sliding_window;

/**
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberofSubstringsContainingAllThreeCharacters {

    public static void main(String[] args) {
        System.out.println(getTotalSubstringCount("abcabc", 3));
    }

    public static int getTotalSubstringCount(String str, int k) {
        int[] count = new int[26]; // Since 26 Alphabets

        int totalSubstring = 0;
        int result = 0;
        int end = 0, start = 0;
        while (end < str.length()) {
            count[str.charAt(end) - 'a']++;
            end++;
            while (start < str.length() && count[0] > 0 && count[1] > 0 && count[2] > 0) {
                result++;
                count[str.charAt(start) - 'a']--;
                start++;
            }
            totalSubstring += result;
        }
        return totalSubstring;
    }
}
