package com.leetcode.backtracking;

import java.util.HashMap;
import java.util.Map;

public class MaximumScoreWordsFormedByLetters {

    /**
     * This is a classic case of backtracking, ToTake-Or-NotTake
     */
    int total;
    Map<String, Map<Character, Integer>> wordCharFreq;
    Map<Character, Integer> charFreq;
    Map<String, Integer> precomputedWordScore;

    public static void main(String[] args) {
        MaximumScoreWordsFormedByLetters obj = new MaximumScoreWordsFormedByLetters();
        System.out.println(obj.maxScoreWords(new String[]{"dog", "cat", "dad", "good"},
                new char[]{
                        'a', 'a', 'c', 'd', 'd', 'd', 'g', 'o', 'o'
                },
                new int[]{1, 0, 9, 5, 0, 0, 3, 0, 0, 0, 0, 0, 0, 0, 2, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}
        ));

        System.out.println(obj.maxScoreWords(new String[]{"xxxz", "ax", "bx", "cx"}, new char[]{
                        'z', 'a', 'b', 'c', 'x', 'x', 'x'
                },
                new int[]{4, 4, 4, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 5, 0, 10}
        ));
    }

    public int maxScoreWords(String[] words, char[] letters, int[] score) {
        total = 0;
        charFreq = new HashMap<>();
        precomputedWordScore = new HashMap<>();
        for (char letter : letters) {
            charFreq.put(letter, charFreq.getOrDefault(letter, 0) + 1);
        }
        wordCharFreq = calculateWordCharFreq(words, score);
        int pointer = 0, currScore = 0;
        solve(pointer, words, currScore);
        return total;
    }

    private Map<String, Map<Character, Integer>> calculateWordCharFreq(String[] words, int[] score) {
        Map<String, Map<Character, Integer>> wordCharFreq = new HashMap<>();
        for (String word : words) {
            Map<Character, Integer> charFreq = new HashMap<>();
            int totalScoreOfWord = 0;
            for (char letter : word.toCharArray()) {
                charFreq.put(letter, charFreq.getOrDefault(letter, 0) + 1);
                totalScoreOfWord += score[letter - 'a'];
            }
            precomputedWordScore.put(word, totalScoreOfWord);
            wordCharFreq.put(word, charFreq);
        }
        return wordCharFreq;
    }

    private void solve(int pointer, String[] words, int currScore) {
        if (pointer == words.length) {
            total = Math.max(total, currScore);
            return;
        }

        // Not take, so we just increment pointer
        solve(pointer + 1, words, currScore);

        // We will take it but we can only take if our Map has letters left
        String word = words[pointer];
        if (canTake(word, charFreq)) {
            int scoreOfWord = precomputedWordScore.get(word);
            decrementFreq(word);
            solve(pointer + 1, words, currScore + scoreOfWord);
            incrementFreq(word);
        }
    }

    private void incrementFreq(String word) {
        Map<Character, Integer> freq = wordCharFreq.get(word);
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            charFreq.put(entry.getKey(), charFreq.get(entry.getKey()) + entry.getValue());
        }
    }

    private void decrementFreq(String word) {
        Map<Character, Integer> freq = wordCharFreq.get(word);
        for (Map.Entry<Character, Integer> entry : freq.entrySet()) {
            charFreq.put(entry.getKey(), charFreq.get(entry.getKey()) - entry.getValue());
        }
    }

    private boolean canTake(String word, Map<Character, Integer> charFreq) {
        Map<Character, Integer> freqOfWordCharBeingInspected = wordCharFreq.get(word);
        for (Map.Entry<Character, Integer> entry : freqOfWordCharBeingInspected.entrySet()) {
            if (!charFreq.containsKey(entry.getKey()) || charFreq.get(entry.getKey()) < entry.getValue()) {
                return false;
            }
        }
        return true;
    }
}
