package com.leetcode.year_2020.DP;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/decode-ways/
 *
 * @author neeraj on 07/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class DecodeWaysCountNumberOfWays {

    public static void main(String[] args) {
        System.out.println(numDecodings("123"));
        System.out.println(numDecodings("01"));
    }

    static int T[]; // memorization;

    public static int numDecodings(String s) {
        /**
         * Input: "12"
         * Output: 2
         * Explanation: It could be decoded as "AB" (1 2) or "L" (12).
         * By looking at question, we clearly know one thing for sure the max
         * characters we can combine is 2 because ... Alphabets are only 26.
         *
         * So at each character :
         * --> we have select it just alone.
         * --> we can club it with one more character.
         */
        T = new int[s.length() + 1];
        Arrays.fill(T, -1);
        return findNumberOfDecodeWays(s, 0);
    }

    private static int findNumberOfDecodeWays(String s, int decodePointer) {
        if (decodePointer == s.length()) {
            // we reached to the end this means we found 1 decoding.
            return 1;
        }

        // Return from cache.
        if (T[decodePointer] != -1) return T[decodePointer];

        // Now from decodePointer we will club upto 2 characters
        int totalDecompositions = 0;
        for (int i = 1; i <= 2; i++) { // Try using the character alone and then combining with the next character.
            if (decodePointer + i <= s.length()) { // So that we don't go out of bounds.
                if (isValidDecoding(s.substring(decodePointer, decodePointer + i))) {
                    totalDecompositions += findNumberOfDecodeWays(s, decodePointer + i);
                }
            }
        }
        return T[decodePointer] = totalDecompositions;
    }

    private static boolean isValidDecoding(String substring) {
        int value = Integer.parseInt(substring);
        return !substring.startsWith("0") && value >= 1 && value <= 26;
    }
}
