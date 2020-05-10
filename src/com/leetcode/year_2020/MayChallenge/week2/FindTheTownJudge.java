package com.leetcode.year_2020.MayChallenge.week2;

/**
 * @author neeraj on 10/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class FindTheTownJudge {

    public static void main(String[] args) {
        System.out.println(findJudge(2, new int[][]{
                {1, 2}
        }));

        System.out.println(findJudge(3, new int[][]{
                {1, 2},
                {2, 3}
        }));

        System.out.println(findJudge(3, new int[][]{
                {1, 3},
                {2, 3}
        }));
        System.out.println(findJudge(3, new int[][]{
                {1, 3},
                {2, 3},
                {3, 1}
        }));
        System.out.println(findJudge(3, new int[][]{
                {1, 2},
                {2, 3}
        }));
        System.out.println(findJudge(4, new int[][]{
                {1, 3},
                {1, 4},
                {2, 3},
                {2, 4},
                {4, 3}
        }));
        System.out.println(findJudge(11, new int[][]{
                {1, 8}, {1, 3}, {2, 8}, {2, 3}, {4, 8}, {4, 3}, {5, 8}, {5, 3}, {6, 8}, {6, 3},
                {7, 8}, {7, 3}, {9, 8}, {9, 3}, {11, 8}, {11, 3}
        }));

    }


    public static int findJudge(int N, int[][] trust) {
        if (trust.length < N - 1) {
            return -1;
        }
        // A --knows B--> so B has 1 increase in IncomingEdges and A has 1 increase in outgoing edges.
        int[] inEdges = new int[N + 1];
        int[] outEdges = new int[N + 1];

        for (int[] trustEntry : trust) {
            inEdges[trustEntry[1]]++;
            outEdges[trustEntry[0]]++;
        }

        for (int i = 1; i <= N; i++) {
            if (inEdges[i] == N - 1 && outEdges[i] == 0) {
                return i;
            }
        }
        return -1;
    }
}
