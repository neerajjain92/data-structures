package com.leetcode.year_2020.graph.bfs;

import com.util.LogUtil;

import java.util.*;

/**
 * https://leetcode.com/problems/word-ladder/submissions/
 *
 * @author neeraj on 03/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class StringTransformations {

    public static void main(String[] args) {
        LogUtil.logIt("Minimum Number of Transformation required for dog to lot is : " +
                ladderLength("dog", "lot", Arrays.asList("dot", "mot", "lot")));

        LogUtil.logIt("Minimum Number of Transformation required for hit to cog is : " +
                ladderLength("hit", "cog", Arrays.asList("hot", "dot", "dog", "lot", "log", "cog")));

        LogUtil.logIt("Minimum Number of Transformation required for a to c is : " +
                ladderLength("a", "c", Arrays.asList("a", "b", "c")));
    }

    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        if (!wordList.contains(endWord)) return 0;
        /**
         * So we have a word and dictionary, what we have to do is try transforming 1 letter at a time
         * So we can basically try out 26 times [A-Z] changing each character at a time. Since we want minimum number
         * of transformation to achieve endWord,so we will do BFS.
         */
        Queue<String> queue = new LinkedList<>();
        Set<String> visited = new HashSet<>();

        queue.add(beginWord);
        visited.add(beginWord);
        int numberOfTransformation = 1;
        int levelToBeTransformed = 0;
        // For Word DOG we have 3 levels(indexed 0 basis)
        // D | O | G
        // 0   1   2
        while (!queue.isEmpty()) {
            String intermediateWord = queue.poll();
            numberOfTransformation++;
            System.out.print(intermediateWord + " ---> ");
            for (int i = 0; i < intermediateWord.length(); i++) {
                char[] charArr = intermediateWord.toCharArray();

                for (char character = 'a'; character <= 'z'; character++) {
                    charArr[i] = character;

                    String transformedWord = new String(charArr);
                    if (transformedWord.equals(endWord)) {
                        System.out.println(transformedWord);
                        return numberOfTransformation;
                    }

                    if (wordList.contains(transformedWord) && !visited.contains(transformedWord)) {
                        queue.add(transformedWord);
                        visited.add(transformedWord);
                    }
                }
            }
        }
        return -1;
    }
}
