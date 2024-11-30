package com.leetcode.year_2020.sliding_window;

import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
    public static void main(String[] args) {
        LongestRepeatingCharacterReplacement lcr = new LongestRepeatingCharacterReplacement();
        System.out.println(lcr.characterReplacement("AABABBA", 1));
        System.out.println(lcr.characterReplacement("ABAA", 0));
    }
    public int characterReplacement(String s, int k) {
        Map<Character, Integer> charFreq = new HashMap<>();
        int end = 0;
        int start = 0;
        int LONGEST = 0;
        while(end < s.length()) {
            char charAtEnd = s.charAt(end);
            charFreq.put(charAtEnd, charFreq.getOrDefault(charAtEnd, 0)+1);

            // find most frequent character in this window
            int mostFrequentItemFrequency = findMostFrequentItemFrequency(charFreq);
            int windowLength = end - start + 1;

            if (windowLength - mostFrequentItemFrequency <= k) {
                // Valid Window
                LONGEST = Math.max(LONGEST, windowLength);
            } else {
                // Time to move start
                charFreq.put(s.charAt(start), charFreq.get(s.charAt(start)) - 1);
                start++;
            }
            end++;
        }
        return LONGEST;
    }

    private int findMostFrequentItemFrequency(Map<Character, Integer> charFreq) {
        int mostFrequent = 0;
        for (Integer freq : charFreq.values()) {
            mostFrequent = Math.max(mostFrequent, freq);
        }
        return mostFrequent;
    }
}
