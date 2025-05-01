package com.leetcode.year_2020.stack.codingWithMIK;

import java.util.Stack;

/**
 * https://leetcode.com/problems/maximum-score-from-removing-substrings/description/
 * 1717. Maximum Score From Removing Substrings
 */
public class MaximumScoreFromRemovingSubstrings {

    public static void main(String[] args) {
        MaximumScoreFromRemovingSubstrings obj = new MaximumScoreFromRemovingSubstrings();
        System.out.println(obj.maximumGain("cdbcbbaaabab", 4, 5));
        System.out.println(obj.maximumGain("aabbaaxybbaabb", 5, 4));

        System.out.println(obj.maximumGain_O_1_Space("cdbcbbaaabab", 4, 5));
        System.out.println(obj.maximumGain_O_1_Space("aabbaaxybbaabb", 5, 4));
    }

    /**
     * https://www.youtube.com/watch?v=WTAjAcjSTqM&list=PLpIkg8OmuX-IA6_cJxfTYCmnv1jkqox47&index=20
     * In O(1) time complexity
     */
    public int maximumGain_O_1_Space(String s, int x, int y) {
        boolean shouldSwap = x > y;
        int totalGain;

        String removedPair = shouldSwap ? removePairs(s, 'a', 'b') : removePairs(s, 'b', 'a');
        int removedItemsFirstPair = s.length() - removedPair.length();

        s = removedPair;

        removedPair = shouldSwap ? removePairs(s, 'b', 'a') : removePairs(s, 'a', 'b');
        int removedItemsSecondPair = s.length() - removedPair.length();

        // Calculate total Gain
        int totalPairFromFirstItems = removedItemsFirstPair / 2;
        int totalPairFromSecondItems = removedItemsSecondPair / 2;
        if (shouldSwap) {
            totalGain = totalPairFromFirstItems * x + totalPairFromSecondItems * y;
        } else {
            totalGain = totalPairFromSecondItems * x + totalPairFromFirstItems * y;
        }
        return totalGain;
    }

    public String removePairs(String s, char first, char second) {
        int i = 0, j = 0; // I is the writing pointer, j is reading pointer
        char[] chars = s.toCharArray();
        int n = chars.length;
        while (j < n) {
            chars[i] = chars[j]; // Override j to i
            i++;
            j++;
            // Now i will simply check, are prev 2 characters first and second respectively
            if (i > 1 && chars[i - 2] == first && chars[i - 1] == second) {
                i = i - 2; // Reset i to the position of first char, so j can override on that instead
            }
        }
        return new String(chars).substring(0, i);
    }

    /**
     * Time Complexity is O(N) and Space is also O(N)
     */
    public int maximumGain(String s, int x, int y) {
        int total;
        if (x > y) {
            Result first = removePair(s, 'a', 'b', x);
            Result second = removePair(first.leftOver, 'b', 'a', y);
            total = first.total + second.total;
        } else {
            Result first = removePair(s, 'b', 'a', y);
            Result second = removePair(first.leftOver, 'a', 'b', x);
            total = first.total + second.total;
        }
        return total;
    }

    private Result removePair(String s, char first, char second, int score) {
        int total = 0;
        StringBuilder leftOver = new StringBuilder();
        Stack<Character> stack = new Stack<>();
        for (char c : s.toCharArray()) {
            if (c == second) {
                if (!stack.isEmpty() && stack.peek() == first) {
                    total += score;
                    stack.pop();
                    leftOver.deleteCharAt(leftOver.length() - 1);
                } else {
                    stack.push(c);
                    leftOver.append(c);
                }
            } else {
                stack.push(c);
                leftOver.append(c);
            }
        }
        return new Result(total, leftOver.toString());
    }

    static class Result {
        int total;
        String leftOver;

        public Result(int total, String leftOver) {
            this.total = total;
            this.leftOver = leftOver;
        }
    }


}
