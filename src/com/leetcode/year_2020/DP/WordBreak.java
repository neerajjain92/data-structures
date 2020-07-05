package com.leetcode.year_2020.DP;

import java.util.*;

/**
 * @author neeraj on 02/07/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WordBreak {

    public static void main(String[] args) {
        List<String> dictionary = Arrays.asList("leet", "code");
        System.out.println(wordBreak("leetcode", dictionary));

        dictionary = Arrays.asList("apple", "pen");
        System.out.println(wordBreak("applepenapple", dictionary));

        dictionary = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(wordBreak("catsandog", dictionary));

        dictionary = Arrays.asList("cats", "cat", "sand", "and", "dog");
        System.out.println(wordBreak("catsanddog", dictionary));

        dictionary = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(wordBreak("pineapplepenapple", dictionary));
    }

    static Map<String, Boolean> dp;
    static Map<String, String> dpPrintAll;

    public static boolean wordBreak(String s, List<String> wordDict) {
        dp = new HashMap<>();
        List<String> sentences = new ArrayList<>();
        boolean result = solve(s, "", sentences, new HashSet<>(wordDict));

        sentences = new ArrayList<>();
        printAll(s, 0, "", sentences, new HashSet<>(wordDict));
        return result;
    }

    public static boolean solve(String s, String current, List<String> sentences, Set<String> wordDict) {
        if (dp.containsKey(s)) return dp.get(s);
        if (s.length() == 0) {
            sentences.add(current);
            return true;
        }
        for (int i = 1; i <= s.length(); i++) {
            String substring = s.substring(0, i);
            if (((dp.containsKey(substring) && dp.get(substring)) ||
                    wordDict.contains(s.substring(0, i))) && solve(s.substring(i), current + " " + substring, sentences, wordDict)) {
                dp.put(s, true);
                return true;
            }
        }
        dp.put(s, false);
        return false;
    }


    public static void printAll(String s, int pointer, String current, List<String> all, Set<String> wordDict) {
        if (s.length() == pointer) {
            System.out.println(current);
            all.add(current);
            return;
        }
        for (int i = pointer; i < s.length(); i++) {
            String substring = s.substring(pointer, i + 1);
            if (wordDict.contains(substring)) {
                String nextWord = current.equals("") ? (current + substring) : current + " " + substring;
                printAll(s, i + 1, nextWord, all, wordDict);
            }
        }
    }
}


