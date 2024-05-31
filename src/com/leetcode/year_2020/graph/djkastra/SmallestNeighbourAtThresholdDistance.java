package com.leetcode.year_2020.graph.djkastra;

import java.util.Arrays;

/**
 * 1334. Find the City With the Smallest Number of Neighbors at a Threshold Distance
 * https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance/description/
 * <p>
 * Input: n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
 * Output: 3
 * Explanation: The figure above describes the graph.
 * The neighboring cities at a distanceThreshold = 4 for each city are:
 * City 0 -> [City 1, City 2]
 * City 1 -> [City 0, City 2, City 3]
 * City 2 -> [City 0, City 1, City 3]
 * City 3 -> [City 1, City 2]
 * Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
 */
public class SmallestNeighbourAtThresholdDistance {

    public static void main(String[] args) {
        System.out.println(findTheCity(4,
                new int[][]{{0, 1, 3}, {1, 2, 1}, {1, 3, 4}, {2, 3, 1}}, 4));

        System.out.println(findTheCity(5,
                new int[][]{{0, 1, 2}, {0, 4, 8}, {1, 2, 3}, {1, 4, 2}, {2, 3, 1}, {3, 4, 1}}, 2));
    }

    public static int findTheCity(int n, int[][] edges, int distanceThreshold) {
        // Let's solve this  with FloyddWarshall

        int[][] minDistance = new int[n][n];
        for (int[] distance : minDistance) {
            Arrays.fill(distance, Integer.MAX_VALUE);
        }
        for (int i = 0; i < edges.length; i++) {
            int[] edge = edges[i];
            minDistance[edge[0]][edge[1]] = edge[2];
            minDistance[edge[1]][edge[0]] = edge[2];
        }
        for (int i = 0; i < n; i++) {
            minDistance[i][i] = 0;
        }
        for (int k = 0; k < n; k++) {
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    if (minDistance[i][k] == Integer.MAX_VALUE || minDistance[k][j] == Integer.MAX_VALUE) continue;
                    minDistance[i][j] = Math.min(minDistance[i][j], minDistance[i][k] + minDistance[k][j]);
                }
            }
        }


        int minCitiesTravelled = Integer.MAX_VALUE;
        int city = -1;
        for (int i = 0; i < minDistance.length; i++) {
            int totalDistanceTravelled = 0;
            for (int j = 0; j < minDistance[0].length; j++) {
                if (minDistance[i][j] <= distanceThreshold) {
                    totalDistanceTravelled++;
                }
            }
            if (minCitiesTravelled >= totalDistanceTravelled) {
                minCitiesTravelled = totalDistanceTravelled;
                city = i;
            }
        }
        return city;
    }
}
