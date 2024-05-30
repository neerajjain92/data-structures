package com.leetcode.year_2020.graph.bfs;

import com.util.LogUtil;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/submissions/
 * <p>
 * # WORD LADDER
 *
 * @author neeraj on 03/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class WordLadder1 {

    public static void main(String[] args) {
        LogUtil.logIt("Minimum Number of Transformation required for dog to lot is : " +
                ladderLength("dog", "lot", Arrays.asList("dot", "mot", "lot")));

        LogUtil.logIt("Minimum Number of Transformation required for hit to cog is : " +
                ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));

        LogUtil.logIt("Minimum Number of Transformation required for a to c is : " +
                ladderLength("a", "c", Arrays.asList("a", "b", "c")));

        LogUtil.newLine();

        LogUtil.logIt("Minimum Number of Transformation required for dog to lot is : " +
                ladderLengthOptimized("dog", "lot", Arrays.asList("dot", "mot", "lot")));

        LogUtil.logIt("Minimum Number of Transformation required for hit to cog is : " +
                ladderLengthOptimized("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));

        LogUtil.logIt("Minimum Number of Transformation required for a to c is : " +
                ladderLengthOptimized("a", "c", Arrays.asList("a", "b", "c")));
    }

    public static int ladderLengthOptimized(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;

        // Prepare identifierToReachableWordsMap
        Map<String, List<Node>> identifierToReachableWords = initializeTransformationsMapping(wordList);
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Node(beginWord, 1));
        visited.add(beginWord);

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            int distance = node.distance;
            final String value = node.val;
            for (int i = 0; i < value.length(); i++) {
                final String identifier = constructIdentifier(value, i);

                for (Node reachableWord : identifierToReachableWords.getOrDefault(identifier, new ArrayList<>())) {
                    if (reachableWord.val.equals(endWord)) {
                        return distance + 1;
                    }

                    if (!visited.contains(reachableWord.val)) {
                        queue.add(new Node(reachableWord.val, distance + 1));
                        visited.add(reachableWord.val);
                    }
                }
            }
        }
        return 0;
    }

    /**
     * So if the dictionary is "dot", "mot", "lot"
     * Map will look like this
     * <p>
     * | "*  o t" : ["dot", "mot", "lot"] |
     * | "d  * t" : ["dot"] |
     * | "d  o *" : ["dot"] |
     * | "m  * t" : ["mot"] |
     * | "m  o *" : ["mot"] |
     * | "l * t"  : ["lot"]
     * | "l o *"  : [""lot"]
     *
     * @param wordList
     * @return
     */
    private static Map<String, List<Node>> initializeTransformationsMapping(final List<String> wordList) {
        Map<String, List<Node>> identifierToReachableWords = new HashMap<>();
        for (String word : wordList) {
            for (int i = 0; i < word.length(); i++) {
                final String identifier = constructIdentifier(word, i);

                final List<Node> reachableWords = identifierToReachableWords.getOrDefault(identifier, new ArrayList<>());
                reachableWords.add(new Node(word, 1));

                identifierToReachableWords.put(identifier, reachableWords);
            }
        }
        return identifierToReachableWords;
    }

    private static String constructIdentifier(final String word, final int index) {
        return word.substring(0, index) + "*" + word.substring(index + 1);
    }

    static class Node {
        String val;
        int distance;

        public Node(final String val, final int distance) {
            this.val = val;
            this.distance = distance;
        }
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        /**
         * So we have a word and dictionary, what we have to do is try transforming 1 letter at a time
         * So we can basically try out 26 times [A-Z] changing each character at a time. Since we want minimum number
         * of transformation to achieve endWord,so we will do BFS.
         */
        Queue<Node> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(new Node(beginWord, 1));
        visited.add(beginWord);
        while (!queue.isEmpty()) {
            Node intermediateWord = queue.poll();
            System.out.print(intermediateWord.val + " ---> ");
            for (int i = 0; i < intermediateWord.val.length(); i++) {
                char[] charArr = intermediateWord.val.toCharArray();

                for (char character = 'a'; character <= 'z'; character++) {
                    charArr[i] = character;

                    String transformedWord = new String(charArr);
                    if (transformedWord.equals(endWord)) {
                        System.out.println(transformedWord);
                        return intermediateWord.distance + 1; // Since we are early exiting and hence increasing as we want to include destination also in the transformation sequence.
                    }

                    if (wordList.contains(transformedWord) && !visited.contains(transformedWord)) {
                        queue.add(new Node(transformedWord, intermediateWord.distance + 1));
                        visited.add(transformedWord);
                    }
                }
            }
        }
        return 0;
    }
}
