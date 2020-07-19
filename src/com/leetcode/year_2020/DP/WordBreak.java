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
        System.out.println(canBreakWordUsingBFS("leetcode", dictionary));

        dictionary = Arrays.asList("apple", "pen");
        System.out.println(canBreakWordUsingBFS("applepenapple", dictionary));

        dictionary = Arrays.asList("cats", "dog", "sand", "and", "cat");
        System.out.println(canBreakWordUsingBFS("catsandog", dictionary));

        dictionary = Arrays.asList("cats", "cat", "sand", "and", "dog");
        System.out.println(canBreakWordUsingBFS("catsanddog", dictionary));

        dictionary = Arrays.asList("apple", "pen", "applepen", "pine", "pineapple");
        System.out.println(canBreakWordUsingBFS("pineapplepenapple", dictionary));
    }

    public static boolean canBreakWordUsingBFS(String s, List<String> wordDictionary) {
        /**
         * Why using BFS, since it will give you result faster than DFS,as we don't have to go deep into 1 route
         * and then backtrack.
         * Now how can we apply BFS, so we can assume the index of the input String to be the starting vertex
         * and we have to reach the end.
         */
        int max_len = -1;
        for (String word : wordDictionary)
            max_len = Math.max (max_len, word.length ());
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // Starting from the 0th index of the input string.
        Set<Integer> visited = new HashSet<>();
        visited.add(0);
        while (!queue.isEmpty()) {
            int currentIndex = queue.poll();
            for (int i = currentIndex + 1; i <= s.length() && i - currentIndex <= max_len; i++) {
                if (visited.contains(i)) continue;
                if (wordDictionary.contains(s.substring(currentIndex, i))) {
                    if (i == s.length()) return true; // You have reached to the end.
                    visited.add(i);
                    queue.offer(i);
                }
            }
        }
        return false; // We cannot break the word.
    }

    static Map<String, Boolean> dp;
    static Map<Integer, Boolean> dpForPointer;
    static Map<String, String> dpPrintAll;

    public static boolean wordBreak(String s, List<String> wordDict) {
        dp = new HashMap<>();
        dpForPointer = new HashMap<>();
        List<String> sentences = new ArrayList<>();
        boolean result = solve(s, "", sentences, new HashSet<>(wordDict));
        System.out.println("Result is " + result);

        result = solve(s, 0, new HashSet<>(wordDict));
//        sentences = new ArrayList<>();
//        printAll(s, 0, "", sentences, new HashSet<>(wordDict));
        return result;
    }

    public static boolean solve(String s, int pointer, Set<String> wordDict) {
        if (dpForPointer.containsKey(pointer)) {
            return dpForPointer.get(pointer);
        }
        if (pointer == s.length()) {
            dpForPointer.put(pointer, true);
            return true;
        }

        for (int i = pointer; i < s.length(); i++) {
            if (wordDict.contains(s.substring(pointer, i + 1))) {
                if (solve(s, i + 1, wordDict)) {
                    dpForPointer.put(i + 1, true);
                    return true;
                }
            }
        }
        dpForPointer.put(pointer, false);
        return false; // We can't break this word
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


