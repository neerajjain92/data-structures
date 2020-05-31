package com.leetcode.year_2020.MayChallenge.week4;

/**
 * @author neeraj on 31/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("execution", "intention"));
        System.out.println(minDistance("zoologicoarchaeologist", "zoogeologist"));
    }

    public static int minDistance(String word1, String word2) {
        if (word1.length() > word2.length()) return minDistance(word2, word1);
        int[][] distance = new int[word1.length() + 1][word2.length() + 1];

        // Now for base case we know that if any of the string is blank
        // the inorder to make that blank other string we have to delete all
        // characters from the other string.

        // when both the String is blank then we don't need any operation.
        distance[0][0] = 0;

        // 1st Row[we have to move columns]
        for (int j = 1; j < word2.length() + 1; j++)
            distance[0][j] = j; // we need these many delete operations, since word1 at 1st row is blank.
        // 1st Column[we have to move rows]
        for (int i = 1; i < word1.length() + 1; i++) {
            distance[i][0] = i; // we need these many delete operations, since word2 at 1st column is blank.
        }

        for (int i = 1; i < distance.length; i++) {
            for (int j = 1; j < distance[i].length; j++) {
                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    distance[i][j] = distance[i-1][j-1];
                } else {
                    distance[i][j] = Math.min(distance[i - 1][j], Math.min(distance[i][j - 1], distance[i - 1][j - 1])) + 1;
                }
            }
        }
        return distance[distance.length - 1][distance[0].length - 1];
    }
}
