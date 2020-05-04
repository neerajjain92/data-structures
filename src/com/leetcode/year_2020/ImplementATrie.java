package com.leetcode.year_2020;

/**
 * @author neeraj on 27/04/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
@SuppressWarnings("ALL")
public class ImplementATrie {

    TrieNode root;

    public static void main(String[] args) {
        ImplementATrie trie = new ImplementATrie();
        // Input keys (use only 'a' through 'z' and lower case)
        String keys[] = {"the", "a", "there", "answer", "any",
                "by", "bye", "their"};

        for (String key : keys) {
            trie.insert(key);
        }

        for (String key : keys) {
            System.out.println("Key : " + key + " ==> exist in TrieNode ? " + trie.search(key));
        }

        String prefixes[] = {"th", "a", "thre", "ans", "an", "b", "by", "thei"};
        for (String prefix : prefixes) {
            System.out.println("Prefix : " + prefix + " ==> startsWith in TrieNode ? " + trie.startsWith(prefix));
        }

    }

    public ImplementATrie() {
        root = new TrieNode('#'); // DummyNode
    }

    /**
     * Inserts a word into the trie.
     */
    public void insert(String word) {
        TrieNode curr = root;
        for (int level = 0; level < word.length(); level++) {
            char nextCharacter = word.charAt(level);
            int accessIndex = nextCharacter - 'a';

            if (curr.adjacents[accessIndex] == null) {
                curr.adjacents[accessIndex] = new TrieNode(nextCharacter);
            }
            curr = curr.adjacents[accessIndex];
        }
        curr.endsWord = true;
    }

    /**
     * Returns if the word is in the trie.
     */
    public boolean search(String word) {
        TrieNode curr = root;
        for (int level = 0; level < word.length(); level++) {
            char nextCharacter = word.charAt(level);
            int accessIndex = nextCharacter - 'a';
            if (curr.adjacents[accessIndex] == null) return false;
            curr = curr.adjacents[accessIndex];
        }
        if (curr.endsWord) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * Returns if there is any word in the trie that starts with the given prefix.
     */
    public boolean startsWith(String prefix) {
        TrieNode curr = root;
        for (int level = 0; level < prefix.length(); level++) {
            char nextCharacter = prefix.charAt(level);
            int accessIndex = nextCharacter - 'a';
            if (curr.adjacents[accessIndex] == null) return false;
            curr = curr.adjacents[accessIndex];
        }
        return true;
    }

    private class TrieNode {
        char character;
        TrieNode[] adjacents;
        boolean endsWord;

        public TrieNode(char character) {
            this.character = character;
            this.adjacents = new TrieNode[26];
            this.endsWord = false;
        }
    }
}


