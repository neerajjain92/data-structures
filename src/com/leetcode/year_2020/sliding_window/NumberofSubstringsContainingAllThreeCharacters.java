package com.leetcode.year_2020.sliding_window;

import java.util.HashMap;
import java.util.Map;

/**
 * @author neeraj on 14/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class NumberofSubstringsContainingAllThreeCharacters {

    public static void main(String[] args) {
        System.out.println(numberOfSubstrings("abcabc"));
    }

    public static int numberOfSubstrings(String str) {
        Map<Character, Integer> charFreq = new HashMap<>();
        charFreq.put('a', 1);
        charFreq.put('b', 1);
        charFreq.put('c', 1);
        int start = 0, end = 0;
        int counter = charFreq.size();
        int total = 0;
        while (end < str.length()) {
            char charAtEnd = str.charAt(end);
            if (charFreq.containsKey(charAtEnd)) {
                charFreq.put(charAtEnd, charFreq.get(charAtEnd) - 1);
                if (charFreq.get(charAtEnd) == 0) {
                    counter--;
                }
            }
            end++;
            if (counter == 0) {
                total++;
            }
        }

        // Now let's shrink the window
        while (start < str.length()) {
            char charAtStart = str.charAt(start);
            if (charFreq.containsKey(charAtStart)) {
                charFreq.put(charAtStart, charFreq.get(charAtStart) + 1);
                if (charFreq.get(charAtStart) == 0) {
                    counter++;
                }
            }
            start++;
            if (counter == 0) {
                total++;
            }
        }
        return total;
    }
}
