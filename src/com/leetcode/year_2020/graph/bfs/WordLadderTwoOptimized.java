package com.leetcode.year_2020.graph.bfs;

import java.util.*;

public class WordLadderTwoOptimized {

    public static void main(String[] args) {
        WordLadderTwoOptimized wordLadderTwoOptimized = new WordLadderTwoOptimized();
//        System.out.println(wordLadderTwoOptimized.findLadders("hit", "cog", List.of("hot", "dot", "dog", "lot", "log", "cog")));
        System.out.println(wordLadderTwoOptimized.findLadders("bat", "coz", List.of("pat", "bot", "pot", "poz", "coz")));
    }

    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        Map<String, List<String>> reachableWords = preprocessDictionary(wordList);
        Set<String> visitedNodes = new HashSet<>();
        Set<String> nodesAdded = new HashSet<>();

        // Now since we need all paths so our queue should hold the entire path
        // and we will take the last item of the path and find other neighbours
        Queue<List<String>> queue = new LinkedList<>();
        List<String> path = new ArrayList<>();
        List<List<String>> result = new ArrayList<>();
        path.add(beginWord);
        queue.add(path);
        visitedNodes.add(beginWord);

        while (!queue.isEmpty()) {
            int queueSize = queue.size();

            while (queueSize-- > 0) {
                List<String> currPath = queue.poll();
                String lastWord = currPath.get(currPath.size() - 1);
                // Find all reachable paths from this word
                for (String reachableWord : findReachableWords(lastWord, reachableWords, visitedNodes)) {
                    nodesAdded.add(reachableWord);
                    List<String> newPath = new ArrayList<>(currPath);
                    newPath.add(reachableWord);
                    if (reachableWord.equals(endWord)) {
                        result.add(newPath);
                    } else {
                        queue.offer(newPath);
                    }
                }
            }

            // Now remove all those nodes you added
            // Only after the level is processed
            for (String node : nodesAdded) {
                visitedNodes.add(node);
            }
        }

        return result;
    }

    private List<String> findReachableWords(String lastWord, Map<String, List<String>> reachableWords, Set<String> visitedNodes) {
        List<String> reachable = new ArrayList<>();
        for (int i = 0; i < lastWord.length(); i++) {
            String identifier = constuctUniqueIdentifier(lastWord, i);
            for (String reachableWord : reachableWords.getOrDefault(identifier, new ArrayList<>())) {
                if (!visitedNodes.contains(reachableWord)) {
                    reachable.add(reachableWord);
                }
            }
        }
        return reachable;
    }

    private Map<String, List<String>> preprocessDictionary(List<String> wordList) {
        Map<String, List<String>> reachableWords = new HashMap<>();

        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                String identifier = constuctUniqueIdentifier(word, i);
                reachableWords.putIfAbsent(identifier, new ArrayList<>());
                reachableWords.get(identifier).add(word);
            }
        }
        return reachableWords;
    }

    private String constuctUniqueIdentifier(String word, int index) {
        return word.substring(0, index) + "*" + word.substring(index + 1);
    }
}
