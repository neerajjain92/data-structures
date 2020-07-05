package com.leetcode.year_2020.MayChallenge.week2;

/**
 * @author neeraj on 14/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class ImplementTrie_Prefix_Tree {

    public static void main(String[] args) {
        Trie trie = new Trie();
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"cat",  "cats"};

        for (String key : keys) {
            trie.insert(key);
        }

        for (String key : keys) {
            System.out.println("Key : " + key + " ==> exist in TrieNode ? " + trie.search(key));
        }

        String prefixes[] = {"th", "a", "thre", "ans", "an", "b", "by", "thei", "cat", "cats"};
        for (String prefix : prefixes) {
            System.out.println("Prefix : " + prefix + " ==> startsWith in TrieNode ? " + trie.startsWith(prefix));
        }
    }
}

class Trie {

    TrieNode head;

    class TrieNode {
        Character value;
        TrieNode[] adjacents;
        boolean ends_word;

        public TrieNode(Character value) {
            this.value = value;
            this.adjacents = new TrieNode[26];
            this.ends_word = false;
        }
    }

    /**
     * Initialize your data structure here.
     */
    public Trie() {
        head = new TrieNode('#');
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode curr = head;
        for (char c : word.toCharArray()) {
            if (curr.adjacents[c - 'a'] == null) {
                curr.adjacents[c - 'a'] = new TrieNode(c);
            }
            curr = curr.adjacents[c - 'a'];
        }
        curr.ends_word = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode curr = head;
        for (char c : word.toCharArray()) {
            if (curr.adjacents[c - 'a'] != null) {
                curr = curr.adjacents[c - 'a'];
            } else {
                return false;
            }
        }
        return curr.ends_word;
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = head;
        for (char c : prefix.toCharArray()) {
            if (curr.adjacents[c - 'a'] != null) {
                curr = curr.adjacents[c - 'a'];
            } else {
                return false;
            }
        }
        return true;
    }
}
