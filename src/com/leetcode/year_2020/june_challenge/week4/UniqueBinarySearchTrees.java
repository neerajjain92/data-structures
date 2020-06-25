package com.leetcode.year_2020.june_challenge.week4;

import com.util.LogUtil;

/**
 * @author neeraj on 24/06/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class UniqueBinarySearchTrees {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            LogUtil.logIt("Unique BST with Nodes --> " + i + " is " + numTrees(i));
        }
    }

    public static int numTrees(int n) {
        /**
         * https://www.youtube.com/watch?v=GgP75HAvrlY
         * Okay so Approach...... we know 2 things for sure when we have 0 nodes, how many unique BST we can make
         * just 1. right (it will be EMPTY BST)
         * Okay so now similarly when we have 1 node how many unique BST can we make .... just 1 node sitting at Root.
         *
         * Now interesting thing starts at when 2 nodes are there [1,2]
         * we can either put 2 as Root or 1 as root.... in both the cases we have 1 side empty.
         *    2          or        1
         *   /                      \
         *  /                        \
         * 1                          2
         *
         * Let's check [1,2,3]
         * Keep 1 as root.                     keeping 2 as root.               keeping 1 as root.
         *
         *      1                   1                       2                      3        or     3
         *       \                   \                     / \                    /               /
         *        \                  3                    1   3                  1               2
         *        2                 /                                             \             /
         *         \               /                                               \           1
         *          \             2                                                 2
         *           3
         *
         *  So to get NumTrees At (N) it's a combination of Number of Combination by putting [1..........N] numbers as root.
         *  NumTrees or G[3] = F[1, 3] + F[2, 3] + F[3,3]    where F is a function F(i=currentRoot, N=TotalNodes)
         *
         *                                        N u m T r e e s [3]
         *                                      /         |          \
         *                                   F[1,3]      F[2]         F[3]
         *                                   /  \        /  \       /  \
         *                                  /    \      /    \     /    \
         *                                 G(0) G(2)  G(1)   G(1) G(2)  G(0)
         *  General Formula:
         *  F(i, n) = G(i-1) * G(n-i).
         */
        if (n <= 1) return 1;
        int[] G = new int[n + 1];
        G[0] = 1;
        G[1] = 1; // Base Clause.

        for (int i = 2; i <= n; i++) {
            for (int j = 1; j <= i; j++) {
                G[i] += G[j - 1] * G[i - j];
            }
        }
        return G[n];
    }
}
