package com.leetcode.year_2020.trie.coding_with_mik;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class WordSearch2 {

    static TrieNode trie;
    Set<String> result;

    public static void main(String[] args) {
        WordSearch2 wordSearch2 = new WordSearch2();
        char[][] board = {
                {'o', 'a', 'a', 'n'},
                {'e', 't', 'a', 'e'},
                {'i', 'h', 'k', 'r'},
                {'i', 'f', 'l', 'v'}
        };

        String[] words = {"oath", "pea", "eat", "rain"};
        List<String> result = wordSearch2.findWords(board, words);
        System.out.println(result);
    }

    public List<String> findWords(char[][] board, String[] words) {
        trie = new TrieNode();
        result = new HashSet<>();
        for (String word : words) {
            trie.insertWord(word);
        }

        // Check with each cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[0].length; j++) {
                findWords(i, j, board, "");
            }
        }
        return new ArrayList<>(result);
    }

    private void findWords(int i, int j, char[][] board, String current) {
        if (i < 0 || i >= board.length || j < 0 || j >= board[0].length || board[i][j] == '#') {
            return;
        }

        current += board[i][j]; // We are exploring this new coordinate, so lets append

        if (!trie.startsWith(current)) {
            return; // Early exit bro
        }

        if (trie.search(current)) {
            result.add(current); // Add to result set if word is available in trie
        }

        // Now travel in 4 directions
        char prevCharAtI_J = board[i][j];
        board[i][j] = '#'; // Marking visited
        findWords(i - 1, j, board, current);
        findWords(i, j + 1, board, current);
        findWords(i + 1, j, board, current);
        findWords(i, j - 1, board, current);

        board[i][j] = prevCharAtI_J;
    }

    static class TrieNode {
        TrieNode[] children;
        boolean endOfWord;

        public TrieNode() {
            this.children = new TrieNode[26];
        }

        public void insertWord(String word) {
            TrieNode curr = trie;
            for (char c : word.toCharArray()) {
                int nextIndex = c - 'a';
                if (curr.children[nextIndex] == null) {
                    curr.children[nextIndex] = new TrieNode();
                }
                curr = curr.children[nextIndex];
            }
            curr.endOfWord = true;
        }

        public boolean search(String word) {
            TrieNode curr = trie;
            for (char c : word.toCharArray()) {
                int nextIndex = c - 'a';
                if (curr.children[nextIndex] == null) {
                    return false;
                }
                curr = curr.children[nextIndex];
            }
            return curr.endOfWord;
        }

        public boolean startsWith(String prefix) {
            TrieNode curr = trie;
            for (char c : prefix.toCharArray()) {
                int nextIndex = c - 'a';
                if (curr.children[nextIndex] == null) {
                    return false;
                }
                curr = curr.children[nextIndex];
            }
            return true;
        }
    }
}
