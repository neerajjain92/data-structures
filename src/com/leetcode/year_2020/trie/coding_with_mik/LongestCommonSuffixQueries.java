package com.leetcode.year_2020.trie.coding_with_mik;

import com.util.LogUtil;

public class LongestCommonSuffixQueries {

    public static void main(String[] args) {
        LongestCommonSuffixQueries obj = new LongestCommonSuffixQueries();
        LogUtil.printArray(obj.stringIndices(new String[]{"abcd", "bcd", "xbcd"},
                new String[]{"cd", "bcd", "xyz"}));

        LogUtil.printArray(obj.stringIndices(new String[]{"abcdefgh", "poiuygh", "ghghgh"},
                new String[]{"gh", "acbfgh", "acbfegh"}));
    }

    public int[] stringIndices(String[] wordsContainer, String[] wordsQuery) {
        TrieNode root = new TrieNode('#');
        int smallestLengthInWordContainer = Integer.MAX_VALUE;
        int indexOfSmallestLength = 0;
        for (int i = 0; i < wordsContainer.length; i++) {
            root.insert(wordsContainer[i], i, wordsContainer);

            if (smallestLengthInWordContainer > wordsContainer[i].length()) {
                smallestLengthInWordContainer = wordsContainer[i].length();
                indexOfSmallestLength = i;
            }
        }

        int[] result = new int[wordsQuery.length];
        for (int i = 0; i < wordsQuery.length; i++) {
            String word = wordsQuery[i];
            int index = findIndexOfLongestCommonSuffix(root, word, indexOfSmallestLength);
            result[i] = index;
        }
        return result;
    }

    private int findIndexOfLongestCommonSuffix(TrieNode root, String word, Integer indexOfSmallestLength) {
        TrieNode crawler = root;
        Integer smallestSource = null;
        char[] charArray = word.toCharArray();
        for (int i = charArray.length - 1; i >= 0; i--) {
            int nextIndex = charArray[i] - 'a';
            if (crawler.children[nextIndex] == null) {
                break;
            }
            crawler = crawler.children[nextIndex];
            smallestSource = crawler.smallestSourceIndex;
        }
        return smallestSource != null ? smallestSource : indexOfSmallestLength;
    }

    static class TrieNode {
        TrieNode[] children;
        char val;
        Integer smallestSourceIndex;

        public TrieNode(char val) {
            this.children = new TrieNode[26];
            this.val = val;
        }

        public void addSourceIndex(int sourceWordIndex) {
            this.smallestSourceIndex = sourceWordIndex;
        }


        public void insert(String word, int index, String[] wordsContainer) {
            // Since it's LongestCommonSuffix we store word in reverse order in the trie
            TrieNode crawler = this;
            char[] charArray = word.toCharArray();
            for (int i = charArray.length - 1; i >= 0; i--) {
                char c = charArray[i];
                int nextIndex = c - 'a';
                if (crawler.children[nextIndex] == null) {
                    crawler.children[nextIndex] = new TrieNode(c);
                }
                TrieNode child = crawler.children[nextIndex];

                // This is the constraint which question gave us
                // 1. Use Smallest length when you get multiple longest common suffix
                // 2. If they have same length then use the one with smallest index
                if (child.smallestSourceIndex == null
                        || wordsContainer[child.smallestSourceIndex].length() > wordsContainer[index].length()
                        || (wordsContainer[child.smallestSourceIndex].length() == wordsContainer[index].length()
                        && child.smallestSourceIndex > index)) {
                    child.addSourceIndex(index);
                }
                crawler = crawler.children[nextIndex];
            }
        }
    }
}
