package com.leetcode.year_2020.sliding_window.codestorywithmik;

import java.util.HashMap;
import java.util.Map;

/**
 * https://leetcode.com/problems/count-complete-substrings/description/
 * You are given a string word and an integer k.
 * <p>
 * A substring s of word is complete if:
 * <p>
 * Each character in s occurs exactly k times.
 * The difference between two adjacent characters is at most 2. That is, for any two adjacent characters c1 and c2 in s, the absolute difference in their positions in the alphabet is at most 2.
 * Return the number of complete substrings of word.
 * <p>
 * A substring is a non-empty contiguous sequence of characters in a string.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: word = "igigee", k = 2
 * Output: 3
 * Explanation: The complete substrings where each character appears exactly twice and the difference between adjacent characters is at most 2 are: igigee, igigee, igigee.
 * Example 2:
 * <p>
 * Input: word = "aaabbbccc", k = 3
 * Output: 6
 * Explanation: The complete substrings where each character appears exactly three times and the difference between adjacent characters is at most 2 are: aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc, aaabbbccc.
 */
public class CountCompleteSubstrings {

    public static void main(String[] args) {
        CountCompleteSubstrings obj = new CountCompleteSubstrings();
        System.out.println(obj.countCompleteSubstrings("igigee", 2));
        System.out.println(obj.countCompleteSubstrings("aaabbbccc", 3));
        System.out.println(obj.countCompleteSubstrings("aaa", 1));
        System.out.println(obj.countCompleteSubstrings("abb", 1));
        System.out.println(obj.countCompleteSubstrings("aaabxddee", 1));


        System.out.println(obj.countCompleteSubstringsUsingSlidingWindow("igigee", 2));
        System.out.println(obj.countCompleteSubstringsUsingSlidingWindow("aaabbbccc", 3));
        System.out.println(obj.countCompleteSubstringsUsingSlidingWindow("aaa", 1));
        System.out.println(obj.countCompleteSubstringsUsingSlidingWindow("abb", 1));
        System.out.println(obj.countCompleteSubstringsUsingSlidingWindow("aaabxddee", 1));
        System.out.println(obj.countCompleteSubstringsUsingSlidingWindow("ca", 1));
    }

    public int countCompleteSubstrings(String word, int k) {
        int count = 0;
        for (int i = 0; i < word.length(); i++) {
            for (int j = i; j < word.length(); j++) {
                if (isSubstringValid(i, j, word, k)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean isSubstringValid(int i, int j, String word, int k) {
        Map<Character, Integer> charFreqCount = new HashMap<>();
        for (int counter = i; counter <= j; counter++) {
            charFreqCount.put(word.charAt(counter), charFreqCount.getOrDefault(word.charAt(counter), 0) + 1);
            if (counter - i > 0) {
                char currChar = word.charAt(counter);
                char prevChar = word.charAt(counter - 1);
                if (Math.abs(currChar - prevChar) > 2) {
                    return false;
                }
            }
        }
        for (Integer val : charFreqCount.values()) {
            if (val != k) {
                return false;
            }
        }
        return true;
    }


    /**
     * a  a  a  b  x  d  d  e  e
     * b|e
     * e  e  e  e
     * <p>
     * This is still O(N^2)
     */
    public int countCompleteSubstringsUsingSlidingWindow(String word, int k) {
        int count = 0;
        for (int start = 0; start < word.length(); start++) {
            Map<Character, Integer> charFreqCount = new HashMap<>();
            for (int end = start; end < word.length(); end++) {
                char currChar = word.charAt(end);
                charFreqCount.put(currChar, charFreqCount.getOrDefault(currChar, 0) + 1);

                if (end > start && Math.abs(currChar - word.charAt(end - 1)) > 2) {
                    break; // Found a new segment
                }

                if (areAllFrequenciesK(charFreqCount, k)) {
                    count++;
                }
            }
        }
        return count;
    }

    private boolean areAllFrequenciesK(Map<Character, Integer> charFreqCount, int k) {
        for (int freq : charFreqCount.values()) {
            if (freq != k) {
                return false;
            }
        }
        return true;
    }
}
