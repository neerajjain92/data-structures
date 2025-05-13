package com.leetcode.year_2020.backtracking;

import java.util.ArrayList;
import java.util.List;

/**
 * https://leetcode.com/problems/palindrome-partitioning/description/
 *
 * @author neeraj on 10/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class PalindromicPartitioning {

    public static void main(String[] args) {
//        System.out.println(partition("aab"));
        PalindromicPartitioning p = new PalindromicPartitioning();
//        System.out.println(p.partition("aabb"));
        System.out.println(p.partition("aab"));
    }

    public List<List<String>> partition(String str) {
        /*
         * [a a b] ==> [[a, a, b], [aa, b]]
         */
        List<List<String>> result = new ArrayList<>();
        int partitionIndex = 0;
        backtrack(partitionIndex, str, str.length(), result, new ArrayList<>());
        return result;
    }

    public void backtrack(int partitionIndex, String str, int len, List<List<String>> result, List<String> current) {
        if (partitionIndex == len) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = partitionIndex; i < len; i++) {
            String part = str.substring(partitionIndex, i + 1);
            if (isPalindrome(part)) {
                current.add(part);
                backtrack(i+1, str, len, result, current);
                current.remove(current.size() - 1);
            }
        }
    }

    private boolean isPalindrome(String part) {
        return new StringBuilder(part).reverse().toString().equals(part);
    }

//    public static List<List<String>> partition(String s) {
//        List<List<String>> result = new ArrayList<>();
//        palindromicSubset(s, 0, new ArrayList<>(), result);
//        return result;
//    }
//
//    private static void palindromicSubset(String s, int pointer, ArrayList<String> subset, List<List<String>> result) {
//        if (pointer == s.length()) {
//            System.out.println(subset);
//            result.add(new ArrayList<>(subset));
//            return;
//        }
//        for (int i = pointer; i < s.length(); i++) {
//            if (isPalindrome(s.substring(pointer, i + 1))) {
//                subset.add(s.substring(pointer, i + 1));
//                palindromicSubset(s, i + 1, subset, result);
//                subset.remove(subset.size() - 1);
//            }
//        }
//    }
//
//    private static boolean isPalindrome(String str) {
//        String temp = str;
//        return new StringBuffer(str).reverse().toString().equals(temp);
//    }
}
