package com.leetcode.year_2020.MayChallenge.week1;

/**
 * @author neeraj on 04/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class RansomNote {

    public static void main(String[] args) {
        System.out.println(canConstruct("a", "b"));
        System.out.println(canConstruct("aa", "ab"));
        System.out.println(canConstruct("aa", "aab"));
    }

    public static boolean canConstruct(String ransomNote, String magazine) {
        if (ransomNote.length() > magazine.length()) return false;
        int[] freq = new int[26]; // total 26 alphabet(small-case);
        for (char c : magazine.toCharArray()) {
            freq[c - 'a'] = freq[c - 'a'] + 1;
        }
        for (char c : ransomNote.toCharArray()) {
            if (freq[c - 'a'] == 0) return false;
            else {
                freq[c - 'a'] = freq[c - 'a'] - 1;
            }
        }
        return true;
    }
}
