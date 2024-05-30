package com.leetcode.year_2020.graph.bfs;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder-ii/description/
 */
public class WordLadder2 {

    public static void main(String[] args) {
        /**
         * BEGIN=PAT
         * END=COZ
         *
         * DICTIONARY:==> PAT, BOT, POT, POZ, COZ
         */
//        System.out.println(findLadders("pat", "coz", new ArrayList<>(Arrays.asList("bot", "pot", "poz", "coz"))));
        System.out.println(findLadders("red", "tax", new ArrayList<>(Arrays.asList("ted", "tex", "red", "tax", "tad", "den", "rex", "pee"))));
    }

    public static List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) {
            return Collections.emptyList();
        }
        Queue<List<String>> queue = new LinkedList<>(); // This will hold the path
        List<List<String>> result = new ArrayList<>();
        Set<String> visited = new HashSet<>();
        queue.add(Arrays.asList(beginWord));
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            int size = queue.size();

            while (size-- > 0) {
                List<String> currentPath = queue.poll();
                String lastWord = currentPath.get(currentPath.size() - 1);

                for (String neighbour : getReachableWord(lastWord, wordList, visited)) {
                    List<String> newPath = new ArrayList<>(currentPath);
                    newPath.add(neighbour);
                    visited.add(neighbour);
                    if (neighbour.equalsIgnoreCase(endWord)) {
                        result.add(newPath);
                    } else {
                        queue.offer(newPath);
                    }
                }
            }

            for (String visitedWord : visited) {
                wordList.remove(visitedWord);
            }
        }
        return result;
    }

    private static List<String> getReachableWord(String lastWord, List<String> wordList, Set<String> visited) {
        List<String> reachableWords = new ArrayList<>();
        for (int i = 0; i < lastWord.length(); i++) {
            char[] lastWordCharArr = lastWord.toCharArray();
            for (char c = 'a'; c <= 'z'; c++) {
                lastWordCharArr[i] = c;
                String transformedStr = new String(lastWordCharArr);
                if (wordList.contains(transformedStr)) {
                    reachableWords.add(transformedStr);
                }
            }
        }
        return reachableWords;
    }
}
