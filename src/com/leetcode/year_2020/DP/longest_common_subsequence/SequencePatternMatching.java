package com.leetcode.year_2020.DP.longest_common_subsequence;

/**
 * https://leetcode.com/problems/is-subsequence/
 * @author neeraj on 09/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class SequencePatternMatching {

    public static void main(String[] args) {
        System.out.println(isSequencePatternMatching("AXY", "ADXCPY"));
    }

    public static boolean isSequencePatternMatching(String X, String Y) {
        /**
         * X = A X Y
         * Y = A D X C Y;
         *
         * What we have to tell is whether X is present in Y.
         * this is a simple problem of LCS(X, Y).... if Length of LCS is == X
         * we are good, X is definitely present in Y, reason being self explanatory
         */
        // Ensuring Always keep X smaller
        if (X.length() > Y.length()) {
            return isSequencePatternMatching(Y, X);
        }
        int lengthOfLCS = LengthOfLongestCommonSubsequence.findLengthOfLCS(X, Y);
        return X.length() == lengthOfLCS;
    }

    /**
     * Isme simple O(N) bhi kaafi hai
     */
    public boolean isSubsequence(String s, String t) {
        int t1 = 0, t2 = 0;
        while(t1 < s.length() && t2 < t.length()) {
            if (s.charAt(t1) == t.charAt(t2)) {
                t1++;
                t2++;
            } else {
                t2++;
            }
        }
        return t1 >= s.length();
    }
}
