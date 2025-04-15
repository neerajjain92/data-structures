package com.leetcode.year_2020.DP.longest_common_subsequence;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This same can be used to solve https://leetcode.com/problems/valid-palindrome-iii/description/
 * 1216 Valid Palindrome III
 *
 * @author neeraj on 08/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("JavadocLinkAsPlainText")
public class LongestPalindromicSubsequence {

    static int[][] cache; // Memorization Matrix.

    public static void main(String[] args) {
        System.out.println(longestPalindromeSubseqUsingLCS("bbbab"));
        System.out.println(longestPalindromeSubseqUsingLCS("abccccdd"));
        System.out.println(longestPalindromeSubseqUsingLCS("g"));
        System.out.println(longestPalindromeSubseqUsingLCS("abcd"));
        System.out.println(longestPalindromeSubseqUsingLCS("aab"));
        System.out.println(longestPalindromeSubseqUsingLCS("aaaa"));

        System.out.println("Is Valid Palindrome abcdeca with k=2 ?" + isValidPalindrome("abcdeca", 2));
        System.out.println("Is Valid Palindrome abcdeca with k=1 ?" + isValidPalindrome("abcdeca", 1));
        System.out.println("Is Valid Palindrome abcdeca with k=2 ?" + isValidPalindrome("abbababa", 1));
        System.out.println("Is Valid Palindrome abcdeca with k=0 ?" + isValidPalindrome("abbababa", 0));
    }

    public static int longestPalindromeSubseqUsingLCS(String s) {
        /**
         * Let's try to figure out which parent problem it relates to .
         *
         * 1) Why it's a DP problem
         *      a) Choice            ---> We don't really have anything to choose from
         *      b) Optimal Requirement  ----> Yes we want [Longest]
         *    Hence it's a DP problem.
         * 2) Now is it similar to LCS
         *   Let's try to compare with our Pattern Matching Algorithm
         *
         *          I/p        O/P         Return Type
         *         (2 Input     LCS           int
         *          Array)
         *
         *         (1 Input     LPS           int
         *          String)
         *------------------------------------------------------
         * How much matched : 1/3... but our criteria for any problem to match parent problem is to match 2 criteria.
         * But But BUt..... if you look carefully we have actually 2 Strings only.
         * because what is a palindrome -----> same from both ends, so if 1 input is "ABCDE" then we have to compare
         * "A" ---> "E"
         * "B" ---> "D"
         * "C" ---> "C"
         *
         * So essentially out 2nd Input in case of LPS is Reverse of 1st Input
         *
         *  int LPS [ABCDE, EDCBA] now it's similar to int LCS [ABCDE, EDCBA]
         *
         *  Cool so it's now so trivial to solve the problem with LCS.
         *
         */
        cache = new int[s.length() + 1][s.length() + 1];
        for (int[] row : cache) {
            Arrays.fill(row, -1);
        }

        return longestPalindromeSubseqUsingLCS(s.toCharArray(), new StringBuilder(s).reverse().toString().toCharArray(), s.length(), s.length());
    }

    /**
     * @param X : Character Array representation of input String
     * @param Y : Reverse of input String
     * @param m : length of X
     * @param n : length of Y
     * @return Length of Longest Palindromic Subseq
     */
    private static int longestPalindromeSubseqUsingLCS(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0) return 0; // Empty String can never be palindrome
        // Return from cache
        if (cache[m][n] > -1) return cache[m][n];

        if (X[m - 1] == Y[n - 1]) {
            return cache[m][n] = 1 + longestPalindromeSubseqUsingLCS(X, Y, m - 1, n - 1);
        } else {
            return cache[m][n] = Math.max(longestPalindromeSubseqUsingLCS(X, Y, m - 1, n),
                    longestPalindromeSubseqUsingLCS(X, Y, m, n - 1));
        }
    }

    /**
     * https://leetcode.ca/all/1216.html
     * Given a string s and an integer k, return true if s is a k-palindrome.
     * <p>
     * A string is k-palindrome if it can be transformed into a palindrome by removing at most k characters from it.
     * <p>
     * If you notice carefully, All it wants basically is if we can somehow remove at most k items, can we convert this into palindrome
     * Isn't it simply saying that if you can find out Longest Palindromic subsequence and then len(S) - len(LPS) <= k
     * because if it's > k that means we had to skip more than k characters
     */
    public static boolean isValidPalindrome(String s, int k) {
        cache = new int[s.length() + 1][s.length() + 1];

        for (int[] row : cache) { // This is to basically memoize and figure out if we calculated the LPS of a particular combination
            Arrays.fill(row, -1);
        }

        int lps = findLPS(s.toCharArray(), new StringBuilder(s).reverse().toString().toCharArray(), s.length(), s.length());
        return s.length() - lps <= k;
    }

    private static int findLPS(char[] X, char[] Y, int m, int n) {
        if (m == 0 || n == 0) return 0;
        if (cache[m][n] > -1) return cache[m][n];

        if (X[m - 1] == Y[n - 1]) {
            return 1 + findLPS(X, Y, m - 1, n - 1);
        } else {
            return Math.max(findLPS(X, Y, m - 1, n), findLPS(X, Y, m, n - 1));
        }
    }


}
