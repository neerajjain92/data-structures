package com.leetcode.year_2020.trie.coding_with_mik;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ReplaceWords {

    public static void main(String[] args) {
        ReplaceWords obj = new ReplaceWords();
        System.out.println(obj.replaceWords(List.of("cat", "bat", "rat"), "the cattle was rattled by the battery"));
        System.out.println(obj.replaceWords(List.of("a", "b", "c"), "aadsfasf absbs bbab cadsfafs"));
    }

    public String replaceWords(List<String> dictionary, String sentence) {
        TrieNode root = new TrieNode('#');
        for (String word : dictionary) {
            root.insert(word);
        }
        List<String> result = new ArrayList<>();
        for (String word : sentence.split(" ")) {
            String rootOfDerivitive = root.findRootForDerivative(word);
            result.add(rootOfDerivitive);
        }
        return String.join(" ", result);
    }

    static class TrieNode {
        TrieNode[] children;
        char c;
        boolean endOfWord = false;

        public TrieNode(char c) {
            this.c = c;
            this.children = new TrieNode[26];
        }

        public void insert(String word) {
            TrieNode crawler = this;
            for (char c : word.toCharArray()) {
                int nextIndex = c - 'a';
                if (crawler.children[nextIndex] == null) {
                    crawler.children[nextIndex] = new TrieNode(c);
                }
                crawler = crawler.children[nextIndex];
            }
            crawler.endOfWord = true;
        }

        public String findRootForDerivative(String word) {
            TrieNode crawler = this;
            char[] charArray = word.toCharArray();
            for (int i = 0; i < charArray.length; i++) {
                int nextIndex = charArray[i] - 'a';
                if (crawler.children[nextIndex] == null) {
                    return word;
                }
                crawler = crawler.children[nextIndex];
                if (crawler.endOfWord) {
                    return word.substring(0, i + 1);
                }
            }
            return word;
        }
    }
}
