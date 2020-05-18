package com.leetcode.year_2020.MayChallenge.week3;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 18/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PermutationInAString {

    public static void main(String[] args) {
        System.out.println(checkInclusion("ab", "eidbaooo"));
        System.out.println(checkInclusion("ab", "eidboaoo"));
    }

    public static boolean checkInclusion(String T, String S) {
        /**
         *  This problem is similar to {@link FindAllAnagramsInAString}
         * and can be solved using Sliding Window Template.
         **/
        if (T.length() > S.length()) return false;
        Map<Character, Integer> charFreq = new HashMap<>();
        for (char c : T.toCharArray()) {
            charFreq.put(c, charFreq.getOrDefault(c, 0) + 1);
        }
        // This will tell us that within current sliding window for S did we find all
        // characters of T
        // When counter reaches 0 then at that moment window contains all elements of T
        int counter = charFreq.size();
        int begin = 0, end = 0;

        while (end < S.length()) {
            char charAtEndOfWindow = S.charAt(end);
            if (charFreq.containsKey(charAtEndOfWindow)) {
                charFreq.put(charAtEndOfWindow, charFreq.get(charAtEndOfWindow) - 1);
                if (charFreq.get(charAtEndOfWindow) == 0) counter--;
            }
            end++; // Expanding the window.

            while (counter == 0) {
                char charAtStartOfWindow = S.charAt(begin);
                if (charFreq.containsKey(charAtStartOfWindow)) {
                    charFreq.put(charAtStartOfWindow, charFreq.get(charAtStartOfWindow) + 1);
                    if (charFreq.get(charAtStartOfWindow) > 0) counter++;
                }
                if (end - begin == T.length()) return true;
                begin++;
            }
        }
        return false;
    }
}
