package com.geeksforgeeks.array.sliding_window;

import com.util.LogUtil;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Find the smallest window in a string containing all characters of another string
 * <p>
 * https://www.geeksforgeeks.org/find-the-smallest-window-in-a-string-containing-all-characters-of-another-string/
 * https://leetcode.com/problems/minimum-window-substring/
 * <p>
 * Given a string S and a string T, find the minimum window in S which will contain all the characters in T in complexity O(n).
 * <p>
 * Example:
 * <p>
 * Input: S = "ADOBECODEBANC", T = "ABC"
 * Output: "BANC"
 *
 * @author neeraj on 2019-05-18
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MinimumWindowSubstring {

    public static void main(String[] args) {
        solveMinimumWindowSubStringUsingSlidingWindow("AZJSKFZTS", "SZ");
        solveMinimumWindowSubStringUsingSlidingWindow("geeksforgeeks", "ork");
        solveMinimumWindowSubStringUsingSlidingWindow("this is a test string", "tist");
    }

    public static void solveMinimumWindowSubStringUsingSlidingWindow(String haystack, String needle) {
        int leftPointer = 0;
        int rightPointer = 0;
        String minimumWindow = "lkdasjdlkasjdlaksdjal;ksjdalsk;djalskdjaskl;djaslKDJASLKD;JASDLKASJLDKASJDLKASJD";
        String window;

        Map<Character, Long> needleFrequency = needle.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));

        while (leftPointer < haystack.length() && rightPointer < haystack.length()) {
            window = haystack.substring(leftPointer, rightPointer + 1);
            if (needleFoundInHaystack(needleFrequency, window, needle)) {
                if (minimumWindow.length() > (rightPointer - leftPointer + 1)) {
                    minimumWindow = haystack.substring(leftPointer, rightPointer + 1);
                    System.out.println(minimumWindow);
                }
                leftPointer++;
            } else {
                rightPointer++;
            }
        }
        LogUtil.logIt("Minimum Window Substring for needle[" + needle + "] in haystack[" + haystack + "]  is " + minimumWindow, true);
    }

    private static boolean needleFoundInHaystack(Map<Character, Long> needleFrequency, String window, String needle) {
        Map<Character, Long> tempFreq = new HashMap<>();

        for (char c : window.toCharArray()) {
            if (needle.contains(String.valueOf(c))) {
                if (tempFreq.containsKey(c)) {
                    tempFreq.put(c, needleFrequency.get(c) + 1L);
                } else {
                    tempFreq.put(c, 1L);
                }
            }
        }
        for (char c : needle.toCharArray()) {
            if (!tempFreq.containsKey(c) || needleFrequency.get(c) > tempFreq.get(c)) {
                return false;
            }
        }
        return true;
    }
}
