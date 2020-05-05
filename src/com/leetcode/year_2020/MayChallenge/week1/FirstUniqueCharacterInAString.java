package com.leetcode.year_2020.MayChallenge.week1;

/**
 * @author neeraj on 05/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FirstUniqueCharacterInAString {

    public static void main(String[] args) {
        System.out.println(firstUniqChar("leetcode"));
        System.out.println(firstUniqChar("loveleetcode"));

    }

    public static int firstUniqChar(String s) {
        if(s == null || s.length() == 0)
            return -1;
        int [] freq = new int[26];
        for(char c: s.toCharArray()) {
            freq[c - 'a']++;
        }
        int i = 0;
        for(char c: s.toCharArray()) {
            if(freq[c - 'a'] == 1)
                return i;
            i++;
        }
        return -1;
    }
}
