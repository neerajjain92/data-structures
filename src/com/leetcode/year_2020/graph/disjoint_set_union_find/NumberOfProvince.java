package com.leetcode.year_2020.graph.disjoint_set_union_find;

import java.util.HashSet;
import java.util.Set;

/**
 *
 */
public class NumberOfProvince {

    public static void main(String[] args) {
        System.out.println(findNumberOfProvince(new int[][]{
                {1, 1, 0},
                {1, 1, 0},
                {0, 0, 1}
        }));

        System.out.println(findNumberOfProvince(new int[][]{
                {1, 0, 0},
                {0, 1, 0},
                {0, 0, 1}
        }));

        System.out.println(findNumberOfProvince(new int[][]{
                {1, 1, 0},
                {0, 0, 1},
                {0, 0, 0}
        }));
    }


    public static int findNumberOfProvince(int[][] isConnected) {
        DisjointSetImplementation disjointSetImplementation = new DisjointSetImplementation(isConnected.length);
        for (int i = 0; i < isConnected.length; i++) {
            for (int j = 0; j < isConnected[0].length; j++) {
                if (isConnected[i][j] == 1) {
                    disjointSetImplementation.unionBySize(i, j);
                }
            }
        }
        Set<Integer> totalParents = new HashSet<>();
        for (int i = 0; i < isConnected.length; i++) {
            totalParents.add(disjointSetImplementation.findUltimateParent(i));
        }
        return totalParents.size();
    }
}
