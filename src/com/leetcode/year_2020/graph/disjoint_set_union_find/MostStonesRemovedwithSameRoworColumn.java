package com.leetcode.year_2020.graph.disjoint_set_union_find;

/**
 * @author neeraj on 30/05/20
 * Copyright (c) 2019, data-structures.
 * All rights reserved.
 */
public class MostStonesRemovedwithSameRoworColumn {

    public static void main(String[] args) {
        System.out.println(removeStones(new int[][]{
                {0, 0}, {0, 1}, {1, 0}, {1, 2}, {2, 1}, {2, 2}
        }));
        System.out.println(removeStones(new int[][]{
                {0, 0}, {0, 2}, {1, 1}, {2, 0}, {2, 2}
        }));
        System.out.println(removeStones(new int[][]{
                {0, 0}
        }));
    }

    public static int removeStones(int[][] stones) {
        if (stones == null || stones.length == 0) return 0;
        /**
         * We can only remove the stone if there is any other stone
         * which can cover for me (i.e other stone in the same row or column.)
         *
         *
         *
         * So essentially you have to tell whether or not there exist another stone in same row and column
         * Now the input clearly tell us in which row or column stone exist.
         *
         * every-time we have to compare ith item with jth item to check if stone exist or not.
         * [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
         *    i     j
         *               j
         *                      j
         *                            j
         *                                  j
         *  Now in next iteration
         *          i
         *              j
         *                     j
         *                          j
         *                                  j   and so on.........
         */

        int[] leaders = new int[stones.length];
        int connectedComponents = stones.length;
        for (int i = 0; i < leaders.length; i++) leaders[i] = i;

        for (int i = 0; i < stones.length - 1; i++) {
            for (int j = i + 1; j < stones.length; j++) {

                if (stones[i][0] != stones[j][0] && stones[i][1] != stones[j][1])
                    continue; // Means no other stone in the same row or column.

                // Now if they do share same row or column, let's just bind them in the same component.
                int leaderOfFirstStone = findLeader(leaders, i);
                int leaderOfSecondStone = findLeader(leaders, j);

                if (leaderOfFirstStone == leaderOfSecondStone) continue; // they already belong to the same group

                leaders[leaderOfFirstStone] = leaderOfSecondStone; // Union.
                connectedComponents--;
            }
        }
        int totalStones = stones.length;

        // We can consider all stones from connected components as just 1.
        return totalStones - connectedComponents; // is the max amount of stones we can remove.
    }

    private static int findLeader(int[] leaders, int i) {
        int leaderOfI = leaders[i];

        if (leaderOfI != i) {// Path compression
            leaders[i] = findLeader(leaders, leaderOfI);
        }
        return leaders[i];
    }
}
