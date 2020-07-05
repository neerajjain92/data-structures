package com.leetcode.year_2020.MayChallenge.week4;

import com.util.LogUtil;

import java.util.Arrays;

/**
 * https://leetcode.com/problems/edit-distance/
 *
 * @author neeraj on 31/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class EditDistance {

    public static void main(String[] args) {
        System.out.println(minDistance("horse", "ros"));
        System.out.println(minDistance("execution", "intention"));
        System.out.println(minDistance("zoologicoarchaeologist", "zoogeologist"));

        LogUtil.logIt("Top Down Approach.....");
        System.out.println(minDistanceTopDown("horse", "ros"));
        System.out.println(minDistanceTopDown("execution", "intention"));
        System.out.println(minDistanceTopDown("zoologicoarchaeologist", "zoogeologist"));
    }

    public static int minDistance(String word1, String word2) {
        /**
         * Intuition behind logic.
         *
         * Suppose we have 2 string :
         * A ===> "HORSE"     B ===> "ROS"
         *
         * Compare the last character of each... it's different so we have 3 operations to choose from
         * 1) Insert, 2) Delete, 3) Substitute.
         *
         *     minDistance("HORSE", "ROS")
         *
         *     A)   1 insert of 'S' + minDistance("HORSE", "RO")
         *                                          ---> What we are saying is we will insert 'S' after we get minDistance("HORSE", "RO")
         *     B)   1 delete of 'E' + minDistance("HORS", "ROS")
         *                                          ---> What we are saying is we will delete 'E' after we get minDistance("HORS", "ROS")
         *     C)   1 substitute of 'S' + minDistance("HORS", "RO")
         *                                          ---> What we are saying is we will substitute 'S' after we get minDistance("HORS", "RO")
         *
         * In the DP table they look like this
         *
         *
         *                       |
         *            SUBSTITUTE |  INSERT
         *          (i-1)(j-1)   |   (j-1)
         *        -------------------------------
         *                      |
         *            DELETE    |  i.e when the curr_character is different
         *          (i-1)       |  we do Min (Substitute, Insert, Delete) Operation.....
         *                          and then we do that operation which is why + 1.
         *
         */
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
                    distance[i][j] = distance[i - 1][j - 1];
                } else {
                    // Minimum of Insert, Delete or Substitute operation + 1.
                    distance[i][j] = Math.min(distance[i - 1][j],
                            Math.min(distance[i][j - 1], distance[i - 1][j - 1])) + 1;
                }
            }
        }
        return distance[distance.length - 1][distance[0].length - 1];
    }

    static int T[][]; // Memorization

    public static int minDistanceTopDown(String word1, String word2) {
        /**
         * We can also solve this problem in TopDown manner.
         * We know we have to find min of any 3 operation... which is insert, delete, replace.
         */
        T = new int[word1.length() + 1][word2.length() + 1];
        for (int[] row : T) {
            Arrays.fill(row, -1);
        }
        return minDistanceTopDown(word1, word2, word1.length() - 1, word2.length() - 1);
    }

    private static int minDistanceTopDown(String word1, String word2, int m, int n) {
        if (m < 0 && n < 0) return 0; // if both string is empty then we don't any operation.
        // else if either of these string is empty, then we need all insert operation which is the length of other string.

        // Why +1 since it's 0 based index and the caller of this method is passing length()-1.
        // so if we have input "" and "B"
        // m = -1, n = 0. Ideally we need 1 operation(insert) but if we don't add 1... then we don't get that.
        if (m < 0) return n + 1;
        if (n < 0) return m + 1;

        // Return from Cache.
        if (T[m][n] != -1) return T[m][n];

        // Now if both character match then we don't need to do any operation at this index
        // but we need to check other ones.
        if (word1.charAt(m) == word2.charAt(n)) {
            return T[m][n] = minDistanceTopDown(word1, word2, m - 1, n - 1);
        } else {
            // if mismatch
            // So we can either do delete, insert or substitute.....
            // but we can only do that once we know what is the min distance after i perform these operation
            int delete = minDistanceTopDown(word1, word2, m - 1, n);
            int insert = minDistanceTopDown(word1, word2, m, n - 1);
            int substitute = minDistanceTopDown(word1, word2, m - 1, n - 1);
            return T[m][n] = 1 + Math.min(delete, Math.min(insert, substitute));
        }
    }
}
