package com.leetcode.year_2020.trie.coding_with_mik;

import com.util.LogUtil;


public class SumOfPrefixScoresOfStrings {

    public static void main(String[] args) {
        SumOfPrefixScoresOfStrings s = new SumOfPrefixScoresOfStrings();
        LogUtil.printArray(s.sumPrefixScores(new String[]{"abc", "ab", "bc", "b"}));
    }

    public int[] sumPrefixScores(String[] words) {
        TrieNode root = new TrieNode();
        for (String word : words) {
            root.insert(word);
        }

        int[] result = new int[words.length];
        for (int i = 0; i < words.length; i++) {
            String word = words[i];
            result[i] = findSumOfMatchedPrefix(root, word);
        }
        return result;
    }

    public int findSumOfMatchedPrefix(TrieNode root, String word) {
        // Generate All Prefix of word
        int sum = 0;
        TrieNode crawler = root;
        for (char c : word.toCharArray()) {
            int nextIndex = c - 'a';
            if (crawler.children[nextIndex] == null) {
                break;
            }
            crawler = crawler.children[nextIndex];
            sum += crawler.count;
        }
        return sum;
    }

    class TrieNode {
        TrieNode[] children = new TrieNode[26];
        int count = 0;
        boolean isEnd = false;

        public void insert(String word) {
            // Store all prefix of the word
            TrieNode crawler = this;
            for (char c : word.toCharArray()) {
                int nextIndex = c - 'a';
                if (crawler.children[nextIndex] == null) {
                    crawler.children[nextIndex] = new TrieNode();
                }
                crawler = crawler.children[nextIndex];
                crawler.count++;
            }
            crawler.isEnd = true;
        }
    }
}


