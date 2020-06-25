package com.leetcode.year_2020.DP.longest_common_subsequence;

/**
 * @author neeraj on 23/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class InterleavingString {

    public static void main(String[] args) {
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac"));
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbbaccc"));
        System.out.println(isInterleave("aa", "aa", "aa"));
        System.out.println(isInterleave("", "", ""));
    }

    public static boolean isInterleave(String s1, String s2, String s3) {
        return isInterleave(s1, s2, s3, 0, 0, 0);
    }

    private static boolean isInterleave(String s1, String s2, String s3, int i, int j, int k) {
        if (k == s3.length())
            return i == s1.length() && j == s2.length();

        if (i < s1.length() && s1.charAt(i) == s3.charAt(k)
                && isInterleave(s1, s2, s3, i + 1, j, k + 1)) {
            return true;
        }
        return j < s2.length() && s2.charAt(j) == s3.charAt(k) && isInterleave(s1, s2, s3, i, j + 1, k + 1);
    }

}
